package com.vnmhpractice.scheduleapp.android

import androidx.compose.foundation.background
import com.vnmhpractice.scheduleapp.android.ui.auth.AuthScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vnmhpractice.scheduleapp.android.ui.auth.login.LoginScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.registration.RegistrationScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.start.StartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleAppTopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun ScheduleApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AuthScreen.valueOf(
        backStackEntry?.destination?.route ?: AuthScreen.Start.name
    )

    Scaffold (
        topBar = {
            ScheduleAppTopBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AuthScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AuthScreen.Start.name) {
                StartScreen(
                    onRegistrationButtonClicked = {
                        navController.navigate(route = AuthScreen.Registration.name)
                    },
                    onLoginButtonClicked = {
                        navController.navigate(route = AuthScreen.Login.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = AuthScreen.Registration.name) {
                RegistrationScreen(modifier.fillMaxSize())
            }
            composable(route = AuthScreen.Login.name) {
                LoginScreen(modifier.fillMaxSize())
            }
            composable(route = AuthScreen.OTP.name) {

            }
            composable(route = AuthScreen.ChangePassword.name) {

            }
        }
    }
}