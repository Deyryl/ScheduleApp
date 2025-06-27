package com.vnmhpractice.scheduleapp.android

import com.vnmhpractice.scheduleapp.android.ui.screens.auth.AuthScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vnmhpractice.scheduleapp.android.ui.screens.auth.login.LoginScreen
import com.vnmhpractice.scheduleapp.android.ui.theme.onPrimaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleAppTopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "") },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
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

    //val viewModel: OrderViewModel = viewModel()

    Scaffold (
        //topBar = ScheduleAppTopBar(),
        containerColor = onPrimaryLight,
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        LoginScreen(modifier = Modifier.padding(innerPadding))
    }
}