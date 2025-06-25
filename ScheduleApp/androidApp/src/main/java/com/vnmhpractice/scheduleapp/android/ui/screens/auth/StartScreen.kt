package com.vnmhpractice.scheduleapp.android.ui.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.theme.onPrimaryLight
import com.vnmhpractice.scheduleapp.android.ui.theme.primaryLight

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(28.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.weight(2f)
        )
        ButtonTemplate(text = stringResource(R.string.registration_button))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButtonTemplate(text = stringResource(R.string.login_button))
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
private fun ButtonTemplate(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(containerColor = primaryLight),
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
        modifier = Modifier.fillMaxWidth().height(60.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
private fun OutlinedButtonTemplate(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(2.dp, primaryLight),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = onPrimaryLight,
            contentColor = primaryLight
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
        modifier = Modifier.fillMaxWidth().height(60.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen()
}