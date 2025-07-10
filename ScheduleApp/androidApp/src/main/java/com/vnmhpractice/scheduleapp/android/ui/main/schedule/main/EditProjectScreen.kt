package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vnmhpractice.scheduleapp.android.data.local.ProjectData

@Composable
fun EditProjectScreen(
    projectId: String,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {

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