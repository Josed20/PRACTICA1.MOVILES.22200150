package dev.josed20.practica1moviles22200150.data.model

enum class Gender(val label: String, val factor: Double) {
    MASCULINO("Masculino", 1.02),
    FEMENINO("Femenino", 1.01),
    SIN("Sin especificar", 1.00)
}