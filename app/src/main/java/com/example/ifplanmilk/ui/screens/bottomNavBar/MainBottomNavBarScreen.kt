package com.example.ifplanmilk.ui.screens.bottomNavBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ifplanmilk.ui.route.BottomNavItem
import com.example.ifplanmilk.ui.screens.simulation.NewSimulationScreen
import com.example.ifplanmilk.ui.screens.home.HomeScreen
import com.example.ifplanmilk.ui.screens.home.HomeViewModel

@Composable
fun MainBottomNavigationBar() {
    val bottomNavController = rememberNavController()
    val currentBackStacksEntry = bottomNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStacksEntry.value?.destination?.route

    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (
        bottomBar = {
            if(currentRoute in listOf(
                BottomNavItem.Simulations.route,
//                BottomNavItem.NewSimulation.route,
                BottomNavItem.Settings.route
            )) {
                BottomNavigationBar(navController = bottomNavController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Simulations.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Simulations.route) {
                HomeScreen(
                    uiState = homeUiState,
                    onEvent = homeViewModel::onEvent,
                    onNavigateToNewSimulation = {
                        bottomNavController.navigate(BottomNavItem.NewSimulation.route)
                    }
                )
            }
            composable(BottomNavItem.NewSimulation.route) { NewSimulationScreen() }
            composable(BottomNavItem.Settings.route) { ProfileScreen() }
        }
    }
}