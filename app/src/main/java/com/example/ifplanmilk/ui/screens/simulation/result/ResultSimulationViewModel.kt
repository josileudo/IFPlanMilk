package com.example.ifplanmilk.ui.screens.simulation.result

import androidx.lifecycle.ViewModel
import com.example.ifplanmilk.data.utils.SimulationMath
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.sliders.SlidersSimulationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ResultSimulationViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(ResultSimulationUiState())
    var uiState: StateFlow<ResultSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: ResultSimulationUiEvent) {
        when (event) {
            is ResultSimulationUiEvent.OnResultSimulation -> onResultSimulation(
                areaState = event.areaState,
                animalState = event.animalState,
                economyState = event.economyState,
                climateSoilState = event.climateSoilState,
                slidersState = event.slidersState
            )

            else -> {}
        }
    }

    private fun onResultSimulation(
        areaState: AreaSimulationUiState,
        animalState: AnimalSimulationUiState,
        economyState: EconomySimulationUiState,
        climateSoilState: ClimateSoilSimulationUiState,
        slidersState: SlidersSimulationUiState
    ) {
        val result = SimulationMath().simulate(
            areaState = areaState,
            animalState = animalState,
            economyState = economyState,
            climateSoilState = climateSoilState,
            slidersState = slidersState,
        )
        println("*** RESULTADO DA SIMULAÇÃO *** ${result}")

        _uiState.update {
            it.copy(resultSimulation = result)
        }
    }
}