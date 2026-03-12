package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.Economia
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EconomiaRepository @Inject constructor(@ApplicationContext context: Context) {

    private val dao = PlanifyDB.getDatabase(context).transaccionDao()

    // Construye el objeto Economia a partir de las transacciones almacenadas en Room
    suspend fun get(): Economia {
        val transacciones = dao.getAll().map { it.toTransaccion() }.toMutableList()
        return Economia(
            saldo = dao.getSaldo(),
            historialTransacciones = transacciones
        )
    }
}