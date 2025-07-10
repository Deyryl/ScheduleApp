package com.vnmhpractice.scheduleapp.android.data.model

data class Project(
    val id: String = "",
    val title: String = "default",
    val members: List<User> = emptyList(),
    val image: Int? = null,
    val owner: User? = null,
    val moderators: List<User> = emptyList(),
    val tasks: List<Task>? = null,
    val tags: List<Tag>? = null,
    val isPinned: Boolean = false
)