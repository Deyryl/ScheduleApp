package com.vnmhpractice.scheduleapp.android.ui.main.schedule.project

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData
import com.vnmhpractice.scheduleapp.android.data.model.Task
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.ProjectViewModel

@Composable
fun ProjectScreen(
    projectId: String,
    modifier: Modifier = Modifier,
    onNavigateToDetails: () -> Unit = {},
) {
    val viewModel: ProjectViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    viewModel.updateProject(ProjectData.getProjectById(projectId)!!)

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
                image = state.project.image,
                onNavigateToDetails = onNavigateToDetails,
                onOptionClick = {},
                onSearchClick = {},
                onExitClick = {}
            )
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.outline)
            Spacer(Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.project.tasks) { task ->
                    TaskCard(
                        task = task,
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .padding(
                                bottom = 20.dp
                            )
                    )
                }
                item {
                    if (state.project.tasks.isEmpty()) {
                        Text(
                            text = stringResource(R.string.there_are_no_tasks),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.btn_add_task),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable(onClick = {})
                    )
                }
            }
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
    image: Uri? = null,
    onNavigateToDetails: () -> Unit = {},
    onOptionClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onExitClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (image == null) {
            Icon(
                painter = painterResource(R.drawable.ic_default_project),
                contentDescription = stringResource(R.string.lack_of_image),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(32.dp)
            )
        } else {
            AsyncImage(
                modifier = Modifier.size(32.dp).clip(MaterialTheme.shapes.large),
                model = image,
                contentDescription = stringResource(R.string.project_image),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable(onClick = onNavigateToDetails)
        )
        Spacer(Modifier.weight(1f))
        ProjectDropdownMenu(
            onOptionClick = onOptionClick,
            onSearchClick = onSearchClick,
            onExitClick = onExitClick,
        )
    }
}

@Composable
fun ProjectDropdownMenu(
    onOptionClick: () -> Unit,
    onSearchClick: () -> Unit,
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.size(32.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.project_menu),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.more_options),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                onClick = onOptionClick
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.more_search),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                onClick = onSearchClick
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.more_exit),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                onClick = onExitClick
            )
        }
    }
}


@Composable
private fun TaskCard(
    task: Task,
    modifier: Modifier = Modifier,
    onTaskClick: () -> Unit = {}
) {
    val title = if (task.title.length > 24) task.title.substring(0, 24)+"..."
                else task.title
    val description = task.description?.length?.let {
        if (it > 60)
            task.description.substring(0, 60)+"..."
        else
            task.description
    }

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onTaskClick
    ) {
        Row {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            bottom = 10.dp
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun ProjectScreenPreview() {
    ProjectData.initTestData()
    ProjectScreen(projectId = "1")
}