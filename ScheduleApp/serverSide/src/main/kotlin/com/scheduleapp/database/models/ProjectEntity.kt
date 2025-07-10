package com.scheduleapp.database.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Projects")
data class ProjectEntity(
    @Id @Column(name = "ProjectID")
    val id: String = UUID.randomUUID().toString(),

    val title: String,
//    @ManyToOne
//    @JoinColumn(name = "OwnerID", nullable = false)
    //val owner: UserEntity,
    val ownerId: String = UUID.randomUUID().toString(),

    @ManyToMany
    @JoinTable(
        name = "ProjectModerators",
        joinColumns = [JoinColumn(name = "ProjectID")],
        inverseJoinColumns = [JoinColumn(name = "UserID")]
    )
    val moderators: List<UserEntity>? = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "ProjectMembers",
        joinColumns = [JoinColumn(name = "ProjectID")],
        inverseJoinColumns = [JoinColumn(name = "UserID")]
    )
    val members: List<UserEntity>? = emptyList(),

    val imageURL: String?,

//    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
//    val tasks: List<TaskEntity> = listOf(),
//
//    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
//    val tags: List<TagEntity> = listOf(),
)