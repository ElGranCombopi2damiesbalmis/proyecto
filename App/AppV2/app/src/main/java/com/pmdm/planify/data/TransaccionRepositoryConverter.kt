package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.TransaccionMock
import com.pmdm.planify.models.Transaccion

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