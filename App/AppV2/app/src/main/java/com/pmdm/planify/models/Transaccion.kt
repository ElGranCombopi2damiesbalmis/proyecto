package com.pmdm.appV2.models

import java.time.LocalDateTime
import java.util.UUID

data class Transaccion(
    val id: String = UUID.randomUUID().toString(),
    var descripcion: String = "",
    var cantidad: Double = 0.0,
    var fecha: LocalDateTime = LocalDateTime.now(),
    var tipo: TipoTransaccion = TipoTransaccion.GASTO
)
