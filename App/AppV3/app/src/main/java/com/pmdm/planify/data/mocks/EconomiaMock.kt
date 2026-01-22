package com.pmdm.planify.data.mocks

import com.pmdm.planify.models.Transaccion

data class EconomiaMock(
    var saldo: Double = 0.0,
    var historialTransacciones: MutableList<Transaccion> = mutableListOf()
)