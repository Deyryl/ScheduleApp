package com.scheduleapp.database.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.stereotype.Indexed
import java.time.LocalDateTime

@Entity
@Table(name = "RefreshTokens")
data class RefreshToken(
    @Id val userId: String,
    val expiresAt: LocalDateTime,
    val hashedToken: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
