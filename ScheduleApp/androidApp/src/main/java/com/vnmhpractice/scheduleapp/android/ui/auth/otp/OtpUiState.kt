package com.vnmhpractice.scheduleapp.android.ui.auth.otp

enum class TypingState() {
    Typing,
    Correct,
    Invalid
}

data class OtpUiState(
    val email:          String      = "example@gmail.com",
    val otpText:        String      = "",
    val secondsLeft:    Int         = 10,
    val isCountingDown: Boolean     = false,
    val isOtpSent:      Boolean     = false,
    val isCorrectOtp:   TypingState = TypingState.Typing
)
