package com.pmdm.appV1.models

import java.time.LocalDateTime

data class Transaccion(
    var descripcion: String = "",
    var cantidad: Double = 0.0,
    var fecha: LocalDateTime = LocalDateTime.now(),
    var tipo: TipoTransaccion? = null // Enum TipoTransaccion
)
