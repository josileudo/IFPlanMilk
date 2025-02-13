package com.example.ifplanmilk.ui.screens.simulation.sliders

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SlidersSimulationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SlidersSimulationUiState())
    val uiState: StateFlow<SlidersSimulationUiState> = _uiState.asStateFlow()

    fun onEvent(event: SlidersSimulationUiEvent) {
        when (event) {
            is SlidersSimulationUiEvent.onUpdateSlideFiled -> onUpdateSlideFiled(
                event.slide,
                event.value
            )

            else -> {}
        }
    }

    private fun onUpdateSlideFiled(slide: String, value: Float) {
        _uiState.update {
            when (slide) {
                "COE" -> it.copy(sliderCoeValue = value)
                "DPL" -> it.copy(sliderDplValue = value)
                "FOR" -> it.copy(sliderForValue = value)
                "MS" -> it.copy(sliderMsValue = value)
                "PRECO" -> it.copy(sliderPrecoValue = value)
                else -> it
            }
        }
    }
}