package com.vnmhpractice.scheduleapp.android.ui.auth.start

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.components.AppTitle
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryButton
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryOutlinedButton

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    viewModel: StartViewModel = viewModel(),
    onRegistrationClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.large_padding))
            .verticalScroll(rememberScrollState())
    ) {
        AppTitle()
        Spacer(modifier = Modifier.height(360.dp))
        PrimaryButton(
            text = stringResource(R.string.btn_registration),
            onClick = onRegistrationClicked,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryOutlinedButton(
            text = stringResource(R.string.btn_login),
            onClick = onLoginClicked,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(
        onRegistrationClicked = {},
        onLoginClicked = {}
    )
}