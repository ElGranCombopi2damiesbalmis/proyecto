package com.pmdm.planify.models

data class Economia(
    var saldo: Double = 0.0,
    var historialTransacciones: MutableList<Transaccion> = mutableListOf()
)
