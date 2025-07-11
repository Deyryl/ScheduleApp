package com.vnmhpractice.scheduleapp.domain.domainModels

data class Project(
    val projectId: String,
    val title: String,
    val ownerId: String,
    val moderatorIds: List<String>,
    val memberIds: List<String>,
    val imageURL: String?,
//    val taskIds: List<String>,
//    val tagIds: List<String>
)