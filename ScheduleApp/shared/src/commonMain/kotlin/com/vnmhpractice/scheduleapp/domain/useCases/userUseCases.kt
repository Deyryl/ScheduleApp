package com.vnmhpractice.scheduleapp.domain.useCases

import com.vnmhpractice.scheduleapp.data.dtoClasses.AuthRequest
import com.vnmhpractice.scheduleapp.data.mapper.toDomain
import com.vnmhpractice.scheduleapp.domain.domainModels.LoginData
import com.vnmhpractice.scheduleapp.domain.domainModels.User
import com.vnmhpractice.scheduleapp.domain.repositories.UserRepository
import util.map
import util.*

class RegisterUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(username: String, email: String, password: String, repeatPassword: String): LoginData? {
        require(password == repeatPassword) { "Ошибка регистрации" }
        return repository
            .registerUser(AuthRequest(username, email, password))
            .onError {
                throw IllegalArgumentException(message = "Ошибка регистрации")
            }
            .map { it.toDomain() }
            .let { result ->
                when (result) {
                    is Result.Success -> result.data
                    is Result.Error -> null
                }
            }
    }
}

class LoginUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): LoginData? {
        return repository
            .registerUser(AuthRequest(null, email, password))
            .onError {
                throw IllegalArgumentException(message = "Неверные данные")
            }
            .map { it.toDomain() }
            .let { result ->
                when (result) {
                    is Result.Success -> result.data
                    is Result.Error -> null
                }
            }
    }
}

class GetUserByIdUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String): User? {
        return repository.getUserFromId(userId)
    }
}

class GetUserByEmailUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String): User? {
        require(email.contains("@")) { "Неверный формат email" }
        return repository.getUserFromEmail(email)
    }
}

class UpdateUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): User {
        require(user.username.isNotBlank()) { "Имя пользователя не может быть пустым" }
        require(user.email.contains("@")) { "Неверный формат email" }
        return repository.updateUser(user)
    }
}
