package com.example.ifplanmilk.ui.screens.simulation.animal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AnimalSimulationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AnimalSimulationUiState())
    val uiState: StateFlow<AnimalSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: AnimalSimulationUiEvent) {
        when(event) {
            is AnimalSimulationUiEvent.OnUpdateAnimalFields -> onUpdateAnimalFields(
                field = event.field, value = event.value
            )
            else -> {}
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