package com.vnmhpractice.scheduleapp.android.datasource

data class Task(
    val title: String,
    val description: String?,
    val startTime: String?,
    val endTime: String?
)