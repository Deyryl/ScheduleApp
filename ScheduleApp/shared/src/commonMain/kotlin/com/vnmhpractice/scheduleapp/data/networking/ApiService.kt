package com.vnmhpractice.scheduleapp.data.networking

import io.ktor.client.*
import com.vnmhpractice.scheduleapp.data.dtoClasses.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import util.NetworkError
import util.Result

interface ApiService {
    suspend fun registerUser(dto: AuthRequest): Result<AuthResponse, NetworkError>
    suspend fun loginUser(dto: AuthRequest): Result<AuthResponse, NetworkError>
//    suspend fun getUserByEmail(email: String): UserDTO
//    suspend fun getUserById(id: String): UserDTO
//    suspend fun updateUser(dto: UserDTO): UserDTO
}

class ApiServiceImpl(
    private val httpClient: HttpClient
): ApiService {
    override suspend fun registerUser(dto: AuthRequest): Result<AuthResponse, NetworkError> =
        safeApiCall {
            httpClient.post(ApiRoutes.Auth.REGISTER) {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }.body<AuthResponse>()
        }

    override suspend fun loginUser(dto: AuthRequest): Result<AuthResponse, NetworkError> =
        safeApiCall {
            httpClient.post(ApiRoutes.Auth.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }.body<AuthResponse>()
        }

//    suspend fun getUserById(userId: String): UserDTO {
//        return client.get(ApiRoutes.Users.BY_ID(userId)).body()
//    }
//
//    suspend fun getUserByEmail(email: String): UserDTO {
//        return client.get(ApiRoutes.Users.byEmail(email)).body()
//    }
//
//    suspend fun updateUser(userDTO: UserDTO): UserDTO {
//        return client.put(ApiRoutes.Users.UPDATE) {
//            contentType(ContentType.Application.Json)
//            setBody(userDTO)
//        }.body()
//    }
}