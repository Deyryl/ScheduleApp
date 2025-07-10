package com.vnmhpractice.scheduleapp.android.ui.main.schedule

import androidx.lifecycle.ViewModel
import com.vnmhpractice.scheduleapp.android.data.model.Project
import com.vnmhpractice.scheduleapp.android.data.model.Tag
import com.vnmhpractice.scheduleapp.android.data.model.Task
import com.vnmhpractice.scheduleapp.android.data.model.User
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

    fun updateProjectTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(
                project = currentState.project.copy(title = title)
            )
        }
    }

    fun addTask(task: Task) {
        _uiState.value.project.tasks.add(task)
    }

    fun removeTask(task: Task) {
        _uiState.value.project.tasks.remove(task)
    }

    fun addTag(tag: Tag) {
        _uiState.value.project.tags.add(tag)
    }

    fun removeTag(tag: Tag) {
        _uiState.value.project.tags.remove(tag)
    }

    fun addMember(member: User) {
        _uiState.value.project.members.add(member)
    }

    fun removeMember(member: User) {
        _uiState.value.project.members.remove(member)
    }

//    fun updateTaskTitle(task: Task, title: String) {
//        _uiState.value.project.tasks.
//    }

    fun updateTaskDescription(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                //project = currentState.project.copy(description = text)
            )
        }
    }

    private fun sortTasksByDate() {

    }
}
