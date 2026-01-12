package com.pmdm.appV1.data.mocks

import com.pmdm.appV1.models.Transaccion

data class EconomiaMock(
    var saldo: Double = 0.0,
    var historialTransacciones: MutableList<Transaccion> = mutableListOf()
)