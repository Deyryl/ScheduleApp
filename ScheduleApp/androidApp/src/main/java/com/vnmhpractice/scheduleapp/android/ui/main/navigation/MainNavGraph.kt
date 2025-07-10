package com.vnmhpractice.scheduleapp.android.ui.main.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.components.TopBarText
import com.vnmhpractice.scheduleapp.android.ui.main.calendar.CalendarScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.MenuScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.account.AccountScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.information.InformationScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.EditProjectScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.ScheduleScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.project.ProjectScreen
import com.vnmhpractice.scheduleapp.android.ui.main.search.SearchScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    val mainDestinations = mapOf(
        "Search" to stringResource(R.string.search),
        "Schedule" to stringResource(R.string.my_schedule),
        "Calendar" to stringResource(R.string.calendar),
        "Menu" to stringResource(R.string.menu),
        "Account" to stringResource(R.string.account),
        "Information" to stringResource(R.string.information)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomRoutes = bottomNavItems.map { it.route }

    val showBackButton = navController.previousBackStackEntry != null
            && currentRoute !in bottomRoutes

    val currentTitle = mainDestinations[currentRoute] ?: ""

    Scaffold(
        topBar = {
            TopBar(
                title = { TopBarText(currentTitle) },
                canNavigateBack = showBackButton,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            if (currentRoute in bottomRoutes) {
                BottomBar(
                    navController = navController,
                    items = bottomNavItems
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "Schedule",
            modifier = Modifier.padding(padding)
        ) {
            composable(route = "Search") {
                SearchScreen()
            }

            // Окно расписаний и вложенные окна
            composable(route = "Schedule") {
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
            composable(route = "Calendar") {
                CalendarScreen()
            }

            // Окно меню и вложенные окна
            composable(route = "Menu") {
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)
) {
    TopAppBar(
        title = title,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.btn_back)
                    )
                }
            }
        },
        windowInsets = TopAppBarDefaults.windowInsets
    )
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(1) }

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.title),
                        tint =  if (selectedItemIndex == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selectedItemIndex == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}