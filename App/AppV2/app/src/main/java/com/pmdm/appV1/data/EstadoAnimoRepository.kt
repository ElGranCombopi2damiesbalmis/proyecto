package com.pmdm.appV1.data

import com.pmdm.appV1.data.daomocks.EstadoAnimoDaoMock
import com.pmdm.appV1.models.EstadoAnimo
import com.pmdm.appV1.models.IconoEstadoAnimo
import java.time.LocalDate

class EstadoAnimoRepository {
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