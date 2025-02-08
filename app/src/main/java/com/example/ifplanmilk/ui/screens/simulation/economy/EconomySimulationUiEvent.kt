package com.example.ifplanmilk.ui.screens.simulation.economy

sealed class EconomySimulationUiEvent {
    data class OnUpdateEconomyFields(val field: String, val value: Double): EconomySimulationUiEvent()
}