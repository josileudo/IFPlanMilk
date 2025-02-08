package com.example.ifplanmilk.ui.screens.simulation.area

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AreaSimulationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AreaSimulationUiState())
    val uiState: StateFlow<AreaSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: AreaSimulationUiEvent) {
        when(event) {
            is AreaSimulationUiEvent.OnSaveAreaForm -> saveAreaForm()
            is AreaSimulationUiEvent.OnUpdateAreaFields -> onUpdateAreaFields(
                field = event.field, value = event.value
            )
            else -> {}
        }
    }

    private fun onUpdateAreaFields(field: String, value: Double) {
        _uiState.update {
            when (field) {
                "area" -> it.copy(area = value)
                "picketsNumber" -> it.copy(picketsNumber = value)
                else -> it
            }
        }
    }

    private fun saveAreaForm() {
        _uiState.value = _uiState.value.copy(isSaving = true)
    }
}