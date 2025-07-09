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

    val imageURL: String?,

    @ManyToMany(mappedBy = "members")
    val projects: List<ProjectEntity> = listOf(),

    @ManyToMany(mappedBy = "moderators")
    val moderatedProjects: List<ProjectEntity> = listOf()
)

