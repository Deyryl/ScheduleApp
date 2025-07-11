package com.vnmhpractice.scheduleapp.authorization

import com.vnmhpractice.scheduleapp.models.*
import com.vnmhpractice.scheduleapp.networking.ApiRoutes
import com.vnmhpractice.scheduleapp.networking.safeApiCall
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import util.NetworkError
import util.Result


class Auth(private val httpClient: HttpClient) {
    @Serializable
    data class AuthRequest(
        val username: String? = null,
        val email: String,
        val password: String
    )

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<Pair<User, TokenPair>, NetworkError> = safeApiCall {

        val user: User = httpClient.post(ApiRoutes.Auth.REGISTER) {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(
                username = username,
                email = email,
                password = password
            ))
        }.body()
        val tokenPair: TokenPair = httpClient.post(ApiRoutes.Auth.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(
                email = email,
                password = password
            ))
        }.body()
        user to tokenPair
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<Pair<User, TokenPair>, NetworkError> = safeApiCall {
        val user: User = httpClient.get(ApiRoutes.Users.ALL) {
            parameter("email", email)
        }.body()

        val tokenPair: TokenPair = httpClient.post(ApiRoutes.Auth.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(
                email = email,
                password = password
            ))
        }.body()

        user to tokenPair
    }
}