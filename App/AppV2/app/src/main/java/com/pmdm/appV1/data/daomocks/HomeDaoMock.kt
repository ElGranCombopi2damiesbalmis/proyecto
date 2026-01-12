package com.pmdm.appV1.data.daomocks

import com.pmdm.appV1.data.mocks.HomeMock

class HomeDaoMock {
    // Simulamos la tabla de configuración Home
    val home = HomeMock(
        fraseBienvenida = "Hola, Juan Pérez",
        notificacionesPendientes = 5
    )
}