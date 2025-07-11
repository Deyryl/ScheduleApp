package com.vnmhpractice.scheduleapp.domain.repositories

import com.vnmhpractice.scheduleapp.domain.domainModels.User

interface UserRepository {
    suspend fun getUserFromId(userId: String): User?
    suspend fun getUserFromEmail(email: String): User?
    suspend fun updateUser(user: User): User
}