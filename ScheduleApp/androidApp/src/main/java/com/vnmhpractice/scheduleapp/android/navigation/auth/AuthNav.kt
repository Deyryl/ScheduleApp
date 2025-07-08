package com.vnmhpractice.scheduleapp.android.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vnmhpractice.scheduleapp.android.ui.auth.changepassword.ChangePasswordScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.login.LoginScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.otp.OtpScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.registration.RegistrationScreen
import com.vnmhpractice.scheduleapp.android.ui.auth.start.StartScreen

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    navigation(startDestination = AuthDestination.Start.name, route = "Auth") {
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