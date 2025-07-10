package com.vnmhpractice.scheduleapp.models

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val username: String,
    val email: String,
    val imageURL: String?,
    val projects: List<Project>
)