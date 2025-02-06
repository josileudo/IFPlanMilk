package com.example.ifplanmilk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifplanmilk.ui.route.Welcome
import com.example.ifplanmilk.ui.screens.Welcome.WelcomeScreen
import com.example.ifplanmilk.ui.screens.Welcome.WelcomeViewModel
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ifplanmilk.ui.route.Home
import com.example.ifplanmilk.ui.route.Splash
import com.example.ifplanmilk.ui.screens.Home.HomeScreen
import com.example.ifplanmilk.ui.screens.splash.SplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IFPlanMilkTheme {
                val navController = rememberNavController()

                val welcomeViewModel: WelcomeViewModel = hiltViewModel()
                val welcomeUiState by welcomeViewModel.uiState.collectAsStateWithLifecycle()

                NavHost(
                    modifier = Modifier.padding(),
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            uiState = welcomeUiState,
                            onNavigateToNext = {
                                if (welcomeUiState.isCompleteOnboarding) {
                                    navController.navigate(Home) {
                                        popUpTo(Splash) { inclusive = true }
                                    }
                                } else {
                                    navController.navigate(Welcome) {
                                        popUpTo(Splash) { inclusive = true }
                                    }
                                }
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(
                            onNavigateToHome = { navController.navigate(Home) },
                            uiState = welcomeUiState,
                            onEvent = welcomeViewModel::onEvent
                        )
                    }
                    composable<Home> {
                        HomeScreen()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IFPlanMilkTheme {

    }
}
