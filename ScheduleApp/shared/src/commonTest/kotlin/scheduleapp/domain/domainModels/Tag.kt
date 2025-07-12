package com.vnmhpractice.scheduleapp.domain.domainModels

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val tagID: String,
    val title: String,
    val description: String,
    val color: Long
)
