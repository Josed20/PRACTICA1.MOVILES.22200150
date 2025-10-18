@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package dev.josed20.practica1moviles22200150.presentation.activity

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.josed20.practica1moviles22200150.data.model.ActivityType
import dev.josed20.practica1moviles22200150.data.model.Intensity
import dev.josed20.practica1moviles22200150.presentation.components.CardTotal
import dev.josed20.practica1moviles22200150.presentation.components.LabeledNumberField
import dev.josed20.practica1moviles22200150.presentation.components.TopBarBack
import kotlinx.coroutines.launch
// si tu versión de Material3 soporta MenuAnchorType, puedes importarlo y usarlo:
// import androidx.compose.material3.MenuAnchorType

@Composable
fun ActivityScreen(nav: NavController, vm: ActivityViewModel = viewModel()) {
    val ctx = LocalContext.current
    val snackbarHost = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()            // ✅ usar scope para snackbar
    var expAct by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHost) },
        topBar = { TopBarBack(nav, "Registro de actividad") }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Actividad
            ExposedDropdownMenuBox(expanded = expAct, onExpandedChange = { expAct = !expAct }) {
                OutlinedTextField(
                    readOnly = true,
                    value = vm.selectedType.value.label,
                    onValueChange = {},
                    label = { Text("Actividad") },
                    // Si tu versión muestra 'menuAnchor()' deprecada, usa la sobrecarga:
                    // modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth()
                    // Si no, deja el deprecado (solo warning):
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expAct, onDismissRequest = { expAct = false }) {
                    // ✅ usar entries en lugar de values()
                    ActivityType.entries.forEach { t ->
                        DropdownMenuItem(
                            text = { Text(t.label) },
                            onClick = { vm.selectedType.value = t; expAct = false }
                        )
                    }
                }
            }

            // Duración
            LabeledNumberField(
                value = vm.minutesText.value,
                onValueChange = { vm.minutesText.value = it },
                label = "Duración (min)",
                modifier = Modifier.fillMaxWidth()
            )

            // Intensidad
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Intensity.entries.forEach { i ->       // ✅ entries
                    Row {
                        RadioButton(
                            selected = vm.selectedIntensity.value == i,
                            onClick = { vm.selectedIntensity.value = i }
                        )
                        Text(i.label, modifier = Modifier.padding(top = 12.dp, start = 4.dp))
                    }
                }
            }

            Button(
                onClick = {
                    if (!vm.addEntry()) {
                        Toast.makeText(ctx, "Duración debe ser entero positivo.", Toast.LENGTH_SHORT).show()
                        scope.launch {                  // ✅ en lugar de LaunchedEffect
                            snackbarHost.showSnackbar("Corrige la duración")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Agregar") }

            // ✅ Divider deprecado → HorizontalDivider
            HorizontalDivider()

            Text("Actividades registradas:", style = MaterialTheme.typography.titleMedium)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(vm.entries) { e ->
                    Card {
                        Column(Modifier.padding(12.dp)) {
                            Text("${e.type.label} • ${e.minutes} min • ${e.intensity.label}")
                            Text("Calorías: ${"%.1f".format(e.calories)}")
                        }
                    }
                }
            }

            if (vm.entries.isNotEmpty()) {
                CardTotal("Calorías totales: ${"%.1f".format(vm.total)}")
            }
        }
    }
}
