package com.pmdm.appV2.data

import com.pmdm.appV2.data.daomocks.EconomiaDaoMock
import com.pmdm.appV2.models.Economia

class EconomiaRepository {
    private val dao = EconomiaDaoMock()

    fun get(): Economia = dao.economia.toEconomia()

    fun update(economia: Economia) {
        val mock = economia.toEconomiaMock()
        dao.economia.saldo = mock.saldo
        dao.economia.historialTransacciones = mock.historialTransacciones
    }
}