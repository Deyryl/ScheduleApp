package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.vnmhpractice.scheduleapp.android.R

@Composable
fun IconActionButton(
    @DrawableRes drawableId: Int,
    onClick: () -> Unit = {},
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        content = {
            Image(painter = painterResource(drawableId),
                contentDescription = contentDescription
            )
        },
        modifier = modifier.size(dimensionResource(R.dimen.ic_size))
    )
}