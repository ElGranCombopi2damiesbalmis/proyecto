package com.pmdm.appV1.data.daomocks

import com.pmdm.appV1.data.mocks.EstadoAnimoMock
import com.pmdm.appV1.models.IconoEstadoAnimo
import java.time.LocalDate

class EstadoAnimoDaoMock {
    // Simulamos la tabla de Estado de Ánimo
    val estadoAnimo = EstadoAnimoMock().apply {
        registroAnimo[LocalDate.now()] = IconoEstadoAnimo.BIEN
    }
}