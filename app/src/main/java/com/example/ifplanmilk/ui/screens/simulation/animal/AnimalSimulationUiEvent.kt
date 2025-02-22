package com.example.ifplanmilk.ui.screens.simulation.animal

sealed class AnimalSimulationUiEvent {
    data class OnUpdateAnimalFields(val field: String, val value: Double) : AnimalSimulationUiEvent()
    data class OnGetSimulation(val id: Long) : AnimalSimulationUiEvent()
}