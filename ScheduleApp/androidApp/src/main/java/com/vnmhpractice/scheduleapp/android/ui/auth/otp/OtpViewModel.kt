package com.vnmhpractice.scheduleapp.android.ui.auth.otp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OtpViewModel : ViewModel() {
    private var validOtp: String = ""
    private var countdownJob: Job? = null

    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState: StateFlow<OtpUiState> = _uiState.asStateFlow()

    init {
        sendOtp()
    }

    fun updateOtp(otp: String) {
        _uiState.update { it.copy(otpText = otp) }
        updateIsOtpCorrect()
    }

    fun sendOtp() {
        countdownJob?.cancel()

        updateValidOtp()

        _uiState.update {
            it.copy(
                otpText = "",
                isOtpSent = true,
                isCountingDown = true,
                secondsLeft = 60,
                isCorrectOtp = TypingState.Typing
            )
        }

        startCountdown()
    }

    private fun updateIsOtpCorrect() {
        val typingState = when {
            uiState.value.otpText.length < validOtp.length -> TypingState.Typing
            uiState.value.otpText.compareTo(validOtp) == 0 -> TypingState.Correct
            else -> TypingState.Invalid
        }
        _uiState.update { it.copy(isCorrectOtp = typingState) }
        Log.d("isOtpCorrect", uiState.value.isCorrectOtp.name)
    }

    private fun updateValidOtp() {
        // MARK: Code to complete
        validOtp = "1234"
    }

    private fun startCountdown(from: Int = 60) {
        countdownJob = viewModelScope.launch {
            for (i in from downTo 0) {
                delay(1000)
                _uiState.update {
                    it.copy(secondsLeft = i)
                }
            }
            _uiState.update {
                it.copy(isCountingDown = false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
    }
}