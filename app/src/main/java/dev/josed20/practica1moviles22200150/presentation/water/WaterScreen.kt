@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package dev.josed20.practica1moviles22200150.presentation.water

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.josed20.practica1moviles22200150.data.model.Gender
import dev.josed20.practica1moviles22200150.presentation.components.TopBarBack
import kotlinx.coroutines.launch

// ✅ IMPORTS correctos para el teclado:
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun WaterScreen(nav: NavController, vm: WaterViewModel = viewModel()) {
    val ctx = LocalContext.current
    val snackbarHost = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHost) },
        topBar = { TopBarBack(nav, "Consumo de agua") }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = vm.name.value,
                onValueChange = { vm.name.value = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vm.weightText.value,
                onValueChange = { vm.weightText.value = it },
                label = { Text("Peso (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // ✅
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    readOnly = true,
                    value = vm.gender.value.label,
                    onValueChange = {},
                    label = { Text("Género") },
                    // Si tu versión pide la nueva firma, usa:
                    // modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth()
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    Gender.entries.forEach { g ->
                        DropdownMenuItem(
                            text = { Text(g.label) },
                            onClick = { vm.gender.value = g; expanded = false }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    val msg = vm.calculate()
                    if (msg == null) {
                        Toast.makeText(ctx, "Verifica nombre y peso (5–200).", Toast.LENGTH_SHORT).show()
                        scope.launch { snackbarHost.showSnackbar("Datos inválidos") }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Calcular") }

            vm.result.value?.let {
                Card { Text(it, modifier = Modifier.padding(16.dp)) }
            }
        }
    }
}
