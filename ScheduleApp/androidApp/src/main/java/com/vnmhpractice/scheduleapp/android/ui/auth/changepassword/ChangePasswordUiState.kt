package com.vnmhpractice.scheduleapp.android.ui.auth.changepassword

data class ChangePasswordUiState(
    val email:          String  = "",
    val password:       String  = "",
    val repeatPassword: String  = "",
    val errorMessage:   String? = null,
    val isLoading:      Boolean = false,
    val isSuccess:      Boolean = false,
)
