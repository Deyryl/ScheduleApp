package com.vnmhpractice.scheduleapp.android.ui.auth.changepassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.vnmhpractice.scheduleapp.android.ui.auth.login.LoginViewModel
import com.vnmhpractice.scheduleapp.android.ui.components.AppTitle
import com.vnmhpractice.scheduleapp.android.ui.components.PasswordTextField
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryOutlinedButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryTextField

@Composable
fun ChangePasswordScreen(
    onChangePasswordClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val viewModel: ChangePasswordViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.large_padding))
            .verticalScroll(rememberScrollState())
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(88.dp))
        PasswordTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            placeholder = stringResource(R.string.new_password),
            imeAction = ImeAction.Done,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        PasswordTextField(
            value = state.password,
            onValueChange = viewModel::onRepeatPasswordChanged,
            placeholder = stringResource(R.string.repeat_password),
            imeAction = ImeAction.Done,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.weight(1f))
        PrimaryOutlinedButton(
            text = stringResource(R.string.btn_change_password),
            onClick = onChangePasswordClicked,
            modifier = Modifier.width(dimensionResource(R.dimen.medium_width))
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.spacer_height_medium)))
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen()
}