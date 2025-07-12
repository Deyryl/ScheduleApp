package com.vnmhpractice.scheduleapp.data.RepositoryImplementations

import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthRequest
import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthResponse
import com.vnmhpractice.scheduleapp.data.dtoClasses.UserDTO
import com.vnmhpractice.scheduleapp.data.mapper.toDomain
import com.vnmhpractice.scheduleapp.data.networking.ApiService
import com.vnmhpractice.scheduleapp.domain.domainModels.User
import com.vnmhpractice.scheduleapp.domain.repositories.UserRepository
import util.NetworkError
import util.Result
import util.map

class UserRepositoryImpl(
    private val api: ApiService,
): UserRepository {
    override suspend fun registerUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError> {
        return api.registerUser(authRequest)
    }
    override suspend fun loginUser(authRequest: AuthRequest): Result<AuthResponse, NetworkError> {
        return api.loginUser(authRequest)
    }
    override suspend fun getUserFromId(userId: String): User? {
        val userDTO: UserDTO = api.getUserFromId(userId)
        val user: User = userDTO.toDomain()
        return user
    }
    override suspend fun getUserFromEmail(email: String): User? {
        val userDTO: UserDTO = api.getUserFromEmail(email)
        val user: User = userDTO.toDomain()
        return user
    }
    override suspend fun updateUser(user: User): User {
        val userDTO = api.updateUser(user)
        val user: User = userDTO.toDomain()
        return user
    }

}