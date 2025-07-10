package com.vnmhpractice.scheduleapp.mocks

import com.vnmhpractice.scheduleapp.models.Tag

object Tags {
    val data: List<Tag> = listOf(
        Tag(
            tagID = "1",
            title = "Главный",
            description = "Основная задача",
            color = 0xFFE53935
        ),
        Tag(
            tagID = "2",
            title = "Важно",
            description = "Высокий приоритет",
            color = 0xFFFFC107
        ),
        Tag(
            tagID = "3",
            title = "Дизайн",
            description = "Все по UI/UX",
            color = 0xFF1E88E5
        )
    )
}