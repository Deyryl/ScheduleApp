package com.vnmhpractice.scheduleapp.android.ui.main.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    onAccountClick: () -> Unit = {},
    onThemeClick: () -> Unit = {},
    onInfoClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(R.dimen.small_padding),
                end = dimensionResource(R.dimen.small_padding)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Аккаунт
        MenuButton(
            text = R.string.btn_account,
            icon = R.drawable.ic_account,
            onClick = onAccountClick,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        )
        // Тема
        MenuButton(
            text = R.string.theme,
            icon = R.drawable.ic_theme,
            onClick = onThemeClick,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        )
        // Информация
        MenuButton(
            text = R.string.btn_info,
            icon = R.drawable.ic_info,
            onClick = onInfoClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MenuButton(
    @StringRes text: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(dimensionResource(R.dimen.btn_medium_height)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(text),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}