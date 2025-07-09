package com.vnmhpractice.scheduleapp.datasource

import com.vnmhpractice.scheduleapp.models.Project

val projects = listOf(
    Project(
        projectID = "101",
        title = "Платформа для учебных проектов",
        ownerID = users.first(),
        moderators = listOf(users.first()),
        members = users,
        imageURL = null,
        tasks = tasks.subList(0, 2),
        tags = listOf(tags[0], tags[2])
    ),
    Project(
        projectID = "102",
        title = "Приложение для спорта",
        ownerID = users[1],
        moderators = emptyList(),
        members = listOf(users[0], users[1]),
        imageURL = null,
        tasks = tasks.subList(2, 4),
        tags = listOf(tags[1])
    )
)