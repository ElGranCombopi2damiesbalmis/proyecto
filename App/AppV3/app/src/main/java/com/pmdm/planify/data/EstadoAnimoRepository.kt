package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.EstadoAnimoDaoMock
import com.pmdm.planify.models.EstadoAnimo
import com.pmdm.planify.models.IconoEstadoAnimo
import java.time.LocalDate
import javax.inject.Inject

class EstadoAnimoRepository @Inject constructor(){
    private val dao = EstadoAnimoDaoMock()

    fun get(): EstadoAnimo = dao.estadoAnimo.toEstadoAnimo()

    // Método de conveniencia para insertar un registro específico
    fun registrar(fecha: LocalDate, icono: IconoEstadoAnimo) {
        dao.estadoAnimo.registroAnimo[fecha] = icono
    }

    fun update(estadoAnimo: EstadoAnimo) {
        val mock = estadoAnimo.toEstadoAnimoMock()
        // Limpiamos y rellenamos el mapa para asegurar consistencia
        dao.estadoAnimo.registroAnimo.clear()
        dao.estadoAnimo.registroAnimo.putAll(mock.registroAnimo)
    }
}