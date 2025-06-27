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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    placeholder: String = "",
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isError: Boolean = false,
    isPassword: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    val visualTransformation = when {
        isPassword && !passwordVisibility -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    val trailingIcon: @Composable (() -> Unit)? = when {
        isPassword -> {
            {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisibility) R.drawable.ic_visibility_off
                            else R.drawable.ic_visibility
                        ),
                        contentDescription = stringResource(R.string.password_visibility)
                    )
                }
            }
        }
        else -> null
    }

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
        visualTransformation = visualTransformation,
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
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    singleLine: Boolean = true
) {
    PrimaryTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        imeAction = imeAction,
        keyboardType = KeyboardType.Password,
        isPassword = true,
        modifier = modifier,
        isError = isError,
        singleLine = singleLine
    )
}
