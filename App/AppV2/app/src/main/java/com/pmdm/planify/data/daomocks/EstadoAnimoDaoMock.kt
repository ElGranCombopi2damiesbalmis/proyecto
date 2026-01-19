package com.pmdm.appV2.data.daomocks

import com.pmdm.appV2.data.mocks.EstadoAnimoMock
import com.pmdm.appV2.models.IconoEstadoAnimo
import java.time.LocalDate

class EstadoAnimoDaoMock {
    // Simulamos la tabla de Estado de Ánimo
    val estadoAnimo = EstadoAnimoMock().apply {
        registroAnimo[LocalDate.now()] = IconoEstadoAnimo.BIEN
    }
}