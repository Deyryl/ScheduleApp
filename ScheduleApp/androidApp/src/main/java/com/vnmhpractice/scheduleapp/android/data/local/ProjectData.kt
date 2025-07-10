package com.vnmhpractice.scheduleapp.android.data.local

import com.vnmhpractice.scheduleapp.android.data.model.Project
import com.vnmhpractice.scheduleapp.android.data.datasource.projects

object ProjectData {
    private val listOfProjects = mutableListOf<Project>()

    fun getAllProjects(): List<Project> = listOfProjects.toList()

    fun getProjectById(id: String): Project {
        return listOfProjects.find { it.id == id } ?: Project()
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

    fun initTestData() {
        if (listOfProjects.isEmpty()) {
            listOfProjects.addAll(projects)
        }
    }
}