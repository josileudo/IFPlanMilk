package com.example.ifplanmilk.ui.screens.simulation.sliders

sealed class SlidersSimulationUiEvent {
    data class onUpdateSlideFiled(var slide: String, var value: Float) : SlidersSimulationUiEvent()
}