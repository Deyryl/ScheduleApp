package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData
import com.vnmhpractice.scheduleapp.android.ui.components.PrimaryTextField

@Composable
fun EditProjectScreen(
    projectId: String,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val project = viewModel.getProjectById(projectId)

    var title by rememberSaveable { mutableStateOf(project.title) }
    var selectedImageUri by rememberSaveable { mutableStateOf(project.image) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedImageUri = it }
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            else -> {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    EditingText(
                        text = "Отменить",
                        onClick = onCancelClick
                    )
                    Spacer(Modifier.weight(1f))
                    EditingText(
                        text = "Сохранить",
                        onClick = {
                            val editProject = project.copy(title = title, image = selectedImageUri)
                            viewModel.saveProjectChanges(editProject)
                            onSaveClick()
                        }
                    )
                }
                Spacer(Modifier.height(40.dp))
                Column(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        )
                ) {
                    if (selectedImageUri == null) {
                        Image(
                            painter = painterResource(R.drawable.ic_add_image),
                            contentDescription = "Выбрать фото для проекта",
                            modifier = Modifier
                                .size(180.dp)
                                .padding(bottom = 16.dp)
                        )
                    } else {
                        AsyncImage(
                            modifier = Modifier
                                .size(180.dp)
                                .padding(bottom = 16.dp),
                            model = selectedImageUri,
                            contentDescription = "Выбрать фото для проекта",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Добавить фото",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(60.dp))
                PrimaryTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = "Название проекта",
                    imeAction = ImeAction.Done,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun EditingText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(10.dp)
    )
}

@Preview
@Composable
fun EditProjectScreenPreview() {
    ProjectData.initTestData()
    EditProjectScreen(
        projectId = ProjectData.getAllProjects().first().id,
        onCancelClick = {},
        onSaveClick = {}
    )
}