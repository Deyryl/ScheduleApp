package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.R

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit = {},
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(
                start = dimensionResource(R.dimen.small_padding),
                end = dimensionResource(R.dimen.small_padding)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         items(state.projects) { project ->
            MainCard(
                project = project,
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.small_padding)),
                onCardClick = {

                }
            )
        }
        item {
            Text(
                text = stringResource(R.string.btn_add),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = onAddClicked)
            )
        }
        item {
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen()
}