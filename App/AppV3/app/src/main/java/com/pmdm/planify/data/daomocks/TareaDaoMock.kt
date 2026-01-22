package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.TareaMock
import com.pmdm.planify.models.EtiquetaTarea
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