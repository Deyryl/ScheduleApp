package com.vnmhpractice.scheduleapp.networking

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val username: String,
    val email: String,
    val imageURL: String,
    val projectsIds: List<String>,
    val hashedPassword: String
)

@Serializable
data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val imageURL: String,
    val projectsIds: List<Int>
)

@Serializable
data class ProjectRequest(
    val id: String?,
    val title: String,
    val moderatorIds: List<String>?,
    val memberIds: List<String>?,
    val imageURL: String?,
)

@Serializable
data class ProjectResponse(
    val id: String,
    val title: String,
    val ownerId: String,
    val moderatorIds: List<String>?,
    val memberIds: List<String>?,
    val imageURL: String?,
)

@Serializable
data class AuthRequest(
    val username: String? = null,
    val email: String,
    val password: String
)

@Serializable
data class RefreshRequest(
    val refreshToken: String
)

@Serializable
data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)
