package com.pmdm.planify.data

import com.pmdm.planify.data.room.EstadoAnimoRegistroEntity
import com.pmdm.planify.models.EstadoAnimo

// --- De lista de Entity a Modelo ---
// Reconstruye el mapa Map<LocalDate, IconoEstadoAnimo> desde las filas de la tabla
fun List<EstadoAnimoRegistroEntity>.toEstadoAnimo(): EstadoAnimo {
    val mapa = this.associate { it.fecha to it.icono }.toMutableMap()
    return EstadoAnimo(registroAnimo = mapa)
}

// --- De Modelo a lista de Entity ---
// Expande el mapa en una fila por cada entrada
fun EstadoAnimo.toEstadoAnimoRegistros(): List<EstadoAnimoRegistroEntity> =
    registroAnimo.map { (fecha, icono) ->
        EstadoAnimoRegistroEntity(fecha = fecha, icono = icono)
    }