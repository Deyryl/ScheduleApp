package com.vnmhpractice.scheduleapp.data.dtoClasses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class Task(
    val taskId: String,
    val title: String,
    val description: String?,
    val startTime: LocalDateTime?,
    val endTime: LocalDateTime?,
    val taskStatus: TaskStatus,
    val tags: List<String>?
)

enum class TaskStatus{
    IN_PROGRESS, COMPLETED
}
