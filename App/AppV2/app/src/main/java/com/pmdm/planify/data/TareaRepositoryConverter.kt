package com.pmdm.appV2.data

import com.pmdm.appV2.data.mocks.TareaMock
import com.pmdm.appV2.models.Tarea

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