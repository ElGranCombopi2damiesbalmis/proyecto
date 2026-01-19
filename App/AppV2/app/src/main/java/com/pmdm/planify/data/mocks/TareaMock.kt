package com.pmdm.appV2.data.mocks

import com.pmdm.appV2.models.EtiquetaTarea
import java.time.LocalDateTime
import java.util.UUID

data class TareaMock(
    val id: String = UUID.randomUUID().toString(),
    var titulo: String = "",
    var descripcion: String = "",
    var fecha: LocalDateTime = LocalDateTime.now(),
    var etiqueta: EtiquetaTarea = EtiquetaTarea.OTROS,
    var completada: Boolean = false
)