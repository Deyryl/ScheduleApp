package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.datasource.projects
import com.vnmhpractice.scheduleapp.android.data.model.Project

@Composable
fun MainCard(
    project: Project,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {},
    onPinClick: () -> Unit = {},
    onCardClick: () -> Unit = {}
) {
    val title = if (project.title.length > 16)
        project.title.take(16)+"..."
    else
        project.title

    Card(
        modifier = modifier.height(100.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            ScheduleImage()
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Участники: ${project.members.size}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                MoreButton(
                    onEditClick = onEditClick,
                    onPinClick = onPinClick,
                    isPinned = project.isPinned
                )
                Spacer(Modifier.weight(1f))
                if (project.isPinned) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pin),
                        contentDescription = "Закреплен",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(end = 9.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ScheduleImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int? = null
) {// я хуесос
    Box(
        modifier = modifier
            .size(90.dp)
            .clip(MaterialTheme.shapes.medium)
            .padding(end = 10.dp)
    ) {
        if (imageRes == null) {
            Icon(
                painter = painterResource(R.drawable.ic_default_project),
                contentDescription = stringResource(R.string.lack_of_image),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = painterResource(imageRes),
                contentDescription = stringResource(R.string.project_image),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun MoreButton(
    onEditClick: () -> Unit,
    onPinClick: () -> Unit,
    isPinned: Boolean,
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
                        text = "Изменить",
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                onClick = onEditClick
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = if (isPinned) "Открепить" else "Закрепить",
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                onClick = onPinClick
            )
        }
    }
}

@Preview
@Composable
fun MainCardPreview() {
    val item = projects[1]
    MainCard(item)
}