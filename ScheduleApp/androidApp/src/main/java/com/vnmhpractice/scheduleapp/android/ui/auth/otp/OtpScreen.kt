package com.vnmhpractice.scheduleapp.android.ui.auth.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.ui.components.AppTitle
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    viewModel: OtpViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(88.dp))
        OtpInputField(
            otpText = state.otpText,
            shouldShowCursor = true,
            onOtpModified = viewModel::updateOtp,
            modifier = Modifier.focusRequester(focusRequester)
        )
    }
}

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpLength: Int = 4,
    shouldShowCursor: Boolean,
    onOtpModified: (String) -> Unit
) {
    BasicTextField(
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpLength && it.text.all { char -> char.isDigit() }) {
                onOtpModified(it.text)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        modifier = modifier,
        decorationBox = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(otpLength) { index ->
                    val char = if (index < otpText.length) otpText[index].toString() else ""
                    val isFocused = index == otpText.length && otpText.length != otpLength
                    CharacterContainer(
                        character = char,
                        showCursor = shouldShowCursor && isFocused
                    )
                }
            }
        }
    )
}


@Composable
fun CharacterContainer(
    character: String,
    showCursor: Boolean
) {
    Box(
        modifier = Modifier
            .width(48.dp)
            .height(64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = character,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (showCursor && character.isEmpty()) {
            BlinkingCursor()
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(2.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline)
        )
    }
}

@Composable
fun BlinkingCursor() {
    val visible = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            visible.value = !visible.value
        }
    }

    AnimatedVisibility(visible = visible.value) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(32.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview
@Composable
fun OtpScreenPreview() {
    OtpScreen()
}