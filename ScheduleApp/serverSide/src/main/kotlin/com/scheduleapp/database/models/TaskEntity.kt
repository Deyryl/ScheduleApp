package com.scheduleapp.database.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.datetime.LocalDateTime
import java.util.*

@Entity
@Table(name = "Tasks")
data class TaskEntity(
    @Id val taskID: UUID = UUID.randomUUID(),
    val title: String,
    @Column(name = "taskDescription") val description: String,
    val startTime: LocalDateTime?,
    val endTime: LocalDateTime?,
    val taskStatus: String,
    val tags: List<TagEntity>
)