package com.vnmhpractice.scheduleapp.android.navigation.main

import androidx.compose.ui.res.stringResource
import com.vnmhpractice.scheduleapp.android.R

enum class MainScreen(title: String) {
    Search(title = "Поиск"),
    Schedule(title = "Мои расписания"),
    Calendar(title = "Календарь"),
    Menu(title = "Меню")
}