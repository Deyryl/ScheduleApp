package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.annotation.DrawableRes

data class ScheduleItem(
    val id: Int,
    val title: String = "",
    @DrawableRes val imageRes: Int? = null,
    val isPinned: Boolean = false
)
