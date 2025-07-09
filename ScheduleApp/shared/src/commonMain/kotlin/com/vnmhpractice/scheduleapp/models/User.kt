package com.vnmhpractice.scheduleapp.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class User (
    val userID: String,
    val hashedPassword: String,
    val username: String,
    val email: String,
    val imageURL: String?,
    val projects: List<Project>
)