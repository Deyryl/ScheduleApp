package com.scheduleAppOrg.database.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Users")
data class User (
    @Id val userID: UUID = UUID.randomUUID(),
    val hashedPassword: String,
    val username: String,
    val email: String,
    val imageURL: String?,
    val projectsID: List<Int>
)

@Entity
@Table(name = "Projects")
data class Project(
    @Id val projectID: UUID = UUID.randomUUID(),
    val title: String,
    val ownerID: User,
    val moderators: List<User>,
    val members: List<User>,
    val imageURL: String?,
    val tasks: List<Task>,
    val tags: List<Tag>
)

@Entity
@Table(name = "Tasks")
data class Task(
    @Id val taskID: UUID = UUID.randomUUID(),
    val title: String,
    @Column(name = "taskDescription") val description: String,
    val startTime: Date?,
    val endTime: Date?,
    val taskStatus: String,
    val tags: List<Tag>
)

@Entity
@Table(name = "Tags")
data class Tag(
    @Id val tagID: UUID = UUID.randomUUID(),
    val title: String,
    @Column(name = "tagDescription") val description: User,
    val color: Long
)