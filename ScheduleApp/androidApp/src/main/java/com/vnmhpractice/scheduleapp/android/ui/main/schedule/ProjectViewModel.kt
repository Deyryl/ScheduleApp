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

    private val _editingTask = MutableStateFlow<Task?>(null)
    val editingTask: StateFlow<Task?> = _editingTask.asStateFlow()

    fun startEditingTask(task: Task) {
        _editingTask.value = task.copy()
    }

    fun updateEditingTask(
        title: String = _editingTask.value?.title ?: "",
        description: String? = _editingTask.value?.description,
        startTime: String? = _editingTask.value?.startTime,
        endTime: String? = _editingTask.value?.endTime,
        type: String = _editingTask.value?.type ?: "default"
    ) {
        _editingTask.update { current ->
            current?.copy(
                title = title,
                description = description,
                startTime = startTime,
                endTime = endTime,
                type = type
            )
        }
    }

    fun saveTaskChanges() {
        _editingTask.value?.let { updatedTask ->
            _uiState.update { currentState ->
                val updatedTasks = currentState.project.tasks.toMutableList().apply {
                    val index = indexOfFirst { it.title == updatedTask.title }
                    if (index != -1) {
                        set(index, updatedTask)
                    } else {
                        add(updatedTask)
                    }
                }
                currentState.copy(
                    project = currentState.project.copy(tasks = updatedTasks)
                )
            }
            _editingTask.value = null
        }
    }

    fun cancelEditing() {
        _editingTask.value = null
    }

    fun addTagToTask(tag: Tag) {
        _editingTask.update { current ->
            current?.copy(tags = (current.tags + tag).toMutableList())
        }
    }

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
