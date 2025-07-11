package com.vnmhpractice.scheduleapp.android.data.model

data class Task(
    val title: String,
    val description: String?,
    val startTime: String?,
    val endTime: String?,
    val type: String,
    val tags: MutableList<Tag> = mutableListOf()
)