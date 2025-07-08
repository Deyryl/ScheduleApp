package com.scheduleAppOrg.database.repository

import com.scheduleAppOrg.database.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}
