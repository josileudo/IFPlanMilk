package com.example.ifplanmilk.ui.screens.simulation.economy

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
class EconomySimulationViewModel @Inject constructor(
    var repository: SimulationRepository
): ViewModel() {
    private var _uiState = MutableStateFlow(EconomySimulationUiState())
    val uiState: StateFlow<EconomySimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: EconomySimulationUiEvent) {
        when (event) {
            is EconomySimulationUiEvent.OnUpdateEconomyFields -> onUpdateEconomyFields(event.field, event.value)
            is EconomySimulationUiEvent.OnGetSimulation -> getSimulation(event.id)
            else -> {}
        }
    }

    private fun onUpdateEconomyFields(field: String, value: Double) {
        when (field) {
            "investmentsPerLiters" -> _uiState.value = _uiState.value.copy(investmentsPerLiters = value)
            "familyIncome" -> _uiState.value = _uiState.value.copy(familyIncome = value)
            "depreciationRate" -> _uiState.value = _uiState.value.copy(depreciationRate = value)
        }
    }

    private fun getSimulation(id: Long) {
        viewModelScope.launch {
            val simulation = repository.getSimulationById(id)?.simulation
            _uiState.value = _uiState.value.copy(
                investmentsPerLiters = simulation?.investmentsPerLiters ?: 0.0,
                familyIncome = simulation?.familyIncome ?: 0.0,
                depreciationRate = simulation?.depreciationRate ?: 0.0
            )
        }
    }
}