package com.vnmhpractice.scheduleapp.android.ui.main.menu.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryTextField

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    onChangePasswordClick: () -> Unit = {},
    onExitClick: () -> Unit = {},
    viewModel: AccountViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.small_padding))
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_default),
                contentDescription = stringResource(R.string.user_avatar),
                modifier = Modifier.size(100.dp).padding(end = 10.dp)
            )
            Column(Modifier.weight(1f)) {
                Spacer(Modifier.height(40.dp))
                PrimaryTextField(
                    value = state.user?.username ?: "",
                    onValueChange = viewModel::onNameChanged,
                    placeholder = "Имя",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        PrimaryTextField(
            value = state.user?.email ?: "",
            onValueChange = viewModel::onNameChanged,
            placeholder = "Email",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            modifier = Modifier.fillMaxWidth()
        )
        // MARK: Code to complete
    }
}

//@Composable
//fun AccountField(
//    label: String,
//    text: String,
//    modifier: Modifier = Modifier
//) {
//    PrimaryTextField(
//
//    )
//}

@Preview
@Composable
fun AccountScreenPreview() {
    AccountScreen()
}