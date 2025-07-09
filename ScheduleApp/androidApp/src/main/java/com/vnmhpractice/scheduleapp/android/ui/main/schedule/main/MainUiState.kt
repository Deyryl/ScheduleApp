package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import com.vnmhpractice.scheduleapp.android.datasource.ScheduleItem

data class MainUiState(
    val schedules: List<ScheduleItem> = emptyList()
)
