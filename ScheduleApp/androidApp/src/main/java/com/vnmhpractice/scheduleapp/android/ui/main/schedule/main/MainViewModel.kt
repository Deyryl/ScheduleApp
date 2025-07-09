package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.lifecycle.ViewModel
import com.vnmhpractice.scheduleapp.android.data.datasource.projects
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        // MARK: Code to complete
        // Замена на вызов с помощью API и др
        ProjectData.initTestData()
        _uiState.update {  it.copy(projects = ProjectData.getAllProjects()) }
    }
}