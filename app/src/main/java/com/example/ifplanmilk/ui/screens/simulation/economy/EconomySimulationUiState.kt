package com.example.ifplanmilk.ui.screens.simulation.economy

data class EconomySimulationUiState(
    val isLoading: Boolean = false,
    val investmentsPerLiters: Double = 0.0,
    val familyIncome: Double = 0.0,
    val depreciationRate: Double = 0.0,
)
