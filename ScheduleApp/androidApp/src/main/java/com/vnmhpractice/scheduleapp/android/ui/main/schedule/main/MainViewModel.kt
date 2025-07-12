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
    }

    fun getProjectById(id: String): Project? {
        return ProjectData.getProjectById(id)
    }

    fun addNewProject(project: Project) {
        ProjectData.addProject(project.copy(owner = project.members.first()))
        loadProjects()
    }

    fun saveProjectChanges(project: Project) {
        ProjectData.updateProject(project)
        loadProjects()
    }

    fun pinProject(id: String) {
        val project = getProjectById(id) ?: return
        val editProject = project.copy(isPinned = !project.isPinned)
        saveProjectChanges(editProject)
    }

    private fun sortProjects(projects: List<Project>): List<Project> {
        return projects.sortedWith(
            compareBy<Project> { !it.isPinned }
                .thenBy { it.title }
        )
    }

    private fun loadProjects() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            ProjectData.initTestData()
            val projects = ProjectData.getAllProjects()
            _uiState.update {
                it.copy(
                    projects = sortProjects(projects).toMutableList(),
                    isLoading = false
                )
            }
        }
    }
}