package com.vnmhpractice.scheduleapp.android.ui.main.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.content.Context
import com.vnmhpractice.scheduleapp.models.User

data class MenuUiState(
    val isDarkTheme: Boolean? = null
)

val user = User

class MenuViewModel(
    private val context: Context
) : ViewModel() {
    val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()


}