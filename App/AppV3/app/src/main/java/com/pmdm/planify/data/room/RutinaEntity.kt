package com.pmdm.planify.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmdm.planify.models.TipoEtiquetaRutina
import java.util.UUID

@Entity(tableName = "rutinas")
data class RutinaEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "detalles")
    val detalles: String,
    @ColumnInfo(name = "imagen_url")
    val imagenUrl: String,
    @ColumnInfo(name = "video_url")
    val videoUrl: String,
    @ColumnInfo(name = "etiqueta_texto")
    val etiquetaTexto: String?,
    @ColumnInfo(name = "tipo_etiqueta")
    val tipoEtiqueta: TipoEtiquetaRutina = TipoEtiquetaRutina.NINGUNA
)