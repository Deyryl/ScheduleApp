package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.theme.onPrimaryLight
import com.vnmhpractice.scheduleapp.android.ui.theme.primaryLight

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String = "",
    placeholder: String = "",
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
    label: String = "",
    placeholder: String = "",
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {

    var passwordVisibility by remember { mutableStateOf(false) }
    var icon =
        if (passwordVisibility) {
            painterResource(R.drawable.ic_visibility_off)
        } else {
            painterResource(R.drawable.ic_visibility)
        }

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        placeholder = { Text(placeholder) },
        label = { Text(label) },
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
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility },
            ) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility icon"
                )
            }
        },
        visualTransformation =
            if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
        modifier = modifier
    )
}
