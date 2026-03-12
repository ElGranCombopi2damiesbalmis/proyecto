package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.EstadoAnimoRegistroEntity
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.EstadoAnimo
import com.pmdm.planify.models.IconoEstadoAnimo
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject

class EstadoAnimoRepository @Inject constructor(@ApplicationContext context: Context) {

    private val dao = PlanifyDB.getDatabase(context).estadoAnimoDao()

    // Reconstruye el mapa completo desde la tabla
    suspend fun get(): EstadoAnimo =
        dao.getAll().toEstadoAnimo()

    // Inserta o sobreescribe el registro de un día concreto
    suspend fun registrar(fecha: LocalDate, icono: IconoEstadoAnimo) {
        dao.insert(EstadoAnimoRegistroEntity(fecha = fecha, icono = icono))
    }

    // Sincroniza todos los registros del mapa con la base de datos
    suspend fun update(estadoAnimo: EstadoAnimo) {
        estadoAnimo.toEstadoAnimoRegistros().forEach { dao.insert(it) }
    }

    suspend fun count(): Int =
        dao.count()
}