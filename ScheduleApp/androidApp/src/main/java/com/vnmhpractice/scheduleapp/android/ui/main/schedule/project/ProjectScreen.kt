package com.vnmhpractice.scheduleapp.android.ui.main.schedule.project

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.ProjectViewModel

@Composable
fun ProjectScreen(
    projectId: String,
    modifier: Modifier = Modifier,
    onNavigateToDetails: () -> Unit = {}
) {
    val viewModel: ProjectViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    viewModel.updateProject(ProjectData.getProjectById(projectId))

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.small_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.project.members.isNotEmpty()) {
            Header(
                title = state.project.title,
                modifier = Modifier.height(40.dp),
                onMoreClick = {}
            )
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .background(MaterialTheme.colorScheme.outline)
                    .fillMaxWidth()
            )
            TaskCard(
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Project not found",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun Header(
    title: String,
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null,
    onMoreClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image ?: R.drawable.ic_default),
            contentDescription = stringResource(R.string.project_image),
            modifier = Modifier
                .size(32.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onMoreClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_card_more),
                contentDescription = stringResource(R.string.project_menu),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun TaskCard(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun ProjectScreenPreview() {
    ProjectData.initTestData()
    ProjectScreen(projectId = "1")
}