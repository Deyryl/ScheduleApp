package com.vnmhpractice.scheduleapp.android.ui.screens.auth.start

data class StartUiState(
    val isLoading:          Boolean = true,
    val isUserAuthorized:   Boolean = false,
    val errorMessage:       String? = null
)