package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.EstadoAnimoMock
import com.pmdm.planify.models.IconoEstadoAnimo
import java.time.LocalDate

class EstadoAnimoDaoMock {
    // Simulamos la tabla de Estado de Ánimo
    val estadoAnimo = EstadoAnimoMock().apply {
        registroAnimo[LocalDate.now()] = IconoEstadoAnimo.BIEN
    }
}