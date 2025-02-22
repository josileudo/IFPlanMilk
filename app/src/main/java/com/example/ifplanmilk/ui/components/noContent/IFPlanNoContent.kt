package com.example.ifplanmilk.ui.components.noContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.ifplanmilk.R
import com.example.ifplanmilk.ui.components.lottie.LoadingAnimation
import com.example.ifplanmilk.ui.theme.Typography

@Composable
fun IFPlanNoContent(
    modifier: Modifier = Modifier,
    title: String
) {
    Box {
        Text(
            text = title,
            style = Typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            ),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(top = 62.dp)
        )

        LoadingAnimation(
            modifier = modifier
                .fillMaxWidth(1f)
                .aspectRatio(1f),
            resInt = R.raw.no_items
        )
    }
}

@Preview
@Composable
fun IFPlanNoContentPreview() {
    IFPlanNoContent(title = "Não Contém itens")
}