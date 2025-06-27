package com.vnmhpractice.scheduleapp.android.ui.screens.auth.registration

data class RegistrationUiState(
    val name:               String  = "",
    val email:              String  = "",
    val password:           String  = "",
    val repeatPassword:    String = "",
    val errorMessage:       String? = null,
    val isLoading:          Boolean = false,
    val isSuccess:          Boolean = false,
)
