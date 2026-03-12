package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.Rutina
import com.pmdm.planify.models.TipoEtiquetaRutina
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RutinaRepository @Inject constructor(@ApplicationContext context: Context) {

    private val dao = PlanifyDB.getDatabase(context).rutinaDao()

    // Rutinas predefinidas (iguales para todos los usuarios)
    private val rutinasSeed = listOf(
        Rutina(nombre = "Día de Pierna",    detalles = "45 min • 6 Ejercicios • Enfocado en fuerza",       imagenUrl = "https://images.unsplash.com/photo-1574680096141-1cddd32e01f9?q=80&w=600", videoUrl = "https://www.youtube.com/watch?v=RqaX_0sR2FI", etiquetaTexto = "Intenso",  tipoEtiqueta = TipoEtiquetaRutina.INTENSO),
        Rutina(nombre = "Torso y Brazos",   detalles = "50 min • 8 Ejercicios • Hipertrofia",              imagenUrl = "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?q=80&w=600", videoUrl = "https://www.youtube.com/watch?v=UyTR2EjVk1Y", etiquetaTexto = null,       tipoEtiqueta = TipoEtiquetaRutina.NINGUNA),
        Rutina(nombre = "Cardio HIIT",      detalles = "25 min • 4 Ejercicios • Resistencia",              imagenUrl = "https://images.unsplash.com/photo-1538805060512-e21960de3b5c?q=80&w=600", videoUrl = "https://www.youtube.com/watch?v=ml6cT4AZdqI", etiquetaTexto = "Rápido",   tipoEtiqueta = TipoEtiquetaRutina.RAPIDO),
        Rutina(nombre = "Día de Pecho",     detalles = "55 min • 7 Ejercicios • Enfoque en hipertrofia",   imagenUrl = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?q=80&w=600", videoUrl = "https://www.youtube.com/watch?v=rxEMKXW2Wqs", etiquetaTexto = "Intenso",  tipoEtiqueta = TipoEtiquetaRutina.INTENSO),
        Rutina(nombre = "Espalda y Bíceps", detalles = "60 min • 8 Ejercicios • Amplitud y grosor",        imagenUrl = "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?q=80&w=600", videoUrl = "https://www.youtube.com/watch?v=VjA3E2lY9z8", etiquetaTexto = "Fuerza",   tipoEtiqueta = TipoEtiquetaRutina.NINGUNA)
    )

    suspend fun getRutinas(): List<Rutina> {
        // Si la BD está vacía, insertar las rutinas predefinidas
        if (dao.count() == 0) {
            rutinasSeed.forEach { dao.insert(it.toRutinaEntity()) }
        }
        return dao.getAll().map { it.toRutina() }
    }

    suspend fun insert(rutina: Rutina) = dao.insert(rutina.toRutinaEntity())
    suspend fun update(rutina: Rutina) = dao.update(rutina.toRutinaEntity())
    suspend fun delete(id: String)     = dao.delete(id)
    suspend fun count(): Int           = dao.count()
}