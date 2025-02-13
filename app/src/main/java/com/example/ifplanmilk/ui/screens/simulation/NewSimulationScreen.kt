package com.example.ifplanmilk.ui.screens.simulation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.data.model.IFPlanStepSimulation
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import com.example.ifplanmilk.ui.components.stepper.IFPlanStepper
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import com.example.ifplanmilk.ui.theme.Typography

@Composable
fun NewSimulationScreen(
    modifier: Modifier = Modifier,
    simulationTitle: String,
    description: String = "",
    stepsSimulation: List<IFPlanStepSimulation> = emptyList(),
    onNavigateToResult: () -> Unit = {}
) {

    Box(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IFPlanButton(
                    onClick = { /*TODO*/ },
                    iconRes = Icons.Filled.ArrowBack
                )

                Spacer(modifier.padding(horizontal = 8.dp))

                Text(
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    text = simulationTitle,
                    style = Typography.headlineMedium
                )
            }

            IFPlanStepper(
                steps = stepsSimulation,
                onFinished = {
                    onNavigateToResult()
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewSimulationScreenPreview() {
    IFPlanMilkTheme {
        NewSimulationScreen(
            simulationTitle = "Exemplo",
        )
    }
}