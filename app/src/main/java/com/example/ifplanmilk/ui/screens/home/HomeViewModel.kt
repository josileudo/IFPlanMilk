package com.example.ifplanmilk.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity
import com.example.ifplanmilk.data.model.IFPlanSimulation
import com.example.ifplanmilk.data.repository.SimulationRepository
import com.example.ifplanmilk.data.utils.toDomain
import com.example.ifplanmilk.data.utils.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var repository: SimulationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when(event) {
            is HomeUiEvent.OnOpenModal -> onOpenModal()
            is HomeUiEvent.OnCloseModal -> onCloseModal()
            is HomeUiEvent.OnUpdateFields -> onUpdateFields(event.field, event.value)
            is HomeUiEvent.OnFetchSimulations -> onFetchSimulations()
            is HomeUiEvent.OnDeleteSimulation -> onDeleteSimulation(event.simulation)
            is HomeUiEvent.OnResetDialogForm -> onResetDialogForm()
            is HomeUiEvent.OnSaveForm -> onSaveForm()
            else -> {}
        }
    }

    private fun onOpenModal() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                showDialog = true
            )
        }
    }

    private fun onCloseModal() {
        viewModelScope.launch {
           _uiState.value = _uiState.value.copy(
               showDialog = false
           )
        }
    }

    private fun onUpdateFields(field: String, value: String) {
        _uiState.update {
            when(field) {
                "title" -> it.copy(title = value)
                "description" -> it.copy(description = value)
                "formTitle" -> it.copy(formTitle = value)
                "formDescription" -> it.copy(formDescription = value)
                else -> it
            }
        }
    }

    private fun onFetchSimulations() {
        viewModelScope.launch {
            val simulationWithResults = repository.getAllSimulations()
            val simulations = simulationWithResults.map { it.simulation.toDomain() }

            _uiState.value = _uiState.value.copy(
                simulationList = simulations
            )
        }
    }

    private fun onDeleteSimulation(delSimulation: IFPlanSimulation) {
        viewModelScope.launch {
            try {
                repository.deleteSimulation(delSimulation.toEntity())
                onFetchSimulations()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun onSaveForm() {
        _uiState.update {
            it.copy(
                title = it.formTitle,
                description = it.formDescription
            )
        }
    }

    private fun onResetDialogForm() {
        viewModelScope.launch {
            delay(3.seconds)
            _uiState.update {
                it.copy(
                    formTitle = "",
                    formDescription = ""
                )
            }
        }
    }
}
