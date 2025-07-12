package com.vnmhpractice.scheduleapp.android.data.local

import com.vnmhpractice.scheduleapp.android.data.datasource.projects
import com.vnmhpractice.scheduleapp.android.data.model.Project
import com.vnmhpractice.scheduleapp.android.data.model.Task

object ProjectData {
    private val listOfProjects = mutableListOf<Project>()

    fun getAllProjects(): List<Project> = listOfProjects.toList()

    fun getProjectById(id: String): Project? {
        return listOfProjects.find { it.id == id }
    }

    fun getTaskById(projectId: String, taskId: String): Task? {
        return getProjectById(projectId)?.let { it.tasks.find { it.taskId == taskId } }
    }

    fun addProject(project: Project) {
        listOfProjects.add(project)
    }

    fun updateProject(project: Project) {
        val index = listOfProjects.indexOfFirst { it.id == project.id }
        if (index != -1) {
            listOfProjects[index] = project
        }
    }

    fun deleteProject(id: String) {
        listOfProjects.removeAll { it.id == id }
    }

    fun editTask(projectId: String, task: Task) {
        val project = getProjectById(projectId) ?: return
        project.copy(
            tasks = project.tasks.map { currentTask ->
                if (currentTask.taskId == task.taskId) task else currentTask
            }.toMutableList()
        )
        updateProject(project)
    }

    fun initTestData() {
        if (listOfProjects.isEmpty()) {
            listOfProjects.addAll(projects)
        }
    }
}