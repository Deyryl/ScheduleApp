package com.vnmhpractice.scheduleapp.android.data.model

data class Project(
    val id: String = "",
    val title: String,
    val members: List<User>,
    val image: Int? = null,
    val owner: User,
    val moderators: User,
    val tasks: List<Task>?,
    val tags: List<Tag>?,
    val isPinned: Boolean = false
)