package com.vnmhpractice.scheduleapp.data.dtoClasses

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val projectID: String,
    val title: String,
    val owner: User,
    val moderators: List<User>,
    val members: List<User>,
    val imageURL: String?,
    val tasks: List<Task>,
    val tags: List<Tag>
)