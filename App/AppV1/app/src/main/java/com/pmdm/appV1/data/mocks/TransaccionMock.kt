package com.pmdm.appV1.data.mocks

import com.pmdm.appV1.models.TipoTransaccion
import java.time.LocalDateTime
import java.util.UUID

data class TransaccionMock(
    val id: String = UUID.randomUUID().toString(),
    var descripcion: String = "",
    var cantidad: Double = 0.0,
    var fecha: LocalDateTime = LocalDateTime.now(),
    var tipo: TipoTransaccion = TipoTransaccion.GASTO
)