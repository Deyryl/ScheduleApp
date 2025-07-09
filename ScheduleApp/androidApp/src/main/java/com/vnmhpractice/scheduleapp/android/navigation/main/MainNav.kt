package com.vnmhpractice.scheduleapp.android.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vnmhpractice.scheduleapp.android.ui.main.calendar.CalendarScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.MenuScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.account.AccountScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.information.InformationScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.ScheduleScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.project.ProjectScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.projectDetails.ProjectDetailsScreen
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
            ProjectScreen(

            )
        }
        composable(route = ScheduleDestination.ProjectDetails.name) {
            ProjectDetailsScreen()
        }

        // Окно календаря и вложенные окна
        composable(route = MainDestination.Calendar.name) {
            CalendarScreen()
        }

        // Окно меню и вложенные окна
        composable(route = MainDestination.Menu.name) {
            MenuScreen(
                onAccountClick = { navController.navigate(MenuDestination.Account.name) },
                onInfoClick = { navController.navigate(MenuDestination.Information.name) }
            )
        }
        composable(route = MenuDestination.Account.name) {
            AccountScreen()
        }
        composable(route = MenuDestination.Information.name) {
            InformationScreen()
        }
    }
}