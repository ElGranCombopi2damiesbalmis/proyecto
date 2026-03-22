package com.pmdm.planify.data

import com.pmdm.planify.data.room.TareaEntity
import com.pmdm.planify.models.Tarea

// --- De Modelo a Entity ---
fun Tarea.toTareaEntity() = TareaEntity(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    fecha = fecha,
    etiqueta = etiqueta,
    completada = completada
)

// --- De Entity a Modelo ---
fun TareaEntity.toTarea() = Tarea(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    fecha = fecha,
    etiqueta = etiqueta,
    completada = completada
)