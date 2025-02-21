package com.example.ifplanmilk.ui.screens.simulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplanmilk.data.repository.SimulationRepository
import com.example.ifplanmilk.data.utils.toDomain
import com.example.ifplanmilk.ui.screens.simulation.result.ResultSimulationUiEvent
import com.example.ifplanmilk.ui.screens.simulation.result.ResultSimulationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsSimulationViewModel @Inject constructor(
    private val repository: SimulationRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(DetailsSimulationUiState())
    var uiState: StateFlow<DetailsSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: DetailsSimulationUiEvent) {
        when (event) {
            is DetailsSimulationUiEvent.OnGetSimulation -> getSimulation(event.simulationId)
            else -> {}
        }
    }


    private fun getSimulation(simulationId: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val simulation = repository.getSimulationById(simulationId)?.simulation
                simulation?.let {
                    _uiState.value = _uiState.value.copy(
                        simulation = simulation.toDomain(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

}



