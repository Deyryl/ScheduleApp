package com.vnmhpractice.scheduleapp.data.dtoClasses

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginDTO (
    val username: String,
    val email: String,
    val password: String,
    val imageURL: String?,
)