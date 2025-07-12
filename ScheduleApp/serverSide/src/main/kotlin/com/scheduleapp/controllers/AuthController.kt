package com.scheduleapp.controllers

import com.scheduleapp.database.repository.UserRepository
import com.scheduleapp.security.AuthService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val userRepository: UserRepository
) {
    data class AuthRequest(
        @field:NotBlank
        val username: String?,
        @field:Email(message = "Invalid email format.")
        val email: String,
        @field:Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}\$",
            message = "Weak password"
        )
        val password: String
    )

    data class AuthResponse(
        val tokenPair: AuthService.TokenPair,
        val username: String?,
        val email: String,
        val imageURL: String?,
        val projectIds: List<String>?
    )

    data class RefreshRequest(
        val refreshToken: String
    )

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody body: AuthRequest
    ): AuthResponse {
        val userEntity = authService.register(body.username!!, body.email, body.password)
        val tokenPair = authService.login(body.email, body.password)
        return AuthResponse(
            tokenPair = tokenPair,
            username = userEntity.username,
            email = userEntity.email,
            imageURL = userEntity.imageURL,
            projectIds = userEntity.projectIds
        )
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody body: AuthRequest
    ): AuthResponse {
        val tokenPair = authService.login(body.email, body.password)
        val userEntity = userRepository.findByEmail(body.email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        return AuthResponse(
            tokenPair = tokenPair,
            username = userEntity.username,
            email = userEntity.email,
            imageURL = userEntity.imageURL,
            projectIds = userEntity.projectIds
        )
    }

    @PostMapping("/refresh")
    fun refresh(
        @Valid @RequestBody body: RefreshRequest
    ): AuthService.TokenPair {
        return authService.refresh(body.refreshToken)
    }
}