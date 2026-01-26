package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.RutinaDaoMock
import com.pmdm.planify.models.Rutina

class RutinaRepository {

    // Instancias directas
    private val rutinaDao = RutinaDaoMock()
    private val converter = RutinaRepositoryConverter()

    fun getRutinas(): List<Rutina> {
        // 1. Obtener datos crudos (Mocks) del DAO
        val listaMocks = rutinaDao.getRutinas()

        // 2. Convertir Mocks a Modelos de dominio
        return listaMocks.map { converter.toModel(it) }
    }
}