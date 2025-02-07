package com.example.ifplanmilk.ui.components.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import com.example.ifplanmilk.ui.theme.Typography

@Composable
fun StepItem(
    modifier: Modifier = Modifier,
    stepIndex: Int,
    isComplete: Boolean = false,
    isActive: Boolean = false,
    label: String = "",
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp),
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(
                when {
                    isComplete -> MaterialTheme.colorScheme.primary
                    isActive -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                }
            ),
            contentAlignment = Alignment.Center
        ) {
            if(isComplete) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
            } else {
                Text(text = "${stepIndex + 1}", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            modifier = Modifier.widthIn(max = 60.dp),
            textAlign = TextAlign.Center,
            text = label,
            style = if(isActive || isComplete) Typography.titleSmall
            else Typography.titleSmall.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun StepItemPreview() {
    IFPlanMilkTheme {
        StepItem(stepIndex = 0, label = "Step 1")
    }
}

@Preview(showBackground = true)
@Composable
private fun StepItemIsActivePreview() {
    IFPlanMilkTheme {
        StepItem(stepIndex = 0, label = "Step 1", isActive = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun StepItemIsCompletedPreview() {
    IFPlanMilkTheme {
        StepItem(stepIndex = 0, label = "Step 1", isComplete = true)
    }
}