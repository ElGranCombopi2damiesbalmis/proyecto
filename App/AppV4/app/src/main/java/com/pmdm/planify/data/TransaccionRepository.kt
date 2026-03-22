package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.Transaccion
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TransaccionRepository @Inject constructor(@ApplicationContext context: Context) {

    private val dao = PlanifyDB.getDatabase(context).transaccionDao()

    suspend fun getAll(): MutableList<Transaccion> =
        dao.getAll().map { it.toTransaccion() }.toMutableList()

    suspend fun insert(transaccion: Transaccion) =
        dao.insert(transaccion.toTransaccionEntity())

    suspend fun delete(transaccion: Transaccion) =
        dao.delete(transaccion.id)

    // Calcula el saldo directamente en SQL (más eficiente que traer todos los datos)
    suspend fun getSaldo(): Double =
        dao.getSaldo()

    suspend fun count(): Int =
        dao.count()
}