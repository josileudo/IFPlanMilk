package com.example.ifplanmilk.ui.screens.simulation

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ifplanmilk.data.model.IFPlanSimulation
import com.example.ifplanmilk.data.model.IFPlanStepSimulation
import com.example.ifplanmilk.data.model.mock.IFPlanSimulationMock
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import com.example.ifplanmilk.ui.components.stepper.IFPlanStepper
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulation
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulation
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationScreen
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulation
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationViewModel
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import com.example.ifplanmilk.ui.theme.Typography

@Composable
fun NewSimulationScreen(
    modifier: Modifier = Modifier,
    simulationTitle: String,
    description: String = "",
    onNavigateToResult: (IFPlanSimulation) -> Unit = {}
) {
    val areaViewModel: AreaSimulationViewModel = viewModel()
    val areaState by areaViewModel.uiState.collectAsStateWithLifecycle()

    val animalViewModel: AnimalSimulationViewModel = viewModel()
    val animalState by animalViewModel.uiState.collectAsStateWithLifecycle()

    val economyViewModel: EconomySimulationViewModel = viewModel()
    val economyState by economyViewModel.uiState.collectAsStateWithLifecycle()

    val climateSoilSimulationViewModel: ClimateSoilSimulationViewModel = viewModel()
    val climateSoilSimulationState by climateSoilSimulationViewModel.uiState.collectAsStateWithLifecycle()

    val stepsSimulation = listOf(
        IFPlanStepSimulation(
            title = "Área",
            content = {
                AreaSimulation(
                    uiState = areaState,
                    onEvent = areaViewModel::onEvent
                )
            }
        ),
        IFPlanStepSimulation(
            title = "Animal",
            content = {
                AnimalSimulation(
                    uiState = animalState,
                    onEvent = animalViewModel::onEvent
                )
            }
        ),
        IFPlanStepSimulation(
            title = "Econômia",
            content = {
                EconomySimulation(
                    uiState = economyState,
                    onEvent = economyViewModel::onEvent
                )
            }
        ),
        IFPlanStepSimulation(
            title = "Clima e Solo",
            content = {
                ClimateSoilSimulationScreen(
                    uiState = climateSoilSimulationState,
                    onEvent = climateSoilSimulationViewModel::onEvent
                )
            }
        )
    )

    Box (
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
           Row(
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.fillMaxWidth()
           ){
               IFPlanButton(
                   onClick = { /*TODO*/ },
                   iconRes = Icons.Filled.ArrowBack
               )

               Spacer(modifier.padding(horizontal = 8.dp))

               Text(modifier = Modifier.weight(1f), maxLines = 1, text = simulationTitle, style = Typography.headlineMedium)
           }

            IFPlanStepper(
                steps = stepsSimulation,
                onFinished = {
                    val result = IFPlanSimulationMock[0]

                    onNavigateToResult(result)
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