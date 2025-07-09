package com.scheduleapp.mapping

import com.scheduleapp.database.models.*
import com.vnmhpractice.scheduleapp.models.*

fun UserEntity.toCommon(): User = User(
    userID = userID.toString(),
    hashedPassword = hashedPassword,
    username = username,
    email = email,
    imageURL = imageURL,
    projects = projects.map {it.toCommon()}
)

fun ProjectEntity.toCommon(): Project = Project (
    projectID = projectID.toString(),
    title = title,
    ownerID = ownerID.toCommon(),
    moderators = moderators.map {it.toCommon()},
    members = members.map {it.toCommon()},
    imageURL = imageURL,
    tasks = tasks.map {it.toCommon()},
    tags = tags.map {it.toCommon()}
)

fun TaskEntity.toCommon(): Task = Task (
    taskID = taskID.toString(),
    title = title,
    description = description,
    startTime = startTime,
    endTime = endTime,
    taskStatus = taskStatus,
    tags = tags.map {it.toCommon()}
)

fun TagEntity.toCommon(): Tag = Tag (
    tagID = tagID.toString(),
    title = title,
    description = description,
    color = color
)