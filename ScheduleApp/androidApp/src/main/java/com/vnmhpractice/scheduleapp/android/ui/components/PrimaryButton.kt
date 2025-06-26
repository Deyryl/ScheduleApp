package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.ui.theme.onPrimaryLight
import com.vnmhpractice.scheduleapp.android.ui.theme.primaryLight

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(containerColor = primaryLight),
        elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
        modifier = modifier.height(60.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun PrimaryOutlinedButton(
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
        elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
        modifier = modifier.height(60.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}