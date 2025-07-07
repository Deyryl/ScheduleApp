package com.vnmhpractice.scheduleapp.android.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vnmhpractice.scheduleapp.android.ui.main.calendar.CalendarScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.MenuScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.MainScreen
import com.vnmhpractice.scheduleapp.android.ui.main.search.SearchScreen

fun NavGraphBuilder.mainNavigation(navController: NavHostController) {
    navigation(startDestination = MainScreen.Schedule.name, route = "Main") {
        composable(route = MainScreen.Search.name) {
            SearchScreen()
        }
        composable(route = MainScreen.Schedule.name) {
            MainScreen()
        }
        composable(route = MainScreen.Calendar.name) {
            CalendarScreen()
        }
        composable(route = MainScreen.Menu.name) {
            MenuScreen()
        }
    }
}