package com.vnmhpractice.scheduleapp.android.ui.auth.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.auth.changepassword.ChangePasswordScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.login.LoginScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.otp.OtpScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.registration.RegistrationScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.start.StartScreen

@Composable
fun AuthNavGraph(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBackButton = currentRoute != AuthDestination.Start.name

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = showBackButton,
                navigateUp = { navController.navigateUp() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AuthDestination.Start.name,
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            composable(route = AuthDestination.Start.name) {
                StartScreen(
                    onRegistrationClicked = {
                        navController.navigate(route = AuthDestination.Registration.name)
                    },
                    onLoginClicked = {
                        navController.navigate(route = AuthDestination.Login.name)
                    }
                )
            }
            composable(route = AuthDestination.Registration.name) {
                RegistrationScreen(
                    onRegistrationClicked = {
                        navController.navigate(route = AuthDestination.OTP.name)
                    }
                )
            }
            composable(route = AuthDestination.Login.name) {
                LoginScreen(
//                    onLoginClicked = TODO(),
//                    onGoogleIconClicked = TODO(),
//                    onVkIconClicked = TODO(),
                    onForgotPasswordClicked = {
                        navController.navigate(AuthDestination.ChangePassword.name)
                    }
                )
            }
            composable(route = AuthDestination.OTP.name) {
                OtpScreen()
            }
            composable(route = AuthDestination.ChangePassword.name) {
                ChangePasswordScreen(
                    onChangePasswordClicked = {
                        navController.popBackStack(route = AuthDestination.Start.name, inclusive = false)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("") },
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