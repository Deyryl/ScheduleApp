package com.vnmhpractice.scheduleapp.android.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vnmhpractice.scheduleapp.android.ui.main.calendar.CalendarScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.MenuScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.ScheduleScreen
import com.vnmhpractice.scheduleapp.android.ui.main.search.SearchScreen

fun NavGraphBuilder.mainNavigation(navController: NavHostController) {
    navigation(startDestination = MainDestination.Schedule.name, route = "Main") {
        // Окно поиска и вложенные окна
        composable(route = MainDestination.Search.name) {
            SearchScreen()
        }

        // Окно расписаний и вложенные окна
        composable(route = MainDestination.Schedule.name) {
            ScheduleScreen()
        }
        composable(route = ScheduleDestination.Project.name) {

        }

        // Окно календаря и вложенные окна
        composable(route = MainDestination.Calendar.name) {
            CalendarScreen()
        }

        // Окно меню и вложенные окна
        composable(route = MainDestination.Menu.name) {
            MenuScreen()
        }
    }
}