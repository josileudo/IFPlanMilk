package com.example.ifplanmilk.ui.screens.simulation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ifplanmilk.data.model.IFPlanStepSimulation
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

@Composable
fun NewSimulationScreen() {
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

    IFPlanStepper(
        modifier = Modifier.padding(8.dp),
        steps = stepsSimulation,
        onFinished = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewSimulationScreenPreview() {
    IFPlanMilkTheme {
        NewSimulationScreen()
    }
}