package com.vnmhpractice.scheduleapp.models

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskID: String,
    val title: String,
    val description: String,
    val startTime: LocalDateTime?,
    val endTime: LocalDateTime?,
    val taskStatus: String,
    val tags: List<Tag>
)
