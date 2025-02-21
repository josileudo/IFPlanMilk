package com.example.ifplanmilk.ui.screens.simulation.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplanmilk.data.repository.SimulationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaSimulationViewModel @Inject constructor(
    var repository: SimulationRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AreaSimulationUiState())
    val uiState: StateFlow<AreaSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: AreaSimulationUiEvent) {
        when(event) {
            is AreaSimulationUiEvent.OnSaveAreaForm -> saveAreaForm()
            is AreaSimulationUiEvent.OnUpdateAreaFields -> onUpdateAreaFields(
                field = event.field, value = event.value
            )
            is AreaSimulationUiEvent.OnGetSimulation -> getSimulation(id = event.id)
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

    private fun getSimulation(id: Long) {
        viewModelScope.launch { Dispatchers.IO
           val simulation = repository.getSimulationById(id)?.simulation
            _uiState.update {
                it.copy(
                    area = simulation?.area ?: 0.0,
                    picketsNumber = simulation?.picketsNumber ?: 0.0
                )
            }
        }
    }

    private fun saveAreaForm() {
        _uiState.value = _uiState.value.copy(isSaving = true)
    }
}