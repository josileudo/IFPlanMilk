package com.example.ifplanmilk.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ifplanmilk.R
import com.example.ifplanmilk.ui.screens.welcome.WelcomeUiState
import com.example.ifplanmilk.ui.theme.GreenLight
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    uiState: WelcomeUiState,
    onNavigateToNext: () -> Unit,
) {
    LaunchedEffect(uiState.isLoading) {
        delay(3_000)
        onNavigateToNext()
    }

    Box(
        modifier = modifier
            .background(GreenLight)
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.onboarding_logo),
            contentDescription = "Imagem Background"
        )

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.grass),
            contentDescription = "Imagem Background"
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(uiState = WelcomeUiState() , onNavigateToNext = {})
}