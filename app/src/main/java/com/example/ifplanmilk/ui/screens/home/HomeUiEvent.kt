package com.example.ifplanmilk.ui.screens.home

sealed class HomeUiEvent {
    data object OnOpenModal: HomeUiEvent()
    data object OnCloseModal: HomeUiEvent()
}