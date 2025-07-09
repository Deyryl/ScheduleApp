package com.vnmhpractice.scheduleapp.android.ui.main.schedule.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import com.vnmhpractice.scheduleapp.android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.datasource.ScheduleItem

@Composable
fun MainCard(
    scheduleItem: ScheduleItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            ScheduleImage()
            Column {
                Text(
                    text = scheduleItem.title,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_card_more),
                contentDescription = stringResource(R.string.card_menu),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
private fun ScheduleImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int? = null
) {
    Box(
        modifier = modifier
            .size(90.dp)
            .clip(MaterialTheme.shapes.medium)
            .padding(end = 10.dp)
    ) {
        if (imageRes == null) {
            Icon(
                painter = painterResource(R.drawable.ic_image),
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

@Preview
@Composable
fun MainCardPreview() {
    val item = ScheduleItem(id = 0, title = "First project")
    MainCard(item)
}