package com.example.ifplanmilk.ui.screens.simulation.area

sealed class AreaSimulationUiEvent {
    data object OnSaveAreaForm: AreaSimulationUiEvent()
    data class OnUpdateAreaFields(var field: String, var value: Double) : AreaSimulationUiEvent()
}
