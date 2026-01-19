package com.pmdm.appV1.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import com.pmdm.appV1.data.mocks.TransaccionMock
import com.pmdm.appV1.models.Transaccion

fun Transaccion.toTransaccionMock() = TransaccionMock(
    id = id,
    descripcion = nombre,
    cantidad = cantidad,
    fecha = fecha,
    tipo = tipo
)

fun TransaccionMock.toTransaccion() = Transaccion(
    id = id,
    nombre = descripcion,
    cantidad = cantidad,
    fecha = fecha,
    tipo = tipo,
    categoria = "Varios", // Valor por defecto ya que el Mock no tiene este campo
    icon = Icons.Default.Help // Icono por defecto
)