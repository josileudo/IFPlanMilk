package com.example.ifplanmilk.ui.screens.simulation.climateSoil

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ClimateSoilSimulationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ClimateSoilSimulationUiState())
    val uiState: StateFlow<ClimateSoilSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: ClimateSoilSimulationUiEvent) {
        when (event) {
            is ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields -> onUpdateClimateSoilFields(
                event.field,
                event.value
            )
        }
    }

    private fun onUpdateClimateSoilFields(field: String, value: Double) {
        when (field) {
            "precipitation" -> _uiState.value = _uiState.value.copy(precipitation = value)
            "maxTemperature" -> _uiState.value = _uiState.value.copy(maxTemperature = value)
            "minTemperature" -> _uiState.value = _uiState.value.copy(minTemperature = value)
            "relativeHumidity" -> _uiState.value = _uiState.value.copy(relativeHumidity = value)
            "velocityVents" -> _uiState.value = _uiState.value.copy(velocityVents = value)
            "nDosage" -> _uiState.value = _uiState.value.copy(nDosage = value)
            "otherAndWater" -> _uiState.value = _uiState.value.copy(otherAndWater = value)
            "waterAvailableToIrrigation" -> _uiState.value = _uiState.value.copy(waterAvailableToIrrigation = value)
        }
    }
}