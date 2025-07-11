package com.vnmhpractice.scheduleapp.domain.useCases

import com.vnmhpractice.scheduleapp.domain.domainModels.User
import com.vnmhpractice.scheduleapp.domain.repositories.UserRepository

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
