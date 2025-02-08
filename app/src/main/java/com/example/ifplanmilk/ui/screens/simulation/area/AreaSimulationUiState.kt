package com.example.ifplanmilk.ui.screens.simulation.area

data class AreaSimulationUiState(
    val area: Double = 0.0,
    val picketsNumber: Double = 0.0,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false
)
