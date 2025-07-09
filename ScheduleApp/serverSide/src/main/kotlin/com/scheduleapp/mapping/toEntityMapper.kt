//package com.scheduleapp.mapping
//
//import com.scheduleapp.database.models.*
//import com.vnmhpractice.scheduleapp.models.*
//import java.util.UUID
//
//
//fun User.toEntity(): UserEntity = UserEntity(
//    userID = java.util.UUID.fromString(userID),
//    hashedPassword = hashedPassword,
//    username = username,
//    email = email,
//    imageURL = imageURL,
//    projects = projects.map { it.toEntity() }
//)
//
//fun Project.toEntity(): ProjectEntity = ProjectEntity(
//    projectID = java.util.UUID.fromString(projectID),
//    title = title,
//    ownerID = UUID.fromString(owner.userID),
//    moderators = moderators.map { it.toEntity() },
//    members = members.map { it.toEntity() },
//    imageURL = imageURL,
//    //tasks = tasks.map { it.toEntity() },
//    //tags = tags.map { it.toEntity() }
//)
//
//fun Task.toEntity(): TaskEntity = TaskEntity(
//    taskID = java.util.UUID.fromString(taskID),
//    title = title,
//    description = description,
//    startTime = startTime,
//    endTime = endTime,
//    taskStatus = taskStatus,
//    tags = tags.map { it.toEntity() }
//)
//
//fun Tag.toEntity(): TagEntity = TagEntity(
//    tagID = java.util.UUID.fromString(tagID),
//    title = title,
//    description = description,
//    color = color
//)
