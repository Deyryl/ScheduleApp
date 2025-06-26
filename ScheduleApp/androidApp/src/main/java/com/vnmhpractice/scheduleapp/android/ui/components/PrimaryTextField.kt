package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.vnmhpractice.scheduleapp.android.ui.theme.onPrimaryLight
import com.vnmhpractice.scheduleapp.android.ui.theme.primaryLight

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        placeholder = { Text(placeholder) },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = onPrimaryLight,
            unfocusedBorderColor = primaryLight,
            focusedContainerColor = onPrimaryLight,
            focusedBorderColor = primaryLight
        ),
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        placeholder = { Text(placeholder) },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = onPrimaryLight,
            unfocusedBorderColor = primaryLight,
            focusedContainerColor = onPrimaryLight,
            focusedBorderColor = primaryLight
        ),
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier
    )
}
