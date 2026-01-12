package com.pmdm.appV1.data.daomocks

import com.pmdm.appV1.data.mocks.TransaccionMock
import com.pmdm.appV1.models.TipoTransaccion
import java.time.LocalDateTime

class TransaccionDaoMock {
    // Simulamos la tabla de Transacciones
    val transacciones = mutableListOf(
        TransaccionMock(
            descripcion = "Salario",
            cantidad = 2000.0,
            fecha = LocalDateTime.now().minusDays(5),
            tipo = TipoTransaccion.INGRESO
        ),
        TransaccionMock(
            descripcion = "Supermercado",
            cantidad = 150.0,
            fecha = LocalDateTime.now().minusDays(2),
            tipo = TipoTransaccion.GASTO
        ),
        TransaccionMock(
            descripcion = "Netflix",
            cantidad = 15.0,
            fecha = LocalDateTime.now().minusDays(1),
            tipo = TipoTransaccion.GASTO
        )
    )
}