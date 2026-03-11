package com.pmdm.planify.data

import com.pmdm.planify.data.room.RutinaEntity
import com.pmdm.planify.models.Rutina

// --- De Modelo a Entity ---
fun Rutina.toRutinaEntity() = RutinaEntity(
    id = id,
    nombre = nombre,
    detalles = detalles,
    imagenUrl = imagenUrl,
    videoUrl = videoUrl,
    etiquetaTexto = etiquetaTexto,
    tipoEtiqueta = tipoEtiqueta
)

// --- De Entity a Modelo ---
fun RutinaEntity.toRutina() = Rutina(
    id = id,
    nombre = nombre,
    detalles = detalles,
    imagenUrl = imagenUrl,
    videoUrl = videoUrl,
    etiquetaTexto = etiquetaTexto,
    tipoEtiqueta = tipoEtiqueta
)