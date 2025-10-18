package dev.josed20.practica1moviles22200150.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import dev.josed20.practica1moviles22200150.presentation.home.HomeScreen
import dev.josed20.practica1moviles22200150.presentation.water.WaterScreen
import dev.josed20.practica1moviles22200150.presentation.activity.ActivityScreen
import dev.josed20.practica1moviles22200150.presentation.cars.CarsScreen

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(nav) }
        composable(Routes.WATER) { WaterScreen(nav) }
        composable(Routes.ACTIVITY) { ActivityScreen(nav) }
        composable(Routes.CARS) { CarsScreen(nav) }
    }
}

