package com.vnmhpractice.scheduleapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val projectID: String,
    val title: String,
    val ownerID: User,
    val moderators: List<User>,
    val members: List<User>,
    val imageURL: String?,
    val tasks: List<Task>,
    val tags: List<Tag>
)