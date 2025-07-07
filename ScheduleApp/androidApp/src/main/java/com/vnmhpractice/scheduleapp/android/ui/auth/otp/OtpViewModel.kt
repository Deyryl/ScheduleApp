package com.vnmhpractice.scheduleapp.android.ui.auth.otp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OtpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState: StateFlow<OtpUiState> = _uiState.asStateFlow()

    lateinit var validOtp: String

    init {
        updateValidOtp()
    }

    fun updateOtp(otp: String) {
        _uiState.value = _uiState.value.copy(otpText = otp)
    }

    private fun updateValidOtp() {
        // MARK: Code to complete
        // обновление текущего кода
        validOtp = "1111"
    }
}
