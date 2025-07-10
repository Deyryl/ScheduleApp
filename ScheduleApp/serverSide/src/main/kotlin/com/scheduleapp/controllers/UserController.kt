package com.scheduleapp.controllers

import com.scheduleapp.database.models.ProjectEntity
import com.scheduleapp.database.models.UserEntity
import com.scheduleapp.database.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository) {

    data class UserRequest(
        val email: String
    )

    data class UserResponse(
        val username: String,
        val email: String,
        val imageURL: String?,
        val projectsIds: List<String>?
    )

    @GetMapping
    fun getUserFromEmail(
        @RequestParam("email") email: String
    ): UserEntity? {
        return repository.findByEmail(email)
    }

    @GetMapping("project/{projectId}/members")
    fun getProjectMembers() {}
//    @PostMapping
//    fun addUser(body: UserRequest): UserResponse {
//        val user = repository.save(
//            UserEntity(
//                //userId = body.userID?.let
//                username = body.username,
//                email = body.email,
//                imageURL = body.imageURL,
//                projectsID = body.projectsID.toList(),
//                hashedPassword = body.hashedPassword
//            )
//        )
//    }
}