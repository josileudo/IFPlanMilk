package com.example.ifplanmilk.ui.screens.simulation.economy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EconomySimulationViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(EconomySimulationUiState())
    val uiState: StateFlow<EconomySimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: EconomySimulationUiEvent) {
        when (event) {
            is EconomySimulationUiEvent.OnUpdateEconomyFields -> onUpdateEconomyFields(event.field, event.value)
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
}