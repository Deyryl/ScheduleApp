package com.vnmhpractice.scheduleapp.android.ui.main.menu.account

import androidx.lifecycle.ViewModel
import com.vnmhpractice.scheduleapp.android.data.datasource.users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AccountViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(user = users.first()) }
    }

    fun onNameChanged(name: String) {
        val email: String = uiState.value.user?.email ?: ""
        _uiState.update {
            it.copy(it.user?.copy(username = name, email = email))
        }
    }

    fun onEmailChanged(email: String) {
        val username: String = uiState.value.user?.username ?: ""
        _uiState.update {
            it.copy(it.user?.copy(username = username, email = email))
        }
    }

    fun onPasswordChanged() {

    }
}