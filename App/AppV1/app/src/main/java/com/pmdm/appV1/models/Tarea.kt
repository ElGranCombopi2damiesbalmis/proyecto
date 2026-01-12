package com.pmdm.appV1.models

import java.time.LocalDateTime
import java.util.UUID

// Asumo que EtiquetaTarea es un Enum que vendrá después, lo importaremos aquí.
// Si no existe, puedes crearlo en la carpeta 'enums'.

data class Tarea(
    var id: String = UUID.randomUUID().toString(),
    var titulo: String = "",
    var descripcion: String = "",
    var fecha: LocalDateTime = LocalDateTime.now(), // Valor por defecto seguro
    var etiqueta: EtiquetaTarea? = null,
    var completada: Boolean = false
) {
    // Kotlin genera automáticamente equals(), hashCode(), toString() y getters/setters.
}
