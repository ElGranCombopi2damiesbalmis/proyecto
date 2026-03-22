package com.pmdm.planify.data.mocks

import com.pmdm.planify.models.TipoEtiquetaRutina
import java.util.UUID

// Esta clase actúa como la "Entidad" de la base de datos simulada
data class RutinaMock(
    var id: String = UUID.randomUUID().toString(),
    var nombre: String = "",
    var detalles: String = "",
    var imagenUrl: String = "",
    var videoUrl: String = "",
    var etiquetaTexto: String? = null,
    var tipoEtiqueta: TipoEtiquetaRutina = TipoEtiquetaRutina.NINGUNA
)