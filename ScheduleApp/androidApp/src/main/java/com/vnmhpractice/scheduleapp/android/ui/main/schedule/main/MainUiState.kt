package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import com.vnmhpractice.scheduleapp.android.data.model.Project

data class MainUiState(
    val projects: List<Project> = emptyList()
)
