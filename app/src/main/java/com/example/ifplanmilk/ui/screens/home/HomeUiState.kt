package com.example.ifplanmilk.ui.screens.home

import com.example.ifplanmilk.data.model.IFPlanSimulation

data class HomeUiState(
    var showDialog: Boolean = false,
    var formTitle: String = "",
    var formDescription: String = "",
    var title: String = "",
    var description: String = "",
    var simulationList: List<IFPlanSimulation> = emptyList()
)