package com.example.ifplanmilk.ui.screens.welcome

sealed class WelcomeUiEvent {
    data object OnCompleteOnboarding: WelcomeUiEvent()
    data object OnFetchOnboardingStatus: WelcomeUiEvent()
}