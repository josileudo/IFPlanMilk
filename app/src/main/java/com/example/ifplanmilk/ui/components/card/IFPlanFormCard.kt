package com.example.ifplanmilk.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ifplanmilk.data.model.IFPlanSeeItem
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun IFPlanFormCard(
    onFormClick: () -> Unit = {},
    items: List<IFPlanSeeItem> = emptyList()
) {
    Card {
        LazyColumn  {
            item {
                TextButton(onClick = { onFormClick }) {
                    Text(text = "Editar")
                }
            }
        }
    }
}

@Preview
@Composable
fun IFPlanFormCardPreview() {
    IFPlanMilkTheme {
        IFPlanFormCard()
    }
}