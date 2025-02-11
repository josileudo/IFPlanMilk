package com.example.ifplanmilk.ui.components.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.data.model.IFPlanSeeItem
import com.example.ifplanmilk.data.model.mock.IFPlanSeeItemResultMock
import com.example.ifplanmilk.data.utils.currencyFormat
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun IFPlanSeeItemResult(
    modifier: Modifier = Modifier,
    item: IFPlanSeeItem
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
                )
            )

            Text(
                text = item.value.currencyFormat(decimals = 2),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
            )
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
private fun IFPlanSeeItemResultPreview() {
    IFPlanMilkTheme {
        IFPlanSeeItemResult(item = IFPlanSeeItemResultMock[0])
    }
}