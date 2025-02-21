package com.example.ifplanmilk.ui.screens.simulation.animal

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
class AnimalSimulationViewModel @Inject constructor(
    private var repository: SimulationRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AnimalSimulationUiState())
    val uiState: StateFlow<AnimalSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: AnimalSimulationUiEvent) {
        when(event) {
            is AnimalSimulationUiEvent.OnUpdateAnimalFields -> onUpdateAnimalFields(
                field = event.field, value = event.value
            )
            is AnimalSimulationUiEvent.OnGetSimulation -> getSimulation(id = event.id)
            else -> {}
        }
    }

    private fun getSimulation(id: Long) {
        viewModelScope.launch { Dispatchers.IO
            val simulation = repository.getSimulationById(id)?.simulation
            _uiState.update {
                it.copy(
                    pesoCorporal = simulation?.pesoCorporal ?: 0.0,
                    milkProduction = simulation?.milkProduction ?: 0.0,
                    milkFatContent = simulation?.milkFatContent ?: 0.0,
                    pbFatMilk = simulation?.pbFatMilk ?: 0.0,
                    horizontalShift = simulation?.horizontalShift ?: 0.0,
                    verticalShift = simulation?.verticalShift ?: 0.0,
                    lactatingCows = simulation?.lactatingCows ?: 0.0
                )
            }
        }
    }

    private fun onUpdateAnimalFields(field: String, value: Double) {
        _uiState.update {
            when (field) {
                "pesoCorporal" -> it.copy(pesoCorporal = value)
                "milkProduction" -> it.copy(milkProduction = value)
                "milkFatContent" -> it.copy(milkFatContent = value)
                "pbFatMilk" -> it.copy(pbFatMilk = value)
                "horizontalShift" -> it.copy(horizontalShift = value)
                "verticalShift" -> it.copy(verticalShift = value)
                "lactatingCows" -> it.copy(lactatingCows = value)          
             else -> it
            }
        }
    }
}