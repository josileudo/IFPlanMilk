package com.example.ifplanmilk.ui.components.stepper

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.data.model.IFPlanStepSimulation
import com.example.ifplanmilk.data.model.mock.IFPlanStepSimulationMock
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun IFPlanStepper(
    modifier: Modifier = Modifier,
    currentStep: Int = 0,
    steps: List<IFPlanStepSimulation>,
    content: @Composable () -> Unit = {}
) {
    var currStep by remember { mutableStateOf(currentStep) }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.forEachIndexed { index, step ->
                StepItem(
                    stepIndex = index,
                    label = step.title,
                    isComplete = index < currStep,
                    isActive = index == currStep
                )

                if(index < steps.lastIndex) {
                    StepConnector(isActive = index < currStep)
                }
            }
        }

        // Conteúdo da etapa atual
        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
            steps.getOrNull(currStep)?.content?.invoke()
        }

        Row(
            modifier = Modifier.fillMaxWidth(1f).padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IFPlanButton(
                text = "Voltar",
                size = "sm",
                enabled = currStep > 0,
                onClick = {
                    if (currStep > 0) {
                        currStep--
                    }
                }
            )

            IFPlanButton(
                text = if(currStep == steps.lastIndex) "Finalizar" else "Próximo",
                onClick = {
                    if(currStep < steps.lastIndex) {
                        // Navegue para a próxima etapa
                        currStep ++
                    }
                },
                size = "sm"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IFPlanStepperPreview() {
    IFPlanMilkTheme {
        IFPlanStepper(currentStep = 0, steps = IFPlanStepSimulationMock)
    }
}