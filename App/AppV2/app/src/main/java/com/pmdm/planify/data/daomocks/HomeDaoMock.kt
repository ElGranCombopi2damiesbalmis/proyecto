package com.pmdm.appV2.data.daomocks

import com.pmdm.appV2.data.mocks.HomeMock

class HomeDaoMock {
    // Simulamos la tabla de configuración Home
    val home = HomeMock(
        fraseBienvenida = "Hola, Juan Pérez",
        notificacionesPendientes = 5
    )
}