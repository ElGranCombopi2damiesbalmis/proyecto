package com.pmdm.appV2.data

import com.pmdm.appV2.data.mocks.EconomiaMock
import com.pmdm.appV2.models.Economia

fun Economia.toEconomiaMock() = EconomiaMock(
    saldo = saldo,
    // Como EconomiaMock usa Transaccion (Modelo) y no TransaccionMock,
    // simplemente copiamos la lista tal cual.
    historialTransacciones = historialTransacciones.toMutableList()
)

fun EconomiaMock.toEconomia() = Economia(
    saldo = saldo,
    historialTransacciones = historialTransacciones.toMutableList()
)