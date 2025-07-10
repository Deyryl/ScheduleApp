package com.scheduleapp.database.repository

import com.scheduleapp.database.models.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

import java.util.UUID

interface ProjectRepository: JpaRepository<ProjectEntity, String> {
    fun findByUserId(id: String): List<ProjectEntity>
}
