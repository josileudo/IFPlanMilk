package com.example.ifplanmilk.ui.screens.bottomNavBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifplanmilk.ui.route.BottomNavItem
import com.example.ifplanmilk.ui.screens.home.HomeScreen

@Composable
fun MainBottomNavigationBar() {
    val bottomNavController = rememberNavController()

    Scaffold (
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Simulations.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Simulations.route) { HomeScreen() }
            composable(BottomNavItem.NewSimulation.route) { SearchScreen() }
            composable(BottomNavItem.Settings.route) { ProfileScreen() }
        }
    }
}