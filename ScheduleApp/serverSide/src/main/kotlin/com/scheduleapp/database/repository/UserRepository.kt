package com.scheduleapp.database.repository

import com.scheduleapp.database.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Int> {
    fun findByEmail(email: String): UserEntity?
}
