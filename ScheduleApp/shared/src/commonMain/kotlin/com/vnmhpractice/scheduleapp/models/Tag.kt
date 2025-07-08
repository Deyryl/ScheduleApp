package com.vnmhpractice.scheduleapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val tagID: String,
    val title: String,
    val description: User,
    val color: Long
)
