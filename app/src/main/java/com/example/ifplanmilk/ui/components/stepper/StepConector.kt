package com.example.ifplanmilk.ui.components.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun RowScope.StepConnector(
    modifier: Modifier = Modifier,
    isActive: Boolean = false
) {
    Box(
        modifier = modifier
            .weight(1f)
            .height(2.dp)
            .background(if (isActive) MaterialTheme.colorScheme.primary else Color.LightGray)
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun StepConnectorPreview() {
//    IFPlanMilkTheme {
//        StepConnector()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun StepConnectorPreviewIsActive() {
//    IFPlanMilkTheme {
//        StepConnector(isActive = true)
//    }
//}