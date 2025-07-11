package com.vnmhpractice.scheduleapp.android.data.datasource

import com.vnmhpractice.scheduleapp.android.data.model.Tag
import com.vnmhpractice.scheduleapp.android.data.model.Task
import com.vnmhpractice.scheduleapp.android.data.model.User
import com.vnmhpractice.scheduleapp.android.data.model.Project

// Данные для примера

val user1 = User(username = "alice", email = "alice@example.com")

val user2 = User(username = "bob", email = "bob@example.com")
val user3 = User(username = "charlie", email = "charlie@example.com")
val tag1 = Tag(id = "0", title = "Urgent", description = "Requires immediate attention")

val tag2 = Tag(id = "1", title = "Design", description = "Design-related tasks")
val tag3 = Tag(id = "2", title = "Backend", description = null)
val task1 = Task(
    title = "Create wireframes",
    description = "Sketch main screen and detail view",
    startTime = "2025-07-10T09:00",
    endTime = "2025-07-10T12:00",
    type = "COMPLETED"
)

val task2 = Task(
    title = "Set up backend",
    description = "Initialize Spring Boot project and configure database",
    startTime = "2025-07-11T10:00",
    endTime = "2025-07-11T16:00",
    type = "IN_PROGRESS"
)

val task3 = Task(
    title = "Fix login bug",
    description = null,
    startTime = null,
    endTime = null,
    type = "IN_PROGRESS"
)

val project1 = Project(
    id = "0",
    title = "Design Prototype",
    members = mutableListOf(user1, user2),
    owner = user1,
    moderators = mutableListOf(user2),
    tasks = mutableListOf(task1, task3),
    tags = mutableListOf(tag1, tag2)
)

val project2 = Project(
    id = "1",
    title = "Backend Implementation",
    members = mutableListOf(user2, user3),
    owner = user3,
    moderators = mutableListOf(user2),
    tasks = mutableListOf(task2),
    tags = mutableListOf(tag3),
    isPinned = true
)

val project3 = Project(
    id = "2",
    title = "Empty Project",
    members = mutableListOf(user1),
    owner = user1,
    moderators = mutableListOf(user1)
)

val users = listOf(user1, user2, user3)

val projects = listOf(project1, project2, project3)
