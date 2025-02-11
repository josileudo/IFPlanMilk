package com.example.ifplanmilk.ui.screens.home

sealed class HomeUiEvent {
    data class OnUpdateFields(val field: String, val value: String): HomeUiEvent()
    data object OnOpenModal: HomeUiEvent()
    data object OnCloseModal: HomeUiEvent()
}