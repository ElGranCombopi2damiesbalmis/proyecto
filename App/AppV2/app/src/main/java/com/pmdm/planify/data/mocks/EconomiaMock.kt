package com.pmdm.appV2.data.mocks

import com.pmdm.appV2.models.Transaccion

data class EconomiaMock(
    var saldo: Double = 0.0,
    var historialTransacciones: MutableList<Transaccion> = mutableListOf()
)