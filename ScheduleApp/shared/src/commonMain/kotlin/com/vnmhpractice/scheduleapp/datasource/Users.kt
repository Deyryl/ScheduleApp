package com.vnmhpractice.scheduleapp.datasource

import com.vnmhpractice.scheduleapp.models.User

val users: List<User> = listOf(
    User(
        userID = "0",
        hashedPassword = "",
        username = "Nikitos",
        email = "example@gmail.com",
        imageURL = null,
        projects = projects
    ),
    User(
        userID = "1",
        hashedPassword = "",
        username = "Nikitos",
        email = "example@gmail.com",
        imageURL = null,
        projects = projects
    )
)