package com.example.ifplanmilk.ui.screens.simulation.climateSoil

data class ClimateSoilSimulationUiState(
    val isLoading: Boolean = false,
    val precipitation: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val minTemperature: Double = 0.0,
    val relativeHumidity: Double = 0.0,
    val velocityVents: Double = 0.0,
    val nDosage: Double = 0.0,
    val otherAndWater: Double = 0.0,
    val waterAvailableToIrrigation: Double = 0.0,
)