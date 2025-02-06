package com.example.ifplanmilk.ui.screens.Welcome

sealed class WelcomeUiEvent {
    data object OnCompleteOnboarding: WelcomeUiEvent()
    data object OnFetchOnboardingStatus: WelcomeUiEvent()
}