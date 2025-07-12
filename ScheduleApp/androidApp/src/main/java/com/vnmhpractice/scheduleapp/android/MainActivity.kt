package com.vnmhpractice.scheduleapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vnmhpractice.scheduleapp.android.data.datastore.DataStoreManager
import com.vnmhpractice.scheduleapp.android.ui.theme.ScheduleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)
        enableEdgeToEdge()
        setContent {
            val darkTheme = remember { mutableStateOf(true) }

            LaunchedEffect(key1 = true) {
                dataStoreManager.getSettings().collect { settings ->
                    darkTheme.value = settings.darkTheme
                }
            }
            ScheduleAppTheme(darkTheme = darkTheme.value) {
                ScheduleApp(dataStoreManager)
            }
        }
    }
}