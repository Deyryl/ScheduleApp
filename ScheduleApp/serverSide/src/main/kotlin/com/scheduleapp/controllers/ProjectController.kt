package com.scheduleapp.controllers

import com.scheduleapp.database.models.ProjectEntity
import com.scheduleapp.database.models.UserEntity
import com.scheduleapp.database.repository.ProjectRepository
import com.scheduleapp.database.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/projects")
class ProjectController(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository
) {
    data class ProjectRequest(
        val id: String? = null,
        val title: String,
        val moderatorIds: List<String>,
        val memberIds: List<String>,
        val imageURL: String? = null,
    )

    data class ProjectResponse(
        val id: String,
        val title: String,
        val ownerId: String,
        val moderatorIds: List<String>,
        val memberIds: List<String>,
        val imageURL: String? = null,
    )

    @PostMapping
    fun save(@RequestBody body: ProjectRequest): ProjectResponse {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        val moderatorIds: List<String> = body.moderatorIds
        val memberIds: List<String> = body.memberIds

        val owner: String = userRepository.findByIdOrNull(ownerId)?.id ?: "testId" //УБРАТЬ !!
        val moderators: List<UserEntity> = userRepository.findAllById(moderatorIds)
        val members: List<UserEntity> = userRepository.findAllById(memberIds)


        val savedProject = projectRepository.save(ProjectEntity(
            id = body.id ?: UUID.randomUUID().toString(),
            title = body.title,
            ownerId = owner,
            moderators = moderators,
            members = members,
            imageURL = body.imageURL
        ))

        return savedProject.toResponse()
    }

    @GetMapping
    fun findByOwnerID(): List<ProjectResponse> {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        return projectRepository.findByOwnerId(ownerId).map { it.toResponse() }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String) {
        val project = projectRepository.findById(id).orElseThrow {
            IllegalArgumentException("Project not found")
        }
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        if (project.ownerId == ownerId) {
            projectRepository.deleteById(id)
        }
    }

    private fun ProjectEntity.toResponse(): ProjectResponse {
        return ProjectResponse(
            id = id,
            title = title,
            ownerId = ownerId,
            moderatorIds = moderators.map { it.id },
            memberIds = members.map { it.id },
            imageURL = imageURL
        )
    }
}


