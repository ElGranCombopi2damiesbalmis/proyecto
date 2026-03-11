package com.pmdm.planify.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmdm.planify.models.TipoTransaccion
import java.time.LocalDateTime
import java.util.UUID

// NOTA: ImageVector no se puede almacenar en Room.
// Guardamos el nombre del icono como String y lo reconstruimos en el converter.
@Entity(tableName = "transacciones")
data class TransaccionEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "fecha")
    val fecha: LocalDateTime?,
    @ColumnInfo(name = "categoria")
    val categoria: String,
    @ColumnInfo(name = "cantidad")
    val cantidad: Double,
    @ColumnInfo(name = "tipo")
    val tipo: TipoTransaccion,
    @ColumnInfo(name = "icon_nombre")
    val iconNombre: String = "" // Ej: "Restaurant", "FitnessCenter", "LocalTaxi"
)