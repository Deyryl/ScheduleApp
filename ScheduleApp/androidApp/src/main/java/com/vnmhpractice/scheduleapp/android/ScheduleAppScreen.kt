package com.vnmhpractice.scheduleapp.android

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vnmhpractice.scheduleapp.android.navigation.auth.authNavigation
import com.vnmhpractice.scheduleapp.android.navigation.main.BottomNavItem
import com.vnmhpractice.scheduleapp.android.navigation.main.bottomNavItems
import com.vnmhpractice.scheduleapp.android.navigation.main.mainNavigation

@Composable
fun ScheduleApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val isUserLoggedIn = true

    val currentRoute = currentRoute(navController)
    val currentScreen = bottomNavItems.find { it.route == currentRoute }

    val bottomRoutes = bottomNavItems.map { it.route }
    val showBackButton = navController.previousBackStackEntry != null &&
            currentRoute !in bottomRoutes

    Scaffold (
        topBar = {
            ScheduleAppTopBar(
                title = if (currentScreen != null) stringResource(currentScreen.title) else "",
                canNavigateBack = showBackButton,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            if (isUserLoggedIn) {
                ScheduleAppBottomBar(
                    navController = navController,
                    items = bottomNavItems
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isUserLoggedIn) "Main" else "Auth",
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            authNavigation(navController)
            mainNavigation(navController)
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleAppTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
        },
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
fun ScheduleAppBottomBar(
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