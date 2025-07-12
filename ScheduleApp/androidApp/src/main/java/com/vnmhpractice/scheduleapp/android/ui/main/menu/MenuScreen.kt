package com.vnmhpractice.scheduleapp.android.ui.main.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.datasource.SettingsData
import com.vnmhpractice.scheduleapp.android.data.datastore.DataStoreManager
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    dataStoreManager: DataStoreManager,
    modifier: Modifier = Modifier,
    onAccountClick: () -> Unit = {},
    onInfoClick: () -> Unit = {}
) {
    val coroutine = rememberCoroutineScope()
    val darkTheme = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        dataStoreManager.getSettings().collect { settings ->
            darkTheme.value = settings.darkTheme
        }
    }

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
        // Информация
        MenuButton(
            text = R.string.btn_info,
            icon = R.drawable.ic_info,
            onClick = onInfoClick,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        )
        // Тема
        Row(
            modifier = Modifier
                .height(dimensionResource(R.dimen.btn_medium_height))
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(24.dp))
            MenuSwitch(
                checked = darkTheme.value,
                onCheckedChange = {
                    darkTheme.value = !darkTheme.value
                    coroutine.launch {
                        dataStoreManager.saveSettings(SettingsData(darkTheme.value))
                    }
                }
            )
        }
    }
}

@Composable
private fun MenuSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(dimensionResource(R.dimen.btn_medium_height))
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(2.dp))
        Icon(
            painter = painterResource(R.drawable.ic_theme),
            contentDescription = stringResource(R.string.theme),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp).padding(2.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.theme),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(end = 12.dp),
            colors = SwitchDefaults.colors(
                checkedIconColor = MaterialTheme.colorScheme.onSurface,
                checkedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedIconColor = MaterialTheme.colorScheme.surface,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                uncheckedBorderColor = MaterialTheme.colorScheme.primary
            ),
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

//@Preview
//@Composable
//fun MenuScreenPreview() {
//    MenuScreen()
//}