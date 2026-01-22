package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.TransaccionMock
import com.pmdm.planify.models.TipoTransaccion
import java.time.LocalDateTime
import java.time.Month

class TransaccionDaoMock {
    // Lista mutable para que los métodos del repositorio (insert/delete) funcionen
    val transacciones = mutableListOf(
        TransaccionMock(
            descripcion = "McDonalds",
            fecha = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0),
            cantidad = 15.0,
            tipo = TipoTransaccion.GASTO
        ),
        TransaccionMock(
            descripcion = "Gold's Gym",
            fecha = LocalDateTime.now().minusDays(1),
            cantidad = 45.0,
            tipo = TipoTransaccion.GASTO
        ),
        TransaccionMock(
            descripcion = "Uber Trip",
            fecha = LocalDateTime.now().withMonth(Month.OCTOBER.value).withDayOfMonth(5),
            cantidad = 12.5,
            tipo = TipoTransaccion.GASTO
        ),
        TransaccionMock(
            descripcion = "Reembolso",
            fecha = LocalDateTime.now().withMonth(Month.OCTOBER.value).withDayOfMonth(1),
            cantidad = 32.0,
            tipo = TipoTransaccion.INGRESO
        )
    )
}