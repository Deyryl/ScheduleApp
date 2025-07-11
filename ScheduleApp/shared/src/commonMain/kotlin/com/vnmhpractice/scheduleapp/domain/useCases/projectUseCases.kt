package com.vnmhpractice.scheduleapp.domain.useCases

import com.vnmhpractice.scheduleapp.domain.domainModels.Project
import com.vnmhpractice.scheduleapp.domain.domainModels.User
import com.vnmhpractice.scheduleapp.domain.repositories.ProjectRepository


class GetUserProjects(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(userId: String): List<Project>? {
        return repository.getUserProjects(userId)
    }
}

class GetProjectOwner(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(projectId: String): User? {
        return repository.getProjectOwner(projectId)
    }
}

class GetProjectModerators(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(projectId: String): List<User>? {
        return repository.getProjectModerators(projectId)
    }
}

class GetProjectMembers(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(projectId: String): List<User>? {
        return repository.getProjectMembers(projectId)
    }
}

class UpdateProject(
    private val repository: ProjectRepository
) {
    suspend operator fun invoke(project: Project): Project? {
        return repository.updateProject(project)
    }
}