package com.example.ifplanmilk.ui.screens.simulation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.example.ifplanmilk.data.model.IFPlanStepSimulation
import com.example.ifplanmilk.ui.components.print.ScreenshotComposable
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiEvent
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiEvent
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiEvent
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiEvent
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.result.ResultSimulationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsSimulationScreen(
    modifier: Modifier = Modifier,
    simulationId: Long,
    stepsSimulation: List<IFPlanStepSimulation>,
    simulationTitle: String,
    description: String,
    areaEvent: (AreaSimulationUiEvent) -> Unit = {},
    economyEvent: (EconomySimulationUiEvent) -> Unit = {},
    animalEvent: (AnimalSimulationUiEvent) -> Unit = {},
    climateSoilEvent: (ClimateSoilSimulationUiEvent) -> Unit = {},
    areaState: AreaSimulationUiState,
    animalState: AnimalSimulationUiState,
    economyState: EconomySimulationUiState,
    climateSoilState: ClimateSoilSimulationUiState,
    onEvent: (DetailsSimulationUiEvent) -> Unit = {},
    uiState: DetailsSimulationUiState,
    onNavigateToHome: () -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Entradas", "Resultado")

    LaunchedEffect(true) {
        onEvent(DetailsSimulationUiEvent.OnGetSimulation(simulationId))
//        areaEvent(AreaSimulationUiEvent.OnGetSimulation(simulationId))
//        economyEvent(EconomySimulationUiEvent.OnGetSimulation(simulationId))
//        animalEvent(AnimalSimulationUiEvent.OnGetSimulation(simulationId))
//        climateSoilEvent(ClimateSoilSimulationUiEvent.OnGetSimulation(simulationId))
    }

    Column() {
//        SingleChoiceSegmentedButtonRow(modifier = modifier.fillMaxWidth()) {
//            options.forEachIndexed { index, label ->
//                SegmentedButton(
//                    shape = SegmentedButtonDefaults.itemShape(
//                        index = index,
//                        count = options.size
//                    ),
//                    onClick = { selectedIndex = index },
//                    selected = index == selectedIndex,
//                    label = { Text(label) }
//                )
//            }
//        }

            ResultSimulationScreen(
                modifier = Modifier.graphicsLayer(),
                isDetails = true,
                simulationTitle = uiState.simulation?.title ?: "",
                description = uiState.simulation?.description ?: "",
                areaState = areaState,
                animalState = animalState,
                economyState = economyState,
                climateSoilState = climateSoilState,
                simulationId = simulationId,
                onNavigateToHome = {
                    onNavigateToHome()
                }

            )


//        when (selectedIndex) {
//            0 -> NewSimulationScreen(
//                simulationTitle = simulationTitle,
//                description = description,
//                stepsSimulation = stepsSimulation
//            )
//
//            1 ->
//
//            else -> {}
//        }
    }
}
