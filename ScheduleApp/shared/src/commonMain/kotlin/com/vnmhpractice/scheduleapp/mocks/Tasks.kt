package com.vnmhpractice.scheduleapp.mocks

import com.vnmhpractice.scheduleapp.models.Task

object Tasks {
    val data = listOf(
        Task(
            taskID = "1",
            title = "Сделать дизайн логина",
            description = "Создать мокап экрана входа",
            startTime = null,
            endTime = null,
            taskStatus = "IN_PROGRESS",
            tags = listOf(Tags.data[2])
        ),
        Task(
            taskID = "2",
            title = "Настроить сервер",
            description = "Поднять Spring Boot + PostgreSQL",
            startTime = null,
            endTime = null,
            taskStatus = "COMPLETED",
            tags = listOf(Tags.data[0], Tags.data[1])
        ),
        Task(
            taskID = "3",
            title = "Написать документацию",
            description = "Минимум по одной странице на каждый модуль",
            startTime = null,
            endTime = null,
            taskStatus = "COMPLETED",
            tags = listOf(Tags.data[1])
        ),
        Task(
            taskID = "4",
            title = "Тестировать авторизацию",
            description = "Проверить вход, регистрацию, выход",
            startTime = null,
            endTime = null,
            taskStatus = "IN_PROGRESS",
            tags = emptyList()
        )
    )
}