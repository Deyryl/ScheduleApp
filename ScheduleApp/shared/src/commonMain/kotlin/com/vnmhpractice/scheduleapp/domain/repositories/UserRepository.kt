package com.vnmhpractice.scheduleapp.domain.repositories

import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthRequest
import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthResponse
import com.vnmhpractice.scheduleapp.domain.domainModels.User
import util.NetworkError
import util.Result

interface UserRepository {
    suspend fun registerUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError>
    suspend fun loginUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError>
    suspend fun getUserFromId(userId: String): User?
    suspend fun getUserFromEmail(email: String): User?
    suspend fun updateUser(user: User): User
}