package com.example.ifplanmilk.ui.screens.simulation.climateSoil

sealed class ClimateSoilSimulationUiEvent {
    data class OnUpdateClimateSoilFields(val field: String, val value: Double): ClimateSoilSimulationUiEvent()
}