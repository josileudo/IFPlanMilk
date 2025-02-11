package com.example.ifplanmilk.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when(event) {
            is HomeUiEvent.OnOpenModal -> onOpenModal()
            is HomeUiEvent.OnCloseModal -> onCloseModal()
            is HomeUiEvent.OnUpdateFields -> onUpdateFields(event.field, event.value)
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
                else -> it
            }
        }
    }
}