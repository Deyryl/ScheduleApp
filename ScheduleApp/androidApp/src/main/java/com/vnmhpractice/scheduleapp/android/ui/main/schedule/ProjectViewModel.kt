package com.vnmhpractice.scheduleapp.android.ui.main.schedule

import androidx.lifecycle.ViewModel
import com.vnmhpractice.scheduleapp.android.data.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProjectViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProjectUiState())
    val uiState: StateFlow<ProjectUiState> = _uiState.asStateFlow()

    fun updateProject(project: Project) {
        _uiState.update { it.copy(project = project) }
    }
}