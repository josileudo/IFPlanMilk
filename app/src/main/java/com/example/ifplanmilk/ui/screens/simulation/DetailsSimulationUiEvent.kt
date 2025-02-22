package com.example.ifplanmilk.ui.screens.simulation

sealed class DetailsSimulationUiEvent {
    data class OnGetSimulation(val simulationId: Long) : DetailsSimulationUiEvent()
}
