package com.vnmhpractice.scheduleapp.mocks

import com.vnmhpractice.scheduleapp.models.User

object Users {
    val data: List<User> = listOf(
        User(
            userID = "0",
            hashedPassword = "",
            username = "Nikitos",
            email = "example@gmail.com",
            imageURL = null,
            projects = Projects.data
        ),
        User(
            userID = "1",
            hashedPassword = "",
            username = "Nikitos",
            email = "example@gmail.com",
            imageURL = null,
            projects = Projects.data
        )
    )
}