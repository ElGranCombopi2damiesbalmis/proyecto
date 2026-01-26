package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.RutinaMock
import com.pmdm.planify.models.TipoEtiquetaRutina

class RutinaDaoMock {

    // Los datos de prueba viven AQUI, dentro del DAO
    private val rutinas = mutableListOf(
        RutinaMock(
            nombre = "Día de Pierna",
            detalles = "45 min • 6 Ejercicios • Enfocado en fuerza",
            imagenUrl = "https://images.unsplash.com/photo-1574680096141-1cddd32e01f9?q=80&w=600",
            videoUrl = "https://www.youtube.com/watch?v=RqaX_0sR2FI",
            etiquetaTexto = "Intenso",
            tipoEtiqueta = TipoEtiquetaRutina.INTENSO
        ),
        RutinaMock(
            nombre = "Torso y Brazos",
            detalles = "50 min • 8 Ejercicios • Hipertrofia",
            imagenUrl = "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?q=80&w=600",
            videoUrl = "https://www.youtube.com/watch?v=UyTR2EjVk1Y",
            etiquetaTexto = null,
            tipoEtiqueta = TipoEtiquetaRutina.NINGUNA
        ),
        RutinaMock(
            nombre = "Cardio HIIT",
            detalles = "25 min • 4 Ejercicios • Resistencia",
            imagenUrl = "https://images.unsplash.com/photo-1538805060512-e21960de3b5c?q=80&w=600",
            videoUrl = "https://www.youtube.com/watch?v=ml6cT4AZdqI",
            etiquetaTexto = "Rápido",
            tipoEtiqueta = TipoEtiquetaRutina.RAPIDO
        )
    )

    fun getRutinas(): List<RutinaMock> {
        return rutinas
    }

    // Ejemplo: Función para añadir datos a la lista interna
    fun addRutina(rutina: RutinaMock) {
        rutinas.add(rutina)
    }
}