package com.example.ifplanmilk.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplanmilk.data.repository.WelcomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    val welcomeRepository: WelcomeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(WelcomeUiState())
    val uiState: StateFlow<WelcomeUiState> = _uiState.asStateFlow()

    init {
        onEvent(WelcomeUiEvent.OnFetchOnboardingStatus)
    }

    fun onEvent(event: WelcomeUiEvent) {
        when(event) {
            is WelcomeUiEvent.OnCompleteOnboarding -> onCompleteOnboarding()
            is WelcomeUiEvent.OnFetchOnboardingStatus -> onFetchOnboardingStatus()
            else -> {}
        }
    }

    private fun onFetchOnboardingStatus() {
        viewModelScope.launch {
            welcomeRepository.readOnBoardingState().collect { completed ->
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    isCompleteOnboarding = completed
                )
            }
        }
    }

    private fun onCompleteOnboarding() {
        viewModelScope.launch {
            welcomeRepository.saveOnBoardingState(true)
            _uiState.value = _uiState.value.copy(
                isCompleteOnboarding = true
            )
        }
    }
}