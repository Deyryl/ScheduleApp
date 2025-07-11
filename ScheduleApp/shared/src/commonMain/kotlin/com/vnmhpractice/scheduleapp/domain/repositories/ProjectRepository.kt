package com.vnmhpractice.scheduleapp.domain.repositories

import com.vnmhpractice.scheduleapp.domain.domainModels.Project
import com.vnmhpractice.scheduleapp.domain.domainModels.User

interface ProjectRepository {
    suspend fun getUserProjects(userId: String): List<Project>?
    suspend fun getProjectOwner(projectId: String): User?
    suspend fun getProjectModerators(projectId: String): List<User>?
    suspend fun getProjectMembers(projectId: String): List<User>?
    suspend fun updateProject(project: Project): Project?

    //get tasks & tags
}