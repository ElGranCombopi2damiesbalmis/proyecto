package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.EconomiaMock

class EconomiaDaoMock {
    // Simulamos la tabla de Economía
    // Nota: Las transacciones se cargan en su propio DAO, aquí guardamos el estado global
    val economia = EconomiaMock(
        saldo = 1835.0 // (2000 - 150 - 15)
    )
}