package com.pmdm.planify.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmdm.planify.models.EtiquetaTarea
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "tareas")
data class TareaEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "fecha")
    val fecha: LocalDateTime,
    @ColumnInfo(name = "etiqueta")
    val etiqueta: EtiquetaTarea,
    @ColumnInfo(name = "completada")
    val completada: Boolean = false
)