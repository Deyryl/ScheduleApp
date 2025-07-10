//package com.scheduleapp.database.models
//
//import jakarta.persistence.*
//import java.time.LocalDateTime
//import java.util.*
//
//@Entity
//@Table(name = "Tasks")
//data class TaskEntity(
//    @Id @Column(name = "TaskID")
//    val id: String = UUID.randomUUID().toString(),
//
//    val title: String,
//
//    @Column(name = "taskDescription")
//    val description: String? = null,
//
//    val startTime: LocalDateTime? = null,
//    val endTime: LocalDateTime? = null,
//
//    @Enumerated(EnumType.STRING)
//    val taskStatus: TaskStatus = TaskStatus.IN_PROGRESS,
//
//    @ManyToOne
//    @JoinColumn(name = "ProjectID", nullable = false)
//    val project: ProjectEntity,
//
//    @ManyToMany
//    @JoinTable(
//        name = "TaskTags",
//        joinColumns = [JoinColumn(name = "TaskID")],
//        inverseJoinColumns = [JoinColumn(name = "TagID")]
//    )
//    val tags: List<TagEntity> = listOf()
//)
//
//enum class TaskStatus {
//    IN_PROGRESS, COMPLETED
//}