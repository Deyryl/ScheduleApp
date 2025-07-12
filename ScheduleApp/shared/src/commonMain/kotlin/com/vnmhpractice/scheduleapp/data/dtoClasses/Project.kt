package com.vnmhpractice.scheduleapp.data.dtoClasses

import kotlinx.serialization.Serializable

@Serializable
data class ProjectDTO(
    val projectId: String,
    val title: String,
    val ownerId: String,
    val moderatorIds: List<String>?,
    val memberIds: List<String>?,
    val imageURL: String?,
//    val tasks: List<Task>,
//    val tags: List<Tag>
)