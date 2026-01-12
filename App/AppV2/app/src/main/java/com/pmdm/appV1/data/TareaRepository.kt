package com.pmdm.appV1.data

import com.pmdm.appV1.data.daomocks.TareaDaoMock
import com.pmdm.appV1.models.Tarea

class TareaRepository {
    private val dao = TareaDaoMock()

    fun getAll(): MutableList<Tarea> = dao.tareas.map { it.toTarea() }.toMutableList()

    fun get(id: String): Tarea? = dao.tareas.find { it.id == id }?.toTarea()

    fun insert(tarea: Tarea) {
        dao.tareas.add(tarea.toTareaMock())
    }

    fun update(tarea: Tarea) {
        val index = dao.tareas.indexOfFirst { it.id == tarea.id }
        if (index != -1) {
            dao.tareas[index] = tarea.toTareaMock()
        }
    }

    fun delete(id: String) {
        dao.tareas.removeIf { it.id == id }
    }
}