package com.vnmhpractice.scheduleapp.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskID: String,
    val title: String,
    val description: String,
    val startTime: Instant?,
    val endTime: Instant?,
    val taskStatus: String,
    val tags: List<Tag>
)
