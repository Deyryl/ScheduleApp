package com.vnmhpractice.scheduleapp.android.data.model

data class Tag(
    val title: String,
    val description: String?,
    val type: String,
    val color: Long = 0xFF00FFAA
)
