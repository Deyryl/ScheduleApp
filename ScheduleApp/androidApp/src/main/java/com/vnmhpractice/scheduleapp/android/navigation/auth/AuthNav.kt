package com.vnmhpractice.scheduleapp.android.navigation.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vnmhpractice.scheduleapp.android.ui.auth.changepassword.ChangePasswordScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.login.LoginScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.otp.OTPScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.registration.RegistrationScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.start.StartScreen

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    val screenModifier = Modifier.fillMaxSize()

    navigation(startDestination = AuthScreen.Start.name, route = "Auth") {
        composable(route = AuthScreen.Start.name) {
            StartScreen(
                onRegistrationClicked = {
                    navController.navigate(route = AuthScreen.Registration.name)
                },
                onLoginClicked = {
                    navController.navigate(route = AuthScreen.Login.name)
                },
                modifier = screenModifier
            )
        }
        composable(route = AuthScreen.Registration.name) {
            RegistrationScreen(
                onRegistrationClicked = {
                    navController.navigate(route = AuthScreen.OTP.name)
                },
                modifier = screenModifier
            )
        }
        composable(route = AuthScreen.Login.name) {
            LoginScreen(
//                    onLoginClicked = TODO(),
//                    onGoogleIconClicked = TODO(),
//                    onVkIconClicked = TODO(),
                onForgotPasswordClicked = {
                    navController.navigate(AuthScreen.ChangePassword.name)
                },
                modifier = screenModifier
            )
        }
        composable(route = AuthScreen.OTP.name) {
            OTPScreen(screenModifier)
        }
        composable(route = AuthScreen.ChangePassword.name) {
            ChangePasswordScreen(
                onChangePasswordClicked = {
                    navController.popBackStack(route = AuthScreen.Start.name, inclusive = false)
                },
                modifier = screenModifier
            )
        }
    }
}