package com.scheduleapp.controllers

import com.scheduleapp.database.model.User
import com.scheduleapp.database.repository.UserRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository) {

    data class UserRequest(
        val userID: Int?,
        val username: String,
        val email: String,
        val imageURL: String,
        val projectsID: List<Int>,
        val hashedPassword: String
    )

    data class UserResponse(
        val userID: Int,
        val username: String,
        val email: String,
        val imageURL: String,
        val projectsID: List<Int>
    )

    @PostMapping
    fun addUser(body: UserRequest): UserResponse {
        val user = repository.save(
            User(
                userId = body.userID?.let
                username = body.username,
                email = body.email,
                imageURL = body.imageURL,
                projectsID = body.projectsID.toList(),
                hashedPassword = body.hashedPassword
            )
        )
    }
}