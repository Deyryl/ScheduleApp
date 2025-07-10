package com.vnmhpractice.scheduleapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.ui.theme.ScheduleAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ThemeUiState(
    val isDarkTheme: Boolean = true,
    val isThemeInitialized: Boolean = false
)

class MainActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState.asStateFlow()

    fun initializeTheme(isDarkTheme: Boolean) {
        _uiState.value = _uiState.value.copy(
            isDarkTheme = isDarkTheme,
            isThemeInitialized = true
        )
    }

    fun toggleTheme() {
        _uiState.value = _uiState.value.copy(
            isDarkTheme = !_uiState.value.isDarkTheme
        )
    }

    fun updateTheme(isDarkTheme: Boolean) {
        _uiState.value = _uiState.value.copy(
            isDarkTheme = isDarkTheme
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val viewModel: MainActivityViewModel = viewModel()
            val state by viewModel.uiState.collectAsState()

            val sharedPreferences = remember {
                context.getSharedPreferences("app_prefs", MODE_PRIVATE)
            }

            LaunchedEffect(Unit) {
                if (!state.isThemeInitialized) {
                    val savedTheme = sharedPreferences.getBoolean("is_dark_theme", true)
                    viewModel.initializeTheme(savedTheme)
                }
            }

            LaunchedEffect(state.isDarkTheme) {
                if (state.isThemeInitialized) {
                    sharedPreferences.edit {
                        putBoolean("is_dark_theme", state.isDarkTheme)
                    }
                }
            }

            ScheduleAppTheme(darkTheme = state.isDarkTheme) {
                ScheduleApp()
            }
        }
    }
}