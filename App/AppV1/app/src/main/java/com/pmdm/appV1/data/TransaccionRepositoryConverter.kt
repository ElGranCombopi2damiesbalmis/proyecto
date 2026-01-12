package com.pmdm.appV1.data

import com.pmdm.appV1.data.mocks.TransaccionMock
import com.pmdm.appV1.models.Transaccion

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