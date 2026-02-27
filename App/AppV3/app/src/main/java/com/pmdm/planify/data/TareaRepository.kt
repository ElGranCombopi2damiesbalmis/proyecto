package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.TareaDaoMock
import com.pmdm.planify.models.Tarea
import javax.inject.Inject

class TareaRepository @Inject constructor(){
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