package com.vnmhpractice.scheduleapp.android.data.datasource

import com.vnmhpractice.scheduleapp.android.data.model.Tag
import com.vnmhpractice.scheduleapp.android.data.model.Task
import com.vnmhpractice.scheduleapp.android.data.model.User
import com.vnmhpractice.scheduleapp.android.data.model.Project

// Данные для примера

val user1 = User(username = "alice", email = "alice@example.com", image = null)

val user2 = User(username = "bob", email = "bob@example.com", image = 1)
val user3 = User(username = "charlie", email = "charlie@example.com", image = 2)
val tag1 = Tag(title = "Urgent", description = "Requires immediate attention", type = "priority")

val tag2 = Tag(title = "Design", description = "Design-related tasks", type = "category")
val tag3 = Tag(title = "Backend", description = null, type = "category")
val task1 = Task(
    title = "Create wireframes",
    description = "Sketch main screen and detail view",
    startTime = "2025-07-10T09:00",
    endTime = "2025-07-10T12:00"
)

val task2 = Task(
    title = "Set up backend",
    description = "Initialize Spring Boot project and configure database",
    startTime = "2025-07-11T10:00",
    endTime = "2025-07-11T16:00"
)

val task3 = Task(
    title = "Fix login bug",
    description = null,
    startTime = null,
    endTime = null
)

val project1 = Project(
    id = "0",
    title = "Design Prototype",
    members = listOf(user1, user2),
    owner = user1,
    moderators = listOf(user2),
    tasks = listOf(task1, task3),
    tags = listOf(tag1, tag2)
)

val project2 = Project(
    id = "1",
    title = "Backend Implementation",
    members = listOf(user2, user3),
    owner = user3,
    moderators = listOf(user2),
    tasks = listOf(task2),
    tags = listOf(tag3)
)

val project3 = Project(
    id = "2",
    title = "Empty Project",
    members = listOf(user1),
    owner = user1,
    moderators = listOf(user1),
    tasks = null,
    tags = null
)

val users = listOf(user1, user2, user3)

val projects = listOf(project1, project2, project3)
