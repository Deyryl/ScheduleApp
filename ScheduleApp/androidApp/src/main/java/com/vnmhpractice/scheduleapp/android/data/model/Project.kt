package com.vnmhpractice.scheduleapp.android.data.model

import android.net.Uri

data class Project(
    val id: String = "",
    val title: String = "default",
    val members: MutableList<User> = mutableListOf(),
    val image: Uri? = null,
    val owner: User? = null,
    val moderators: MutableList<User> = mutableListOf(),
    val tasks: MutableList<Task> = mutableListOf(),
    val tags: MutableList<Tag> = mutableListOf(),
    val isPinned: Boolean = false
)