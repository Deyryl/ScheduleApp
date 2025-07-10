package com.vnmhpractice.scheduleapp.mocks

import com.vnmhpractice.scheduleapp.models.Project

object Projects {
    val data = listOf(
        Project(
            projectID = "101",
            title = "Платформа для учебных проектов",
            ownerID = Users.data.first(),
            moderators = listOf(Users.data.first()),
            members = Users.data,
            imageURL = null,
            tasks = Tasks.data.subList(0, 2),
            tags = listOf(Tags.data[0], Tags.data[2])
        ),
        Project(
            projectID = "102",
            title = "Приложение для спорта",
            ownerID = Users.data[1],
            moderators = emptyList(),
            members = listOf(Users.data[0], Users.data[1]),
            imageURL = null,
            tasks = Tasks.data.subList(2, 4),
            tags = listOf(Tags.data[1])
        )
    )
}