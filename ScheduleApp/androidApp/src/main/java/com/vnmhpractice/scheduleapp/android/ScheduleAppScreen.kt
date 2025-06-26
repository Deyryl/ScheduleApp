package com.vnmhpractice.scheduleapp.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vnmhpractice.scheduleapp.android.ui.screens.auth.LoginScreen
import com.vnmhpractice.scheduleapp.android.ui.screens.auth.RegistrationScreen
import com.vnmhpractice.scheduleapp.android.ui.screens.auth.StartScreen
import com.vnmhpractice.scheduleapp.android.ui.theme.onPrimaryLight

@Composable
fun ScheduleApp(modifier: Modifier = Modifier) {
    Scaffold (
        containerColor = onPrimaryLight,
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        LoginScreen(modifier = Modifier.padding(innerPadding))
    }
}