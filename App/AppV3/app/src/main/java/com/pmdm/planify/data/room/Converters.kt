package com.pmdm.planify.data.room

import androidx.room.TypeConverter
import com.pmdm.planify.models.EtiquetaTarea
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.models.TipoEtiquetaRutina
import com.pmdm.planify.models.TipoTransaccion
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {

    // --- LocalDateTime ---
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): Long? =
        value?.toEpochSecond(ZoneOffset.UTC)

    @TypeConverter
    fun toLocalDateTime(value: Long?): LocalDateTime? =
        value?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }

    // --- LocalDate ---
    @TypeConverter
    fun fromLocalDate(value: LocalDate?): Long? =
        value?.toEpochDay()

    @TypeConverter
    fun toLocalDate(value: Long?): LocalDate? =
        value?.let { LocalDate.ofEpochDay(it) }

    // --- EtiquetaTarea ---
    @TypeConverter
    fun fromEtiquetaTarea(value: EtiquetaTarea): String = value.name

    @TypeConverter
    fun toEtiquetaTarea(value: String): EtiquetaTarea = EtiquetaTarea.valueOf(value)

    // --- TipoTransaccion ---
    @TypeConverter
    fun fromTipoTransaccion(value: TipoTransaccion): String = value.name

    @TypeConverter
    fun toTipoTransaccion(value: String): TipoTransaccion = TipoTransaccion.valueOf(value)

    // --- TipoEtiquetaRutina ---
    @TypeConverter
    fun fromTipoEtiquetaRutina(value: TipoEtiquetaRutina): String = value.name

    @TypeConverter
    fun toTipoEtiquetaRutina(value: String): TipoEtiquetaRutina = TipoEtiquetaRutina.valueOf(value)

    // --- IconoEstadoAnimo ---
    @TypeConverter
    fun fromIconoEstadoAnimo(value: IconoEstadoAnimo): String = value.name

    @TypeConverter
    fun toIconoEstadoAnimo(value: String): IconoEstadoAnimo = IconoEstadoAnimo.valueOf(value)
}