package com.vnmhpractice.scheduleapp.android.ui.auth.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name, errorMessage = null) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun onRepeatPasswordChanged(repeatPassword: String) {
        _uiState.update {
            it.copy(
                repeatPassword = repeatPassword,
                errorMessage = null)
        }
    }

    fun onRegistrationClick() {
        /* Обработка события регистрации
         * отправка данных на сервер и получение ответа
         * далее вывод ошибки или успешная регистрация аккаунта
         */

        // Простейшая валидация
        if (!isDataBlanked()) {
            _uiState.update {
                it.copy(errorMessage = "Заполните все поля")
            }
            return
        }

        if (!validatePasswordMatch()) {
            _uiState.update {
                it.copy(errorMessage = "Пароли не совпадают")
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                // TODO: функция запроса на отправку данных

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

    private fun isDataBlanked(): Boolean {
        val state = _uiState.value

        return  !state.name.isBlank() && !state.email.isBlank() &&
                !state.password.isBlank() && state.password.isBlank()
    }
    private fun validatePasswordMatch(): Boolean {
        return _uiState.value.password == _uiState.value.repeatPassword
    }
}