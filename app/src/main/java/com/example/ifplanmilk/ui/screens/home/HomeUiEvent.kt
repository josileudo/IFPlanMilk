package com.example.ifplanmilk.ui.screens.home

import com.example.ifplanmilk.data.model.IFPlanSimulation

sealed class HomeUiEvent {
    data class OnUpdateFields(val field: String, val value: String): HomeUiEvent()
    data object OnOpenModal: HomeUiEvent()
    data object OnCloseModal: HomeUiEvent()
    data object OnFetchSimulations: HomeUiEvent()
    data class OnDeleteSimulation(var simulation: IFPlanSimulation): HomeUiEvent()
    data object OnResetDialogForm: HomeUiEvent()
    data object OnSaveForm: HomeUiEvent()
    data class OnFetchSimulationByField(var filter: String) : HomeUiEvent()
    data class OnUpdateSearchQuery(val query: String) : HomeUiEvent()
}