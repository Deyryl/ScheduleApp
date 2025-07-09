package com.scheduleapp.controllers

import com.scheduleapp.database.models.ProjectEntity
import com.scheduleapp.database.models.TagEntity
import com.scheduleapp.database.models.TaskEntity
import com.scheduleapp.database.models.UserEntity
import com.scheduleapp.database.repository.ProjectRepository
import jakarta.persistence.Id
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/projects")
class ProjectController(private val repository: ProjectRepository) {

    data class ProjectRequest(
        val projectID: String?,
        val title: String,
        val ownerID: String,
        val moderators: List<UserEntity>,
        val members: List<UserEntity>,
        val imageURL: String?,
    )

    data class ProjectResponse(
        val projectID: String,
        val title: String,
        val ownerID: String,
        val moderators: List<UserEntity>,
        val members: List<UserEntity>,
        val imageURL: String?,
    )

    @PostMapping
    fun save(body: ProjectRequest): ProjectResponse {
        val project = repository.save(
            ProjectEntity(
                projectID = body.projectID?.let {UUID.fromString(body.projectID)} ?: UUID.randomUUID(),
                title = body.title,
                ownerID = UUID.fromString(body.ownerID),
                moderators = body.moderators.toList(),
                members = body.members.toList(),
                imageURL = body.imageURL
            )
        )

        return project.toResponse()
    }

    @GetMapping
    fun findByOwnerID(
        @RequestParam(required = true) ownerID: String
    ): List<ProjectResponse> {
        return repository.findByOwnerID(UUID.fromString(ownerID)).map {
            it.toResponse()
        }
    }

    private fun ProjectEntity.toResponse(): ProjectResponse {
        return ProjectResponse(
            projectID = projectID.toString(),
            title = title,
            ownerID = ownerID.toString(),
            moderators = moderators.toList(),
            members = members.toList(),
            imageURL = imageURL
        )
    }
}

