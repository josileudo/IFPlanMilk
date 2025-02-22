package com.example.ifplanmilk.ui.screens.simulation

import com.example.ifplanmilk.data.model.IFPlanResultSimulation
import com.example.ifplanmilk.data.model.IFPlanSimulation

data class DetailsSimulationUiState(
    val simulation: IFPlanSimulation? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
