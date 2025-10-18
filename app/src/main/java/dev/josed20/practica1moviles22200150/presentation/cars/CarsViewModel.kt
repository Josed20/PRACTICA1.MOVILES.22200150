package dev.josed20.practica1moviles22200150.presentation.cars

import androidx.lifecycle.ViewModel
import dev.josed20.practica1moviles22200150.data.model.SportCar
import dev.josed20.practica1moviles22200150.data.repository.CarsRepository

class CarsViewModel : ViewModel() {
    val cars: List<SportCar> = CarsRepository.getAll()
    val total: Double = cars.sumOf { it.precio }
}