package com.scheduleapp.database.models

import jakarta.persistence.*
import kotlinx.datetime.LocalDateTime
import java.util.*

@Entity
@Table(name = "Tasks")
data class TaskEntity(
    @Id
    val taskID: UUID = UUID.randomUUID(),

    val title: String,

    @Column(name = "taskDescription")
    val description: String? = null,

    val startTime: LocalDateTime? = null,
    val endTime: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    val taskStatus: TaskStatus = TaskStatus.IN_PROGRESS,

    @ManyToMany
    @JoinTable(
        name = "TaskTags",
        joinColumns = [JoinColumn(name = "TaskID")],
        inverseJoinColumns = [JoinColumn(name = "TagID")]
    )
    val tags: List<TagEntity> = listOf()
)

enum class TaskStatus {
    IN_PROGRESS, COMPLETED
}