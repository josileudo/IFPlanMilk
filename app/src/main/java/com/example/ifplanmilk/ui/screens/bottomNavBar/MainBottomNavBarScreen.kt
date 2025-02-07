package com.example.ifplanmilk.ui.screens.bottomNavBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifplanmilk.ui.route.BottomNavItem
import com.example.ifplanmilk.ui.screens.home.HomeScreen
import com.example.ifplanmilk.ui.screens.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MainBottomNavigationBar() {
    val bottomNavController = rememberNavController()

    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Simulations.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Simulations.route) {
                HomeScreen(
                    uiState = homeUiState,
                    onEvent = homeViewModel::onEvent
                )
            }
            composable(BottomNavItem.NewSimulation.route) { SearchScreen() }
            composable(BottomNavItem.Settings.route) { ProfileScreen() }
        }
    }
}