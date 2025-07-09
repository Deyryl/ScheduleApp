package com.vnmhpractice.scheduleapp.android.datasource

import com.vnmhpractice.scheduleapp.android.datasource.Project

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
    title = "Design Prototype",
    members = listOf(user1, user2),
    owner = user1,
    moderators = user2,
    tasks = listOf(task1, task3),
    tags = listOf(tag1, tag2)
)

val project2 = Project(
    title = "Backend Implementation",
    members = listOf(user2, user3),
    owner = user3,
    moderators = user2,
    tasks = listOf(task2),
    tags = listOf(tag3)
)

val project3 = Project(
    title = "Empty Project",
    members = listOf(user1),
    owner = user1,
    moderators = user1,
    tasks = null,
    tags = null
)

val users = listOf(user1, user2, user3)

val projects = listOf(project1, project2, project3)
