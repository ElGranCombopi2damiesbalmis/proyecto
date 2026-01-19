package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.TareaMock
import com.pmdm.planify.models.Tarea

// --- De Modelo a Mock ---
fun Tarea.toTareaMock() = TareaMock(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    fecha = fecha,
    etiqueta = etiqueta, // Asumimos mismo Enum compartido
    completada = completada
)

// --- De Mock a Modelo ---
fun TareaMock.toTarea() = Tarea(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    fecha = fecha,
    etiqueta = etiqueta,
    completada = completada
)