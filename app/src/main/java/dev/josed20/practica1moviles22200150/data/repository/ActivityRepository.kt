package dev.josed20.practica1moviles22200150.data.repository

import dev.josed20.practica1moviles22200150.data.model.ActivityType
import dev.josed20.practica1moviles22200150.data.model.Intensity

data class ActivityEntry(
    val type: ActivityType,
    val minutes: Int,
    val intensity: Intensity,
    val calories: Double
)

object ActivityRepository {
    private val _entries = mutableListOf<ActivityEntry>()
    val entries: List<ActivityEntry> get() = _entries

    fun add(entry: ActivityEntry) { _entries.add(entry) }
    fun clear() { _entries.clear() }
}