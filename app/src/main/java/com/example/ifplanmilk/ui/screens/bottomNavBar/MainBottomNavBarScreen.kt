package com.example.ifplanmilk.ui.screens.bottomNavBar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ifplanmilk.data.model.IFPlanStepSimulation
import com.example.ifplanmilk.ui.route.BottomNavItem
import com.example.ifplanmilk.ui.route.Routes
import com.example.ifplanmilk.ui.screens.home.HomeScreen
import com.example.ifplanmilk.ui.screens.home.HomeViewModel
import com.example.ifplanmilk.ui.screens.simulation.DetailsSimulationScreen
import com.example.ifplanmilk.ui.screens.simulation.DetailsSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.NewSimulationScreen
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulation
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulation
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationScreen
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulation
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationViewModel
import com.example.ifplanmilk.ui.screens.simulation.result.ResultSimulationScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainBottomNavigationBar() {
    val bottomNavController = rememberNavController()
    val currentBackStacksEntry = bottomNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStacksEntry.value?.destination?.route

    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    val areaViewModel: AreaSimulationViewModel = hiltViewModel()
    val areaState by areaViewModel.uiState.collectAsStateWithLifecycle()

    val animalViewModel: AnimalSimulationViewModel = hiltViewModel()
    val animalState by animalViewModel.uiState.collectAsStateWithLifecycle()

    val economyViewModel: EconomySimulationViewModel = hiltViewModel()
    val economyState by economyViewModel.uiState.collectAsStateWithLifecycle()

    val climateSoilSimulationViewModel: ClimateSoilSimulationViewModel = hiltViewModel()
    val climateSoilSimulationState by climateSoilSimulationViewModel.uiState.collectAsStateWithLifecycle()

    val detailsSimulationViewModel: DetailsSimulationViewModel = hiltViewModel()
    val detailsSimulationState by detailsSimulationViewModel.uiState.collectAsStateWithLifecycle()

    val stepsSimulation = listOf(
        IFPlanStepSimulation(
            title = "Área",
            content = {
                AreaSimulation(
                    uiState = areaState,
                    onEvent = areaViewModel::onEvent
                )
            }
        ),
        IFPlanStepSimulation(
            title = "Animal",
            content = {
                AnimalSimulation(
                    uiState = animalState,
                    onEvent = animalViewModel::onEvent
                )
            }
        ),
        IFPlanStepSimulation(
            title = "Econômia",
            content = {
                EconomySimulation(
                    uiState = economyState,
                    onEvent = economyViewModel::onEvent
                )
            }
        ),
        IFPlanStepSimulation(
            title = "Clima e Solo",
            content = {
                ClimateSoilSimulationScreen(
                    uiState = climateSoilSimulationState,
                    onEvent = climateSoilSimulationViewModel::onEvent
                )
            }
        )
    )
    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    BottomNavItem.Simulations.route,
                    BottomNavItem.Settings.route
                )
            ) {
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
                        bottomNavController.navigate(Routes.NewSimulation)
                    },
                    onNavigateToDetails = { simulationId ->
                        bottomNavController.navigate("${Routes.DetailsSimulation}/$simulationId")
                    }
                )
            }
            composable(BottomNavItem.Settings.route) { ProfileScreen() }
            composable(Routes.NewSimulation) {
                NewSimulationScreen(
                    simulationTitle = homeUiState.title,
                    description = homeUiState.description,
                    stepsSimulation = stepsSimulation,
                    onNavigateToResult = {
                        bottomNavController.navigate(Routes.ResultSimulation)
                    },
                    onNavigateToHome = {
                        bottomNavController.navigate(BottomNavItem.Simulations.route)
                    }
                )
            }
            composable(Routes.ResultSimulation) {
                ResultSimulationScreen(
                    areaState = areaState,
                    animalState = animalState,
                    economyState = economyState,
                    climateSoilState = climateSoilSimulationState,
                    simulationTitle = homeUiState.title,
                    description = homeUiState.description,
                    onNavigateToHome = { bottomNavController.navigate(BottomNavItem.Simulations.route) }
                )
            }
            composable(
                route = "${Routes.DetailsSimulation}/{simulationId}",
                arguments = listOf(navArgument("simulationId") {
                    type = NavType.LongType; defaultValue = -1
                })
            ) {
                val simulationId = it.arguments?.getLong("simulationId")

                if (simulationId != null) {
                    DetailsSimulationScreen(
                        simulationTitle = homeUiState.title,
                        description = homeUiState.description,
                        stepsSimulation = stepsSimulation,
                        simulationId = simulationId,
                        areaEvent = areaViewModel::onEvent,
                        economyEvent = economyViewModel::onEvent,
                        animalEvent = animalViewModel::onEvent,
                        climateSoilEvent = climateSoilSimulationViewModel::onEvent,
                        areaState = areaState,
                        animalState = animalState,
                        economyState = economyState,
                        climateSoilState = climateSoilSimulationState,
                        onEvent = detailsSimulationViewModel::onEvent,
                        uiState = detailsSimulationState,
                        onNavigateToHome = {
                            bottomNavController.navigate(BottomNavItem.Simulations.route)
                        }
                    )
                }
            }
        }
    }
}