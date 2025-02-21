package com.example.ifplanmilk.ui.screens.simulation.result

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import com.example.ifplanmilk.ui.components.print.ScreenshotComposable
import com.example.ifplanmilk.ui.components.simulation.result.ResultSimulationContainer
import com.example.ifplanmilk.ui.components.simulation.result.ResultSimulationHeader
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.sliders.SlidersSimulationScreen
import com.example.ifplanmilk.ui.screens.simulation.sliders.SlidersSimulationViewModel
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun ResultSimulationScreen(
    modifier: Modifier = Modifier,
    isDetails: Boolean = false,
    areaState: AreaSimulationUiState,
    animalState: AnimalSimulationUiState,
    economyState: EconomySimulationUiState,
    climateSoilState: ClimateSoilSimulationUiState,
    simulationTitle: String = "",
    description: String = "",
    simulationId: Long = -1,
    onNavigateToHome: () -> Unit = {},
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val resultSimulationViewModel: ResultSimulationViewModel = hiltViewModel()
    val uiState by resultSimulationViewModel.uiState.collectAsStateWithLifecycle()
    val onEvent = resultSimulationViewModel::onEvent
    val eventFlow = resultSimulationViewModel.eventFlow

    val slidersViewModel: SlidersSimulationViewModel = viewModel()
    val slidersState by slidersViewModel.uiState.collectAsStateWithLifecycle()

    val result = uiState.resultSimulation

    LaunchedEffect(slidersState) {
        onEvent(
            ResultSimulationUiEvent.OnResultSimulation(
                areaState = areaState,
                animalState = animalState,
                economyState = economyState,
                climateSoilState = climateSoilState,
                slidersState = slidersState
            )
        )
    }

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when (event) {
                is ResultSimulationUiEvent.OnSuccessSimulationSaved -> {
                    onNavigateToHome()
                }

                else -> {}
            }
        }
    }

    LaunchedEffect(simulationId) {
        if (simulationId != -1L) {
            onEvent(ResultSimulationUiEvent.OnGetResult(simulationId))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (!isDetails) {
                ResultSimulationHeader(
                    onClick = {
                        onEvent(
                            ResultSimulationUiEvent.OnSaveSimulation(
                                simulationTitle = simulationTitle,
                                description = description,
                                areaState = areaState,
                                animalState = animalState,
                                economyState = economyState,
                                slidersState = slidersState,
                                climateSoilState = climateSoilState
                            )
                        )
                    }
                )
            }

            ScreenshotComposable(
                isDetails = isDetails,
                simulationTitle = simulationTitle,
                onNavigateToHome = {
                    onNavigateToHome()
                }
            ) {
                ResultSimulationContainer(
                    modifier = modifier.fillMaxWidth(),
                    result = result
                )
            }
        }

        if(!isDetails) {
            Box {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Botão, posicionado na parte inferior do Box
                    IFPlanButton(
                        onClick = { showBottomSheet = true },
                        text = "Simular",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                            .zIndex(1f)
                    )

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.TopCenter)
                            .offset(y = (-20).dp)
                            .zIndex(1f)
                            .background(color = Color.White, shape = CircleShape)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.KeyboardArrowUp,
                            contentDescription = "Arrow Up",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        SlidersSimulationScreen(
            onDismiss = { showBottomSheet = false },
            modifier = Modifier.zIndex(5f),
            uiState = slidersState,
            onEvent = slidersViewModel::onEvent
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultSimulationPreview() {
    IFPlanMilkTheme {
        ResultSimulationScreen(
            modifier = Modifier.zIndex(5f),
            areaState = AreaSimulationUiState(),
            animalState = AnimalSimulationUiState(),
            economyState = EconomySimulationUiState(),
            climateSoilState = ClimateSoilSimulationUiState(),
        )
    }
}