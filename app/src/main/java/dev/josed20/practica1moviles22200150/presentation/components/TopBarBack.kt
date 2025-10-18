package dev.josed20.practica1moviles22200150.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBack(nav: NavController, title: String) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            TextButton(onClick = { nav.popBackStack() }) { Text("Menú") }
        }
    )
}
