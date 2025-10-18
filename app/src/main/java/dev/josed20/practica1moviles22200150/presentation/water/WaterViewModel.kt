package dev.josed20.practica1moviles22200150.presentation.water

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.josed20.practica1moviles22200150.data.model.Gender

class WaterViewModel : ViewModel() {
    var name = mutableStateOf("")
    var weightText = mutableStateOf("")
    var gender = mutableStateOf(Gender.SIN)
    var result = mutableStateOf<String?>(null)

    fun calculate(): String? {
        val w = weightText.value.toDoubleOrNull() ?: return null
        if (w < 5 || w > 200 || name.value.isBlank()) return null
        val liters = w * 0.035 * gender.value.factor
        result.value = "${name.value} debe beber aproximadamente ${"%.2f".format(liters)} litros de agua al día"
        return result.value
    }
}