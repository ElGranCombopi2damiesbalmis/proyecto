package com.pmdm.appV2.data

import com.pmdm.appV2.data.mocks.TransaccionMock
import com.pmdm.appV2.models.Transaccion

fun Transaccion.toTransaccionMock() = TransaccionMock(
    id = id,
    descripcion = descripcion,
    cantidad = cantidad,
    fecha = fecha,
    tipo = tipo
)

fun TransaccionMock.toTransaccion() = Transaccion(
    id = id,
    descripcion = descripcion,
    cantidad = cantidad,
    fecha = fecha,
    tipo = tipo
)