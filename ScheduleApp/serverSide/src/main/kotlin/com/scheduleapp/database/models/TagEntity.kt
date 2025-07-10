//package com.scheduleapp.database.models
//
//import jakarta.persistence.*
//import java.util.*
//
//@Entity
//@Table(name = "Tags")
//data class TagEntity(
//    @Id @Column(name = "TagID")
//    val id: String = UUID.randomUUID().toString(),
//
//    val title: String = "Новая заметка",
//
//    @Column(name = "tagDescription")
//    val description: String? = null,
//
//    val color: Long = 0xFFFFF,
//
//    @ManyToOne
//    @JoinColumn(name = "ProjectID", nullable = false)
//    val project: ProjectEntity
//
//)