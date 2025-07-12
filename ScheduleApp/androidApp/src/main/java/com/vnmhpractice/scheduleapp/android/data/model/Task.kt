package com.vnmhpractice.scheduleapp.android.data.model

import kotlinx.datetime.LocalDateTime

enum class TaskType {
    IN_PROGRESS,
    COMPLETED
}

data class Task(
    val title: String,
    val description: String?,
    val startTime: LocalDateTime? = null,
    val endTime: LocalDateTime? = null,
    val type: TaskType,
    val tags: MutableList<Tag> = mutableListOf()
)