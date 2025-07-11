package com.scheduleapp.database.repository

import com.scheduleapp.database.models.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository: JpaRepository<ProjectEntity, String> {
    fun findByOwnerId(id: String): List<ProjectEntity>
}
