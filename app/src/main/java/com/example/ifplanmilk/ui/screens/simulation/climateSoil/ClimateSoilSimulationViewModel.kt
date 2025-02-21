package com.example.ifplanmilk.ui.screens.simulation.climateSoil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplanmilk.data.repository.SimulationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClimateSoilSimulationViewModel @Inject constructor(
   var repository: SimulationRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ClimateSoilSimulationUiState())
    val uiState: StateFlow<ClimateSoilSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: ClimateSoilSimulationUiEvent) {
        when (event) {
            is ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields -> onUpdateClimateSoilFields(
                event.field,
                event.value
            )
            is ClimateSoilSimulationUiEvent.OnGetSimulation -> getSimulation(event.id)
        }
    }

    private fun getSimulation(id: Long) {
        viewModelScope.launch {
            val simulation = repository.getSimulationById(id)?.simulation
            _uiState.value = _uiState.value.copy(
                precipitation = simulation?.precipitation ?: 0.0,
                maxTemperature = simulation?.maxTemperature ?: 0.0,
                minTemperature = simulation?.minTemperature ?: 0.0,
                relativeHumidity = simulation?.relativeHumidity ?: 0.0,
                velocityVents = simulation?.velocityVents ?: 0.0,
                nDosage = simulation?.nDosage ?: 0.0,
                otherAndWater = simulation?.otherAndWater ?: 0.0
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