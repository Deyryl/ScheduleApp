package com.vnmhpractice.scheduleapp.android.ui.main.schedule.projectDetails

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.datasource.project2
import com.vnmhpractice.scheduleapp.android.data.model.Task
import com.vnmhpractice.scheduleapp.android.data.model.TaskType
import com.vnmhpractice.scheduleapp.android.data.model.User
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.main.MainViewModel
import com.vnmhpractice.scheduleapp.android.ui.main.schedule.project.ProjectDropdownMenu
import com.vnmhpractice.scheduleapp.android.ui.theme.shapes
import kotlinx.datetime.LocalDateTime

@Composable
fun ProjectDetailsScreen(
    projectId: String?,
    onSearchClick: () -> Unit,
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.small_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // val state by viewModel.uiState.collectAsState()
        val project = projectId?.let { viewModel.getProjectById(projectId) }

        if (project != null) {
            Header(
                title = project.title,
                onSearchClick = onSearchClick,
                onExitClick = onExitClick,
                image = project.image
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(MaterialTheme.colorScheme.outline)
            )
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_member),
                    contentDescription = "Добавление участника",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier.padding(end = 16.dp)
                )
                Text(
                    text = "Добавить участника",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(project.members) { member ->
                    MemberCard(
                        member = member,
                        isOwner = project.owner == member
                    )
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(Modifier.height(30.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(MaterialTheme.colorScheme.outline)
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "История",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
                items(project.tasks.filter { it.type == TaskType.COMPLETED }.toList()) { task ->
                    TaskCard(task, Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp))
                }
            }
        } else {
            Text("Ошибка", style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Composable
private fun Header(
    title: String,
    onSearchClick: () -> Unit,
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier,
    image: Uri? = null,
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
                modifier = Modifier
                    .size(32.dp)
                    .clip(MaterialTheme.shapes.large),
                model = image,
                contentDescription = stringResource(R.string.project_image),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.weight(1f))
        ProjectDropdownMenu(
            onOptionClick = {},
            onSearchClick = onSearchClick,
            onExitClick = onExitClick,
        )
    }
}

@Composable
private fun MemberCard(
    member: User,
    modifier: Modifier = Modifier,
    isOwner: Boolean = false
) {
    Card(
        modifier = modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (member.image == null) {
                Icon(
                    painter = painterResource(R.drawable.ic_account),
                    contentDescription = "Участник",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = modifier.size(40.dp)
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = member.image,
                    contentDescription = stringResource(R.string.project_image),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.width(8.dp))
            Text(
                text = member.username,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.weight(1f))
            if (isOwner) {
                Text(
                    text = "Владелец",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(10.dp))
            }
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    modifier: Modifier = Modifier
) {
    val title = if (task.title.length > 24) task.title.take(24)+"..."
    else task.title
    val description = task.description?.length?.let {
        if (it > 60)
            task.description.take(60)+"..."
        else
            task.description
    }

    Card(
        modifier = modifier.height(80.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.height(80.dp).padding(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (task.startTime != null) {
                    TaskDate(task.startTime, Modifier.padding(4.dp))
                }
                if (task.endTime != null) {
                    TaskDate(task.endTime, Modifier.padding(4.dp))
                }
            }
            Column(
                modifier = Modifier.padding(8.dp).width(220.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.weight(2f))
            Checkbox(
                checked = task.type == TaskType.COMPLETED,
                onCheckedChange = null,
                modifier = Modifier
                    .size(32.dp),
                enabled = false,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.surface,
                    uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    checkmarkColor = MaterialTheme.colorScheme.primary,
                    disabledCheckedColor = MaterialTheme.colorScheme.surface,
                    disabledUncheckedColor = MaterialTheme.colorScheme.onSurface
                )
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
private fun TaskDate(
    time: LocalDateTime,
    modifier: Modifier = Modifier
) {
    val text = "${time.hour} : " + if (time.minute < 10) "0${time.minute}" else time.minute.toString()
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
fun ProjectScreenPreview() {
    ProjectDetailsScreen(project2.id, {}, {})
}