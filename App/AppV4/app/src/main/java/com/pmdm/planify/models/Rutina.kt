package com.pmdm.planify.models

import java.util.UUID

// Enum para controlar los colores de las etiquetas en la UI
enum class TipoEtiquetaRutina {
    NINGUNA, INTENSO, RAPIDO
}

data class Rutina(
    var id: String = UUID.randomUUID().toString(),
    var nombre: String = "",
    var detalles: String = "", // Ej: "45 min • 6 Ejercicios"
    var imagenUrl: String = "",
    var videoUrl: String = "", // Enlace al video de YouTube
    var etiquetaTexto: String? = null, // Ej: "INTENSO"
    var tipoEtiqueta: TipoEtiquetaRutina = TipoEtiquetaRutina.NINGUNA
)