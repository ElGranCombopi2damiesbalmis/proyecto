package com.pmdm.appV2.models

import java.time.LocalDateTime
import java.util.UUID

data class Tarea(
    val id: String = UUID.randomUUID().toString(),
    var titulo: String = "",
    var descripcion: String = "",
    var fecha: LocalDateTime = LocalDateTime.now(),
    var etiqueta: EtiquetaTarea = EtiquetaTarea.OTROS,
    var completada: Boolean = false
)
