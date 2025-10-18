package dev.josed20.practica1moviles22200150.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardTotal(text: String) {
    Card { Text(text, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp)) }
}