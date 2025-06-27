package com.vnmhpractice.scheduleapp.android.ui.screens.auth.login

data class LoginUiState(
    val email:          String  = "",
    val password:       String  = "",
    val errorMessage:   String? = null,
    val isLoading:      Boolean = false,
    val isSuccess:      Boolean = false,
)
