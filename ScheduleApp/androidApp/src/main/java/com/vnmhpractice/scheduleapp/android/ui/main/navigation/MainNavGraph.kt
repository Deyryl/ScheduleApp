package com.vnmhpractice.scheduleapp.android.ui.main.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.datastore.DataStoreManager
import com.vnmhpractice.scheduleapp.android.ui.components.TopBarText
import com.vnmhpractice.scheduleapp.android.ui.components.composableAnimated
import com.vnmhpractice.scheduleapp.android.ui.main.calendar.CalendarScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.MenuScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.account.AccountScreen
import com.vnmhpractice.scheduleapp.android.ui.main.menu.information.InformationScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.ProjectManageScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.ScheduleScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.project.ProjectScreen
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.projectDetails.ProjectDetailsScreen
import com.vnmhpractice.scheduleapp.android.ui.main.search.SearchScreen

@Composable
fun MainNavGraph(
    dataStoreManager: DataStoreManager,
    navController: NavHostController
) {
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
            composableAnimated(
                route = "Search",
            ) {
                SearchScreen()
            }

            // Окно расписаний и вложенные окна
            composableAnimated(route = "Schedule") {
                ScheduleScreen(
                    onAddClick = { navController.navigate("CreateProject") },
                    onProjectClick = { projectId ->
                        navController.navigate("project/$projectId")
                    },
                    onEditClick = { projectId ->
                        navController.navigate("manage_project/$projectId")
                    }
                )
            }
            composableAnimated(
                route = "project/{projectId}",
                arguments = listOf(
                    navArgument("projectId") {
                        type = NavType.StringType
                    }
                ),
                isHierarchical = true
            ) { backStackEntry ->
                val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
                ProjectScreen(
                    projectId = projectId,
                    onTaskClick = {},
                    onNavigateToDetails = {
                        navController.navigate("ProjectDetails/$projectId")
                    }
                )
            }
            composableAnimated(
                route = "manage_project/{projectId}",
                arguments = listOf(
                    navArgument("projectId") {
                        type = NavType.StringType
                    }
                ),
                isHierarchical = true
            ) { backStackEntry ->
                val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
                ProjectManageScreen(
                    projectId = projectId,
                    onCancelClick = { navController.popBackStack() },
                    onSaveClick = {
                        navController.navigate("Schedule") {
                            popUpTo("Schedule") { inclusive = true }
                        }
                    }
                )
            }
            composableAnimated(
                route = "CreateProject",
                isHierarchical = true
            ) {
                ProjectManageScreen(
                    onCancelClick = { navController.popBackStack() },
                    onSaveClick = {
                        navController.navigate("Schedule") {
                            popUpTo("Schedule") { inclusive = true }
                        }
                    }
                )
            }
            composableAnimated(
                route = "ProjectDetails/{projectId}",
                arguments = listOf(
                    navArgument("projectId") {
                        type = NavType.StringType
                    }
                ),
                isHierarchical = true
            ) { backStackEntry ->
                val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
                ProjectDetailsScreen(
                    projectId = projectId,
                    onSearchClick = {},
                    onExitClick = {}
                )
            }

            // Окно календаря и вложенные окна
            composableAnimated(route = "Calendar") {
                CalendarScreen()
            }

            // Окно меню и вложенные окна
            composableAnimated(route = "Menu") {
                MenuScreen(
                    dataStoreManager = dataStoreManager,
                    onAccountClick = { navController.navigate("Account") },
                    onInfoClick = { navController.navigate("Information") }
                )
            }
            composableAnimated(route = "Account", isHierarchical = true) {
                AccountScreen()
            }
            composableAnimated(route = "Information", isHierarchical = true) {
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
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.title),
                        tint = if (currentRoute == item.route)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (currentRoute == item.route)
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