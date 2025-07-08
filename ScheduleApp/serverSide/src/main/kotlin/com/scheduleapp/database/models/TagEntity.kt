package com.scheduleapp.database.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "Tags")
data class TagEntity(
    @Id val tagID: UUID = UUID.randomUUID(),
    val title: String,
    @Column(name = "tagDescription") val description: UserEntity,
    val color: Long
)