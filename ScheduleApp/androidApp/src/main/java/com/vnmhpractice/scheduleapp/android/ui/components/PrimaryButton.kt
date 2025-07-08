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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val elevation = dimensionResource(R.dimen.btn_elevation)
    val height = dimensionResource(R.dimen.btn_large_height)

    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults
            .buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = ButtonDefaults.elevatedButtonElevation(elevation),
        modifier = modifier.height(height)
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
    val elevation = dimensionResource(R.dimen.btn_elevation)
    val height = dimensionResource(R.dimen.btn_large_height)

    OutlinedButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(elevation),
        modifier = modifier.height(height)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.outline
        )
    }
}