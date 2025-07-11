package com.vnmhpractice.scheduleapp.android.ui.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.components.AppTitle
import com.vnmhpractice.scheduleapp.android.ui.components.IconActionButton
import com.vnmhpractice.scheduleapp.android.ui.components.PasswordTextField
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    onLoginClicked: () -> Unit = {},
    onGoogleIconClicked: () -> Unit = {},
    onVkIconClicked: () -> Unit = {},
    onForgotPasswordClicked: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.large_padding))
            .verticalScroll(rememberScrollState())
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(88.dp))
        PrimaryTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChanged,
            placeholder = stringResource(R.string.email),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        PasswordTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            placeholder = stringResource(R.string.password),
            imeAction = ImeAction.Done,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.spacer_height_medium)))
        Row {
            IconActionButton(
                onClick = onGoogleIconClicked,
                drawableId = R.drawable.google_icon
            )
            Spacer(Modifier.width(12.dp))
            IconActionButton(
                onClick = onVkIconClicked,
                drawableId = R.drawable.vk_icon
            )
        }
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            text = stringResource(R.string.btn_login),
            onClick = onLoginClicked,
            modifier = Modifier.width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.forgot_the_password),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.clickable(
                onClick = onForgotPasswordClicked
            )
        )
        Spacer(Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}