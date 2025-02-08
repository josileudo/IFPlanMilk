package com.example.ifplanmilk.ui.screens.simulation.animal

data class AnimalSimulationUiState (
    val isLoading: Boolean = false,
    val pesoCorporal: Double = 0.0,
    val milkProduction: Double = 0.0,
    val milkFatContent: Double = 0.0,
    val pbFatMilk: Double = 0.0,
    val horizontalShift: Double = 0.0,
    val verticalShift: Double = 0.0,
    val lactatingCows: Double = 0.0,
)