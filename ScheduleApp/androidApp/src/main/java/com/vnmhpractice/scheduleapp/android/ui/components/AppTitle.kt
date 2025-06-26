package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(160.dp))
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.displayLarge,
    )
}