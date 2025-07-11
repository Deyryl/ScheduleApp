package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData
import com.vnmhpractice.scheduleapp.android.data.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUiState(
    val projects: MutableList<Project> = mutableListOf(),
    val isLoading: Boolean = false
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadProjects()
        sortProjects()
    }

    fun getProjectById(id: String): Project {
        return ProjectData.getProjectById(id)
    }

    fun saveProjectChanges(project: Project) {
        ProjectData.updateProject(project)
    }

    fun pinProject(id: String) {
        val project = getProjectById(id)
        val editProject = project.copy(isPinned = !project.isPinned)
        saveProjectChanges(editProject)
        sortProjects()
    }

    private fun sortProjects() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    projects = currentState.projects.sortedWith(
                        compareBy<Project> { !it.isPinned }
                            .thenBy { it.title }
                    ).toMutableList()
                )
            }
        }
    }

    private fun loadProjects() {
        // MARK: Code to complete
        // Замена на вызов с помощью API и др
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            ProjectData.initTestData()
            val projects = ProjectData.getAllProjects().toMutableList()
            _uiState.update { it.copy(projects = projects, isLoading = false) }
        }
    }
}