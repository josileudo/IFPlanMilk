package com.example.ifplanmilk.ui.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data object Routes {
    const val Splash = "splash"
    const val Welcome = "welcome"
    const val Home = "home"
    const val Navigation = "navigation"
    const val NewSimulation = "new_simulation"
    const val Settings = "settings"
    const val ResultSimulation = "result_simulation"
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Simulations : BottomNavItem(
        route = Routes.Home,
        title = "Simulações",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object NewSimulation : BottomNavItem(
        route = Routes.NewSimulation,
        title = "Nova Simulação",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    object Settings : BottomNavItem(
        route = Routes.Settings,
        title = "Configuração",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
}