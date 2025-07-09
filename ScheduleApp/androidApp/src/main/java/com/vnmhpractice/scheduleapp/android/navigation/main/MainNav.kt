import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.vnmhpractice.scheduleapp.android.navigation.main.MainDestination
import com.vnmhpractice.scheduleapp.android.navigation.main.MenuDestination
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
            ScheduleScreen(
                onProjectClick = { projectId ->
                    navController.navigate("project/$projectId")
                }
            )
        }

        composable(
            route = "project/{projectId}",
            arguments = listOf(
                navArgument("projectId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
            ProjectScreen(
                projectId = projectId,
                onNavigateToDetails = {
                    navController.navigate("project_details/$projectId")
                }
            )
        }

        composable(
            route = "project_details/{projectId}",
            arguments = listOf(
                navArgument("projectId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
            //ProjectDetailsScreen(projectId = projectId)
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