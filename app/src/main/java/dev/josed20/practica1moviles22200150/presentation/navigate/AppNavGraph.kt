package dev.josed20.practica1moviles22200150.presentation.navigate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.josed20.practica1moviles22200150.presentation.home.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        /*   composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }

        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen()
            }
        }
        //de permisos
        composable("permisos") {
            DrawerScaffold(navController) {
                GalleryPermissionScreen()
            }
        }
        //de favoritos
        composable("favoritos") {
            DrawerScaffold(navController) {
                Text("pantalla de favoritos proximamente")
            }
        }
        */

    }
}

