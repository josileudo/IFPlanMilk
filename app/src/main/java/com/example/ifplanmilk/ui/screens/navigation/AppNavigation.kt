package com.example.ifplanmilk.ui.screens.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifplanmilk.ui.route.Routes
import com.example.ifplanmilk.ui.screens.welcome.WelcomeScreen
import com.example.ifplanmilk.ui.screens.welcome.WelcomeViewModel
import com.example.ifplanmilk.ui.screens.bottomNavBar.MainBottomNavigationBar
import com.example.ifplanmilk.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val welcomeViewModel: WelcomeViewModel = hiltViewModel()
    val welcomeUiState by welcomeViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        modifier = Modifier.padding(),
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable(route = Routes.Splash) {
            SplashScreen(
                uiState = welcomeUiState,
                onNavigateToNext = {
                    if (welcomeUiState.isCompleteOnboarding) {
                        navController.navigate(Routes.Navigation) {
                            popUpTo(Routes.Splash) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Routes.Welcome) {
                            popUpTo(Routes.Splash) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Routes.Welcome) {
            WelcomeScreen(
                onNavigateToHome = { navController.navigate(Routes.Navigation) },
                uiState = welcomeUiState,
                onEvent = welcomeViewModel::onEvent
            )
        }
        composable(Routes.Navigation) {
            MainBottomNavigationBar()
        }
    }
}