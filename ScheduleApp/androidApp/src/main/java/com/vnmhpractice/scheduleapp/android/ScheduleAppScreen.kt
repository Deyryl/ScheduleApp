package com.vnmhpractice.scheduleapp.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.vnmhpractice.scheduleapp.android.data.datastore.DataStoreManager
import com.vnmhpractice.scheduleapp.android.ui.auth.AuthViewModel
import com.vnmhpractice.scheduleapp.android.ui.auth.navigation.AuthNavGraph
import com.vnmhpractice.scheduleapp.android.ui.main.navigation.MainNavGraph

@Composable
fun ScheduleApp(
    dataStoreManager: DataStoreManager,
    authViewModel: AuthViewModel = viewModel()
) {
    val navController = rememberNavController()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    if (isLoggedIn) {
        MainNavGraph(dataStoreManager, navController)
    } else {
        AuthNavGraph(navController)
    }
}