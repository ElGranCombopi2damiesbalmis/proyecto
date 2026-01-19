package com.pmdm.appV2.data.daomocks

import com.pmdm.appV2.data.mocks.TareaMock
import com.pmdm.appV2.models.EtiquetaTarea
import java.time.LocalDateTime

class TareaDaoMock {
    // Simulamos la tabla de Tareas (una lista mutable)
    val tareas = mutableListOf(
        TareaMock(
            titulo = "Comprar comida",
            descripcion = "Ir al supermercado",
            fecha = LocalDateTime.now().plusDays(1),
            etiqueta = EtiquetaTarea.HOGAR
        ),
        TareaMock(
            titulo = "Estudiar Java",
            descripcion = "Repasar POO",
            fecha = LocalDateTime.now(),
            etiqueta = EtiquetaTarea.ESTUDIO
        )
    )
}