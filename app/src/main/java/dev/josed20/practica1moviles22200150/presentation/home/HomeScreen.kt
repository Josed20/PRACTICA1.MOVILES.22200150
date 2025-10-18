package dev.josed20.practica1moviles22200150.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.josed20.practica1moviles22200150.navigate.Routes

@Composable
fun HomeScreen(nav: NavController) {
    Scaffold { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            val isWide = maxWidth > 600.dp
            Column(modifier = Modifier.fillMaxSize()) {
                // Header decorativo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Bienvenido",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Grid de opciones (1 o 2 columnas según ancho)
                val spacing = 16.dp
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    if (isWide) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(spacing)) {
                            OptionCard(
                                modifier = Modifier.weight(1f).height(140.dp),
                                title = "Calculadora de agua",
                                subtitle = "Controla tu ingesta diaria",
                                iconTint = MaterialTheme.colorScheme.primary,
                                icon = Icons.Filled.LocalDrink
                            ) { nav.navigate(Routes.WATER) }

                            OptionCard(
                                modifier = Modifier.weight(1f).height(140.dp),
                                title = "Registro de actividad",
                                subtitle = "Monitorea tu movimiento",
                                iconTint = MaterialTheme.colorScheme.secondary,
                                icon = Icons.Filled.FitnessCenter
                            ) { nav.navigate(Routes.ACTIVITY) }
                        }

                        Spacer(modifier = Modifier.height(spacing))

                        OptionCard(
                            modifier = Modifier.fillMaxWidth().height(140.dp),
                            title = "Catálogo de autos",
                            subtitle = "Explora modelos y detalles",
                            iconTint = MaterialTheme.colorScheme.tertiary,
                            icon = Icons.Filled.DirectionsCar
                        ) { nav.navigate(Routes.CARS) }
                    } else {
                        OptionCard(
                            modifier = Modifier.fillMaxWidth().height(120.dp),
                            title = "Calculadora de agua",
                            subtitle = "Controla tu ingesta diaria",
                            iconTint = MaterialTheme.colorScheme.primary,
                            icon = Icons.Filled.LocalDrink
                        ) { nav.navigate(Routes.WATER) }

                        Spacer(modifier = Modifier.height(spacing))

                        OptionCard(
                            modifier = Modifier.fillMaxWidth().height(120.dp),
                            title = "Registro de actividad",
                            subtitle = "Monitorea tu movimiento",
                            iconTint = MaterialTheme.colorScheme.secondary,
                            icon = Icons.Filled.FitnessCenter
                        ) { nav.navigate(Routes.ACTIVITY) }

                        Spacer(modifier = Modifier.height(spacing))

                        OptionCard(
                            modifier = Modifier.fillMaxWidth().height(120.dp),
                            title = "Catálogo de autos",
                            subtitle = "Explora modelos y detalles",
                            iconTint = MaterialTheme.colorScheme.tertiary,
                            icon = Icons.Filled.DirectionsCar
                        ) { nav.navigate(Routes.CARS) }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OptionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    iconTint: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    val cardModifier = modifier
        .clip(RoundedCornerShape(12.dp))

    Card(
        modifier = cardModifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(10.dp),
                color = iconTint.copy(alpha = 0.12f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(28.dp))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}