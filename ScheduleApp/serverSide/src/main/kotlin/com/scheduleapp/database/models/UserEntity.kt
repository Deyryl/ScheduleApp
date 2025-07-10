package com.scheduleapp.database.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Users")
data class UserEntity (
    @Id @Column(name = "UserID")
    val id: String = UUID.randomUUID().toString(),

    val hashedPassword: String,

    val username: String,

    val email: String,

    val imageURL: String? = null,

    @ManyToMany(mappedBy = "members")
    val projects: List<ProjectEntity> = listOf(),

    @ManyToMany(mappedBy = "moderators")
    val moderatedProjects: List<ProjectEntity> = listOf()
)


//// ProjectRepository.kt
//package com.example.project
//
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.stereotype.Repository
//
//@Repository
//interface ProjectRepository : JpaRepository<ProjectEntity, String> {
//    fun findByIdAndOwnerId(id: String, ownerId: String): ProjectEntity?
//}
//
//
//// ProjectService.kt
//package com.example.project
//
//import org.springframework.security.access.AccessDeniedException
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.stereotype.Service
//
//@Service
//class ProjectService(
//    private val projectRepository: ProjectRepository
//) {
//    private fun currentUserId(): String {
//        val auth = SecurityContextHolder.getContext().authentication
//        return auth.name // предполагается, что name = userId
//    }
//
//    private fun checkAccess(project: ProjectEntity) {
//        val userId = currentUserId()
//        val isOwner = project.ownerId == userId
//        val isModerator = project.moderators?.any { it.id == userId } == true
//        if (!isOwner && !isModerator) {
//            throw AccessDeniedException("Access denied: no rights to view project users")
//        }
//    }
//
//    fun getMembers(projectId: String): List<UserEntity> {
//        val project = projectRepository.findById(projectId)
//            .orElseThrow { NoSuchElementException("Project not found") }
//        checkAccess(project)
//        return project.members ?: emptyList()
//    }
//
//    fun getModerators(projectId: String): List<UserEntity> {
//        val project = projectRepository.findById(projectId)
//            .orElseThrow { NoSuchElementException("Project not found") }
//        checkAccess(project)
//        return project.moderators ?: emptyList()
//    }
//}
//
//
//// ProjectController.kt
//package com.example.project
//
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/projects")
//class ProjectController(
//    private val projectService: ProjectService
//) {
//
//    @GetMapping("/{projectId}/members")
//    fun getProjectMembers(
//        @PathVariable projectId: String
//    ): ResponseEntity<List<UserEntity>> {
//        val members = projectService.getMembers(projectId)
//        return ResponseEntity.ok(members)
//    }
//
//    @GetMapping("/{projectId}/moderators")
//    fun getProjectModerators(
//        @PathVariable projectId: String
//    ): ResponseEntity<List<UserEntity>> {
//        val moderators = projectService.getModerators(projectId)
//        return ResponseEntity.ok(moderators)
//    }
//
//    // при необходимости: общее представление с ролью
//    @GetMapping("/{projectId}/users")
//    fun getAllProjectUsers(
//        @PathVariable projectId: String
//    ): ResponseEntity<List<ProjectUserDTO>> {
//        val project = projectService.getProjectWithAccessCheck(projectId)
//        val owner = listOf(UserRoleDTO(project.ownerId, "OWNER"))
//        val mods = project.moderators?.map { UserRoleDTO(it.id, "MODERATOR") } ?: emptyList()
//        val members = project.members?.map { UserRoleDTO(it.id, "MEMBER") } ?: emptyList()
//        val all = owner + mods + members
//        return ResponseEntity.ok(all)
//    }
//}
//
//// DTOs
//package com.example.project
//
//data class UserRoleDTO(
//    val userId: String,
//    val role: String
//)
//
//// Дополнение в сервисе:
//package com.example.project
//
//fun ProjectService.getProjectWithAccessCheck(projectId: String): ProjectEntity {
//    val project = projectRepository.findById(projectId)
//        .orElseThrow { NoSuchElementException("Project not found") }
//    checkAccess(project)
//    return project
//}


