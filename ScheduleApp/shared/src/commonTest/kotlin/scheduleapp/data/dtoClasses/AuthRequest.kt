package com.vnmhpractice.scheduleapp.data.dtoClasses

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest (
    val username: String?,
    val email: String,
    val password: String
)
