package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.TransaccionDaoMock
import com.pmdm.planify.models.Transaccion
import javax.inject.Inject

class TransaccionRepository @Inject constructor(){
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