package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.RutinaMock
import com.pmdm.planify.models.Rutina

class RutinaRepositoryConverter {

    // De Mock (Entidad) a Modelo (Dominio)
    fun toModel(mock: RutinaMock): Rutina {
        return Rutina(
            id = mock.id,
            nombre = mock.nombre,
            detalles = mock.detalles,
            imagenUrl = mock.imagenUrl,
            videoUrl = mock.videoUrl,
            etiquetaTexto = mock.etiquetaTexto,
            tipoEtiqueta = mock.tipoEtiqueta
        )
    }

    // De Modelo (Dominio) a Mock (Entidad)
    fun toMock(model: Rutina): RutinaMock {
        return RutinaMock(
            id = model.id,
            nombre = model.nombre,
            detalles = model.detalles,
            imagenUrl = model.imagenUrl,
            videoUrl = model.videoUrl,
            etiquetaTexto = model.etiquetaTexto,
            tipoEtiqueta = model.tipoEtiqueta
        )
    }
}