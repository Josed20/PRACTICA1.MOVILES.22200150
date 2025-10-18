package dev.josed20.practica1moviles22200150.presentation.activity

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.josed20.practica1moviles22200150.data.model.ActivityType
import dev.josed20.practica1moviles22200150.data.model.Intensity
import dev.josed20.practica1moviles22200150.data.repository.ActivityEntry
import dev.josed20.practica1moviles22200150.data.repository.ActivityRepository

class ActivityViewModel : ViewModel() {
    val selectedType = mutableStateOf(ActivityType.CORRER)
    val minutesText = mutableStateOf("")
    val selectedIntensity = mutableStateOf(Intensity.MEDIA)
    val entries = mutableStateListOf<ActivityEntry>().apply { addAll(ActivityRepository.entries) }

    fun addEntry(): Boolean {
        val m = minutesText.value.toIntOrNull() ?: return false
        if (m <= 0) return false
        val cals = selectedType.value.calPerMin * m * selectedIntensity.value.factor
        val e = ActivityEntry(selectedType.value, m, selectedIntensity.value, cals)
        entries.add(e)
        ActivityRepository.add(e)
        minutesText.value = ""
        return true
    }

    val total: Double get() = entries.sumOf { it.calories }
}