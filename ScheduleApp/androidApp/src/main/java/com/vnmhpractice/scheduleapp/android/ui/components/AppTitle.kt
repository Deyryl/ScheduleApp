package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.theme.primaryLight

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(160.dp))
    Text(
        text = buildAnnotatedString {
                append("Schedule")
                withStyle(style = SpanStyle(color = primaryLight)) {
                    append("App")
                }
            },
        style = MaterialTheme.typography.displayLarge,
        modifier = modifier
    )
}