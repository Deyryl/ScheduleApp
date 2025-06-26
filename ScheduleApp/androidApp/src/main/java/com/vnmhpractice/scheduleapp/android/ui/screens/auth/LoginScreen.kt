package com.vnmhpractice.scheduleapp.android.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.components.AppTitle
import com.vnmhpractice.scheduleapp.android.ui.components.IconActionButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryOutlinedButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryTextField
import com.vnmhpractice.scheduleapp.android.ui.theme.secondaryLight

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.large_padding))
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(88.dp))
        PrimaryTextField(
            value = "",
            placeholder = stringResource(R.string.email),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        PrimaryTextField(
            value = "",
            placeholder = stringResource(R.string.password),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.height(30.dp))
        Row {
            IconActionButton(
                drawableId = R.drawable.google_icon
            )
            Spacer(Modifier.width(12.dp))
            IconActionButton(
                drawableId = R.drawable.vk_icon
            )
        }
        Spacer(Modifier.height(30.dp))
        PrimaryButton(
            text = stringResource(R.string.login_button),
            modifier = Modifier.width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.forgot_the_password),
            style = MaterialTheme.typography.labelLarge,
            color = secondaryLight,
            modifier = Modifier.clickable(
                onClick = {}
            )
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}