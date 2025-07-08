package com.scheduleapp.database.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "Projects")
data class ProjectEntity(
    @Id val projectID: UUID = UUID.randomUUID(),
    val title: String,
    val ownerID: UserEntity,
    val moderators: List<UserEntity>,
    val members: List<UserEntity>,
    val imageURL: String?,
    val tasks: List<TaskEntity>,
    val tags: List<TagEntity>
)