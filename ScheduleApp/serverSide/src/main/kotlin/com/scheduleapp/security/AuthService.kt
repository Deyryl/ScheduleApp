package com.scheduleapp.security

import com.scheduleapp.database.models.RefreshToken
import com.scheduleapp.database.models.UserEntity
import com.scheduleapp.database.repository.UserRepository
import org.antlr.v4.runtime.Token
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*
import java.time.LocalDateTime
import com.scheduleapp.database.repository.RefreshTokenRepository
import jakarta.transaction.Transactional

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val hashEncoder: HashEncoder
) {
    data class TokenPair(
        val accessToken: String,
        val refreshToken: String
    )
    fun register(username: String, email: String, password: String): UserEntity {
        return userRepository.save(
            UserEntity(
                username = username,
                email = email,
                hashedPassword = hashEncoder.encode(password)
            )
        )
    }

    fun login(email: String, password: String): TokenPair {
        val user = userRepository.findByEmail(email)
            ?: throw BadCredentialsException("Invalid email.")

        if(!hashEncoder.matches(password, user.hashedPassword)) {
            throw BadCredentialsException("Invalid password.")
        }

        val newAccessToken = jwtService.generateAccessToken(user.id)
        val newRefreshToken = jwtService.generateRefreshToken(user.id)

        storeRefreshToken(user.id, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    @Transactional
    fun refresh(refreshToken: String): TokenPair {
        if(!jwtService.validateRefreshToken(refreshToken)) {
            throw IllegalArgumentException("Invalid refresh token.")
        }

        val userId = jwtService.getUserIdFromToken(refreshToken)
        val user = userRepository.findById(userId).orElseThrow() {
            IllegalArgumentException("Invalid refresh token.")
        }

        val hashed = hashToken(refreshToken)
        refreshTokenRepository.findByUserIdAndHashedToken(user.id, hashed)
            ?: throw IllegalArgumentException("Refresh token not recognized.")

        refreshTokenRepository.deleteByUserIdAndHashedToken(user.id, hashed)

        val newAccessToken = jwtService.generateAccessToken(userId)
        val newRefreshToken = jwtService.generateRefreshToken(userId)

        storeRefreshToken(user.id, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    private fun storeRefreshToken(userId: String, rawRefreshToken: String) {
        val hashed = hashEncoder.encode(rawRefreshToken)
        val expiryMs = jwtService.refreshTokenValidityMs
        val expiresAt = LocalDateTime.now().plusNanos(expiryMs*10*10*10*10*10*10)
        refreshTokenRepository.save(
            RefreshToken(
                userId = userId,
                expiresAt = expiresAt,
                hashedToken = hashed
            )
        )
    }

    private fun hashToken(token: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)
    }
}