package com.scheduleapp.database.repository

import com.scheduleapp.database.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<UserEntity, String> {
    fun findByEmail(email: String): UserEntity?
}
