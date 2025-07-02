package com.vnmhpractice.scheduleapp.android.ui.auth.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryOutlinedButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryTextField
import androidx.compose.runtime.getValue

@Composable
fun RegistrationScreen(
    onRegistrationClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val viewModel: RegistrationViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.large_padding))
            .verticalScroll(rememberScrollState())
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(44.dp))
        PrimaryTextField(
            value = state.name,
            onValueChange = viewModel::onNameChanged,
            placeholder = stringResource(R.string.name),
            imeAction = ImeAction.Next,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
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
            imeAction = ImeAction.Next,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        PasswordTextField(
            value = state.repeatPassword,
            onValueChange = viewModel::onRepeatPasswordChanged,
            placeholder = stringResource(R.string.repeat_password),
            imeAction = ImeAction.Done,
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
        Spacer(Modifier.weight(1f))
        PrimaryOutlinedButton(
            text = stringResource(R.string.create_account),
            onClick = onRegistrationClicked,
            modifier = Modifier.width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.spacer_height_medium)))
    }
}

    @Preview
    @Composable
    fun RegistrationScreenPreview() {
        RegistrationScreen()
    }