package com.vnmhpractice.scheduleapp.android.ui.auth.start

import androidx.lifecycle.ViewModel
import com.vnmhpractice.scheduleapp.android.ui.screens.auth.start.StartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StartUiState())
    val uiState: StateFlow<StartUiState> = _uiState.asStateFlow()
}