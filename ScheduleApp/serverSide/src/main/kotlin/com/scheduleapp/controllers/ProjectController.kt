package com.scheduleapp.controllers

import com.scheduleapp.database.models.ProjectEntity
import com.scheduleapp.database.models.UserEntity
import com.scheduleapp.database.repository.ProjectRepository
import com.scheduleapp.database.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/projects")
class ProjectController(
    private val repository: ProjectRepository,
    private val userRepository: UserRepository
) {
    data class ProjectRequest(
        val projectID: String? = null,
        val title: String,
        val ownerID: String,
        val moderatorIDs: List<String>,
        val memberIDs: List<String>,
        val imageURL: String? = null,
    )

    data class ProjectResponse(
        val projectID: String,
        val title: String,
        val ownerID: String,
        val moderatorIDs: List<String>,
        val memberIDs: List<String>,
        val imageURL: String? = null,
    )

    @PostMapping
    fun save(@RequestBody body: ProjectRequest): ProjectResponse {

        val ownerID: UUID = UUID.fromString(body.ownerID)
        val moderatorIDs: List<UUID> = body.moderatorIDs.map { UUID.fromString(it) }
        val memberIDs: List<UUID> = body.memberIDs.map { UUID.fromString(it) }

        val owner: UserEntity = userRepository.findByIdOrNull(ownerID)!! //УБРАТЬ !!
        val moderators: List<UserEntity> = userRepository.findAllById(moderatorIDs)
        val members: List<UserEntity> = userRepository.findAllById(memberIDs)

        val savedProject = repository.save(ProjectEntity(
            projectID = body.projectID?.let(UUID::fromString) ?: UUID.randomUUID(),
            title = body.title,
            owner = owner,
            moderators = moderators,
            members = members,
            imageURL = body.imageURL
        ))

        return savedProject.toResponse()
    }

    @GetMapping
    fun findByOwnerID(@RequestParam ownerID: String): List<ProjectResponse> {
        val ownerUuid = UUID.fromString(ownerID)
        return repository.findByOwnerID(ownerUuid).map { it.toResponse() }
    }

    private fun ProjectEntity.toResponse(): ProjectResponse {
        return ProjectResponse(
            projectID = projectID.toString(),
            title = title,
            ownerID = owner.userID.toString(),
            moderatorIDs = moderators.map { it.userID.toString() },
            memberIDs = members.map { it.userID.toString() },
            imageURL = imageURL
        )
    }
}


