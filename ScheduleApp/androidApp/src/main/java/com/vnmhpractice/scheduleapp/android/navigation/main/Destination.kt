package com.vnmhpractice.scheduleapp.android.navigation.main

enum class MainDestination(title: String) {
    Search(title = "Поиск"),
    Schedule(title = "Мои расписания"),
    Calendar(title = "Календарь"),
    Menu(title = "Меню")
}

enum class ScheduleDestination {
    Project,
    ProjectDetails,
    CreateTask,
    CreateTag,

}

enum class MenuDestination(val title: String) {
    Account("Аккаунт"),
    Information("Информация")
}