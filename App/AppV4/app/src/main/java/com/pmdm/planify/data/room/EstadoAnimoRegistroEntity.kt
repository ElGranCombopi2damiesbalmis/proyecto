package com.pmdm.planify.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pmdm.planify.models.IconoEstadoAnimo
import java.time.LocalDate

// El mapa Map<LocalDate, IconoEstadoAnimo> se convierte en una tabla:
// cada fila es un registro de un día concreto.
// La clave primaria es la fecha (solo puede haber un estado de ánimo por día).
@Entity(tableName = "estado_animo_registros")
data class EstadoAnimoRegistroEntity(
    @PrimaryKey
    @ColumnInfo(name = "fecha")
    val fecha: LocalDate,
    @ColumnInfo(name = "icono")
    val icono: IconoEstadoAnimo
)