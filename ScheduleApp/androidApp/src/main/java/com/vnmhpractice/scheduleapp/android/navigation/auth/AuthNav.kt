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
    navigation(startDestination = AuthScreen.Start.name, route = "Auth") {
        composable(route = AuthScreen.Start.name) {
            StartScreen(
                onRegistrationClicked = {
                    navController.navigate(route = AuthScreen.Registration.name)
                },
                onLoginClicked = {
                    navController.navigate(route = AuthScreen.Login.name)
                }
            )
        }
        composable(route = AuthScreen.Registration.name) {
            RegistrationScreen(
                onRegistrationClicked = {
                    navController.navigate(route = AuthScreen.OTP.name)
                }
            )
        }
        composable(route = AuthScreen.Login.name) {
            LoginScreen(
//                    onLoginClicked = TODO(),
//                    onGoogleIconClicked = TODO(),
//                    onVkIconClicked = TODO(),
                onForgotPasswordClicked = {
                    navController.navigate(AuthScreen.ChangePassword.name)
                }
            )
        }
        composable(route = AuthScreen.OTP.name) {
            OtpScreen()
        }
        composable(route = AuthScreen.ChangePassword.name) {
            ChangePasswordScreen(
                onChangePasswordClicked = {
                    navController.popBackStack(route = AuthScreen.Start.name, inclusive = false)
                }
            )
        }
    }
}