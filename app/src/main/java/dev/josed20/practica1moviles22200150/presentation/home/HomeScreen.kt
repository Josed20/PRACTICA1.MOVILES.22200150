package dev.josed20.practica1moviles22200150.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.josed20.practica1moviles22200150.navigate.Routes

@Composable
fun HomeScreen(nav: NavController) {
    Scaffold { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Menú principal", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            Button(onClick = { nav.navigate(Routes.WATER) }, modifier = Modifier.fillMaxWidth()) {
                Text("Calculadora de agua")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { nav.navigate(Routes.ACTIVITY) }, modifier = Modifier.fillMaxWidth()) {
                Text("Registro de actividad")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { nav.navigate(Routes.CARS) }, modifier = Modifier.fillMaxWidth()) {
                Text("Catálogo de autos")
            }
        }
    }
}