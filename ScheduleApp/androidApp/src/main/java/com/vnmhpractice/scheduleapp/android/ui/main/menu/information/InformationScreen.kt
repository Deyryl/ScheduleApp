package com.vnmhpractice.scheduleapp.android.ui.main.menu.information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnmhpractice.scheduleapp.android.R
import com.vnmhpractice.scheduleapp.android.ui.components.AppTitle

@Composable
fun InformationScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.small_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        AppTitle()
        Spacer(Modifier.height(60.dp))
        InformationText("Свяжитесь с нами:", MaterialTheme.colorScheme.onBackground)
        InformationText("o736b25@voenmeh.ru", MaterialTheme.colorScheme.primary)
        InformationText(
            "БГТУ \"ВОЕНМЕХ\" им Д. Ф. Устинова",
            MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(50.dp))
        InformationText(
            "УСЛОВИЯ ОБСЛУЖИВАНИЯ",
            MaterialTheme.colorScheme.primary
        )
        InformationText(
            "ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ",
            MaterialTheme.colorScheme.primary
        )
        InformationText(
            "ОТКРЫТОЕ ПРОГРАММНОЕ ОБЕСПЕЧЕНИЕ",
            MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun InformationText(
    text: String,
    color: Color
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Preview
@Composable
fun InformationScreenPreview() {
    InformationScreen()
}