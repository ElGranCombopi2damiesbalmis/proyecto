package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.Tarea
import javax.inject.Inject

class TareaRepository @Inject constructor(context: Context) {

    private val dao = PlanifyDB.getDatabase(context).tareaDao()

    suspend fun getAll(): MutableList<Tarea> =
        dao.getAll().map { it.toTarea() }.toMutableList()

    suspend fun get(id: String): Tarea? =
        dao.getById(id)?.toTarea()

    suspend fun insert(tarea: Tarea) =
        dao.insert(tarea.toTareaEntity())

    suspend fun update(tarea: Tarea) =
        dao.update(tarea.toTareaEntity())

    suspend fun delete(id: String) =
        dao.delete(id)

    suspend fun count(): Int =
        dao.count()

    suspend fun getCompletadas(): List<Tarea> =
        dao.getCompletadas().map { it.toTarea() }
}