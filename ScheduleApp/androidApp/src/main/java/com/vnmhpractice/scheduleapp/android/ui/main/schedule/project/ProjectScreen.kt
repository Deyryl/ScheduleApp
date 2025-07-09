package com.vnmhpractice.scheduleapp.android.ui.main.schedule.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData

@Composable
fun ProjectScreen(
    projectId: String,
    modifier: Modifier = Modifier,
    onNavigateToDetails: () -> Unit = {}
) {
    val project = remember(projectId) {
        ProjectData.getProjectById(projectId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (project != null) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "Members: ${project.members.size}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Количество задач
            if (project.tasks?.isNotEmpty() == true) {
                Text(
                    text = "Tasks: ${project.tasks.size}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Закрепленный проект
            if (project.isPinned) {
                Text(
                    text = "Pinned",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToDetails,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Details")
            }
        } else {
            Text(
                text = "Project not found",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview
@Composable
fun ProjectScreenPreview() {
    ProjectData.initTestData()
    ProjectScreen(projectId = "1")
}