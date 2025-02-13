package com.example.ifplanmilk.ui.screens.simulation.result

import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.sliders.SlidersSimulationUiState

sealed class ResultSimulationUiEvent {
    data class OnResultSimulation(
        var areaState: AreaSimulationUiState,
        var animalState: AnimalSimulationUiState,
        var economyState: EconomySimulationUiState,
        var climateSoilState: ClimateSoilSimulationUiState,
        var slidersState: SlidersSimulationUiState
    ) : ResultSimulationUiEvent()
}