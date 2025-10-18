package dev.josed20.practica1moviles22200150.data.model

enum class ActivityType(val label: String, val calPerMin: Int) {
    CORRER("Correr", 10),
    CAMINAR("Caminar", 5),
    NADAR("Nadar", 8),
    CICLISMO("Ciclismo", 7),
    YOGA("Yoga", 4)
}