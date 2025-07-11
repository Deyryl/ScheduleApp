package com.vnmhpractice.scheduleapp.android.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnmhpractice.scheduleapp.authorization.Auth
import com.vnmhpractice.scheduleapp.networking.createHttpClient
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.ktor.client.engine.okhttp.*

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun onLoginClick() {
        val state = _uiState.value



        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            //val httpClient = createHttpClient(HttpClient(OkHttp), )

            try {
//                Auth().login(
//                    email = state.email,
//                    password = state.password
//                )

                // Успешный вход
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Ошибка регистрации: ${e.message}"
                    )
                }
            }
        }
    }
}