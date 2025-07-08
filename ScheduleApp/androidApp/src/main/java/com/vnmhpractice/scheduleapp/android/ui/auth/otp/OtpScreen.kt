package com.vnmhpractice.scheduleapp.android.ui.auth.otp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.vnmhpractice.scheduleapp.android.ui.components.rememberShakingState
import com.vnmhpractice.scheduleapp.android.ui.components.shakable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    viewModel: OtpViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val shakeState = rememberShakingState()
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "Введите код из письма, присланного на ${state.email}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(44.dp))
        OtpInputField(
            otpText = state.otpText,
            shouldShowCursor = true,
            typingState = state.isCorrectOtp,
            onOtpModified = viewModel::updateOtp,
            modifier = Modifier
                .focusRequester(focusRequester)
                .shakable(shakeState)
        )
        LaunchedEffect(state.isCorrectOtp) {
            if (state.isCorrectOtp == TypingState.Invalid && state.otpText.length == 4) {
                shakeState.shake(animationDuration = 40)
            }
        }
        Spacer(Modifier.height(32.dp))
        if (state.isCountingDown) {
            Text(
                text = "Отправить повторно через ${state.secondsLeft} сек",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        } else {
            Text(
                text = "Отправить код повторно",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable(
                        onClick = {
                            viewModel.sendOtp()
                        }
                    )
            )
        }
    }
}

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpLength: Int = 4,
    shouldShowCursor: Boolean,
    typingState: TypingState,
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
                        showCursor = shouldShowCursor && isFocused,
                        typingState = typingState
                    )
                }
            }
        }
    )
}

@Composable
fun CharacterContainer(
    character: String,
    showCursor: Boolean,
    typingState: TypingState
) {
    val outlineColor = when (typingState) {
        TypingState.Typing -> MaterialTheme.colorScheme.onBackground
        TypingState.Correct -> MaterialTheme.colorScheme.primary
        TypingState.Invalid -> MaterialTheme.colorScheme.error
    }

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
                .height(3.dp)
                .fillMaxWidth()
                .background(outlineColor)
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