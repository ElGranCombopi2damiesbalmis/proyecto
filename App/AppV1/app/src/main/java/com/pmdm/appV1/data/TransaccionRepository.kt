package com.pmdm.appV1.data

import com.pmdm.appV1.data.daomocks.TransaccionDaoMock
import com.pmdm.appV1.models.Transaccion

class TransaccionRepository {
    private val dao = TransaccionDaoMock()

    fun getAll(): MutableList<Transaccion> = dao.transacciones.map { it.toTransaccion() }.toMutableList()

    fun insert(transaccion: Transaccion) {
        dao.transacciones.add(transaccion.toTransaccionMock())
    }

    // Método extra útil para borrar
    fun delete(transaccion: Transaccion) {
        dao.transacciones.removeIf { it.id == transaccion.id }
    }
}