package com.pmdm.appV1.models

data class Economia(
    var saldo: Double = 0.0,
    var historialTransacciones: MutableList<Transaccion> = mutableListOf()
)
