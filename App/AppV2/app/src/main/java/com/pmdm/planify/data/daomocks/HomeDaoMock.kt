package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.HomeMock

class HomeDaoMock {
    // Simulamos la tabla de configuración Home
    val home = HomeMock(
        fraseBienvenida = "Hola, Juan Pérez",
        notificacionesPendientes = 5
    )
}