package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.EconomiaDaoMock
import com.pmdm.planify.models.Economia
import javax.inject.Inject

class EconomiaRepository @Inject constructor(){
    private val dao = EconomiaDaoMock()

    fun get(): Economia = dao.economia.toEconomia()

    fun update(economia: Economia) {
        val mock = economia.toEconomiaMock()
        dao.economia.saldo = mock.saldo
        dao.economia.historialTransacciones = mock.historialTransacciones
    }
}