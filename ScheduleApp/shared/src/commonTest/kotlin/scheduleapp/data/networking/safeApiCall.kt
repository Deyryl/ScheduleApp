package com.vnmhpractice.scheduleapp.data.networking

import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.SerializationException
import util.NetworkError
import util.Result

suspend fun <T> safeApiCall(
    block: suspend () -> T
): Result<T, NetworkError> = try {
    Result.Success(block())
} catch (e: HttpRequestTimeoutException) {
    Result.Error(NetworkError.REQUEST_TIMEOUT)
} catch (e: ResponseException) {
    val code = e.response.status.value
    val error = when (code) {
        401                            -> NetworkError.UNAUTHORIZED
        409                            -> NetworkError.CONFLICT
        429                            -> NetworkError.TOO_MANY_REQUESTS
        413                            -> NetworkError.PAYLOAD_TOO_LARGE
        in 500..599              -> NetworkError.SERVER_ERROR
        else                           -> NetworkError.UNKNOWN
    }
    Result.Error(error)
} catch (e: SerializationException) {
    Result.Error(NetworkError.SERIALIZATION)
} catch (e: IOException) {
    Result.Error(NetworkError.NO_INTERNET)
} catch (_: Exception) {
    Result.Error(NetworkError.UNKNOWN)
}

