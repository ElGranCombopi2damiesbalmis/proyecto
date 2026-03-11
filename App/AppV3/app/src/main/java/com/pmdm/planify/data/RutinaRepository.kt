package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.Rutina
import javax.inject.Inject

class RutinaRepository @Inject constructor(context: Context) {

    private val dao = PlanifyDB.getDatabase(context).rutinaDao()

    suspend fun getRutinas(): List<Rutina> =
        dao.getAll().map { it.toRutina() }

    suspend fun insert(rutina: Rutina) =
        dao.insert(rutina.toRutinaEntity())

    suspend fun update(rutina: Rutina) =
        dao.update(rutina.toRutinaEntity())

    suspend fun delete(id: String) =
        dao.delete(id)

    suspend fun count(): Int =
        dao.count()
}