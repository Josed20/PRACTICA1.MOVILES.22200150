package dev.josed20.practica1moviles22200150.presentation.cars

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.josed20.practica1moviles22200150.presentation.components.CardTotal
import dev.josed20.practica1moviles22200150.presentation.components.TopBarBack

@Composable
fun CarsScreen(nav: NavController, vm: CarsViewModel = viewModel()) {
    val total = remember(vm.cars) { vm.total }

    Scaffold(
        topBar = { TopBarBack(nav, "Catálogo de autos") }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(vm.cars) { car ->
                    Card {
                        Column {
                            AsyncImage(
                                model = car.imagenUrl,
                                contentDescription = "${car.marca} ${car.modelo}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth().height(180.dp)
                            )
                            Column(Modifier.padding(12.dp)) {
                                Text("${car.marca} ${car.modelo}", style = MaterialTheme.typography.titleMedium)
                                Text("Precio aprox.: \$${"%,.2f".format(car.precio)}")
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            CardTotal("Costo total: \$${"%,.2f".format(total)}")
        }
    }
}