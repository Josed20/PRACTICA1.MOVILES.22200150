package dev.josed20.practica1moviles22200150.presentation.water

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ExperimentalMaterial3Api

// Nuevos imports para scroll y manejo del teclado
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.imePadding

@OptIn(ExperimentalMaterial3Api::class)
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .imePadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tarjeta superior grande y moderna que muestra el resultado o un mensaje
            ElevatedCard(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 140.dp)
                    .animateContentSize()
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    vm.result.value?.let { res ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Recomendación", style = MaterialTheme.typography.titleLarge)
                            Spacer(Modifier.height(8.dp))
                            Text(res, style = MaterialTheme.typography.headlineSmall)
                        }
                    } ?: run {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Calcula tu consumo ideal", style = MaterialTheme.typography.titleLarge)
                            Spacer(Modifier.height(8.dp))
                            Text("Ingresa tu nombre y peso", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // ✅ usar la import coincidente
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    readOnly = true,
                    value = vm.gender.value.label,
                    onValueChange = {},
                    label = { Text("Género") },
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth()
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
            ) { Text("Calcular") }

            // Si hay resultado, lo dejamos bien visible y con animación dentro de la tarjeta superior
            // (ya mostrado arriba). Dejamos un espacio para separar del final.
            Spacer(Modifier.height(8.dp))
        }
    }
}
