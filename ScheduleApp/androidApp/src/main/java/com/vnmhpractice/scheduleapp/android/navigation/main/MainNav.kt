package com.vnmhpractice.scheduleapp.android.navigation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vnmhpractice.scheduleapp.android.ui.home.schedule.ScheduleScreen

fun NavGraphBuilder.mainNavigation(navController: NavHostController) {
    val screenModifier = Modifier.fillMaxSize()

    navigation(startDestination = MainScreen.Schedule.name, route = "Main") {
        composable(route = MainScreen.Schedule.name) {
            ScheduleScreen(screenModifier)
        }
    }
}