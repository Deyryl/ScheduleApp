package com.vnmhpractice.scheduleapp.android.data.model

data class Tag(
    val id: String,
    val title: String,
    val description: String?,
    val color: Long = 0xFF00FFAA
)
