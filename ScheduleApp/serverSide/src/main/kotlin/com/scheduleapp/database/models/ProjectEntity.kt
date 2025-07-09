package com.scheduleapp.database.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Projects")
data class ProjectEntity(
    @Id
    val projectID: UUID = UUID.randomUUID(),

    val title: String,

    @ManyToOne
    @JoinColumn(name = "OwnerID", nullable = false)
    val owner: UserEntity,

    @ManyToMany
    @JoinTable(
        name = "ProjectModerators",
        joinColumns = [JoinColumn(name = "ProjectID")],
        inverseJoinColumns = [JoinColumn(name = "UserID")]
    )
    val moderators: List<UserEntity> = listOf(),

    @ManyToMany
    @JoinTable(
        name = "ProjectMembers",
        joinColumns = [JoinColumn(name = "ProjectID")],
        inverseJoinColumns = [JoinColumn(name = "UserID")]
    )
    val members: List<UserEntity> = listOf(),

    val imageURL: String?,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tasks: List<TaskEntity> = listOf(),

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tags: List<TagEntity> = listOf(),
)