package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vnmhpractice.scheduleapp.android.datasource.*

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadSchedules()
    }

    private fun loadSchedules() {
        // MARK: Code to complete
        // Замена на вызов с помощью API и др
        _uiState.value = MainUiState(projects = projects)
    }
}