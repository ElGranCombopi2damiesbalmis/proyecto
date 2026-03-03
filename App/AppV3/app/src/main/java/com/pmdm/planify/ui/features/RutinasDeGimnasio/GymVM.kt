package com.pmdm.planify.ui.features.RutinasDeGimnasio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.planify.data.RutinaRepository
import com.pmdm.planify.models.Rutina
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class GymVM @Inject constructor(
    private val rutinaRepo: RutinaRepository
) : ViewModel() {

    // Lista de rutinas
    var rutinas by mutableStateOf<List<Rutina>>(emptyList())
        private set

    // Estadísticas
    var sesiones by mutableStateOf(0)
        private set
    var tiempoTotalMinutos by mutableStateOf(0)
        private set
    var racha by mutableStateOf(0)
        private set

    // Control de racha diaria
    private var ultimaFechaEntrenamiento: LocalDate? = null

    init {
        cargarRutinas()
    }

    private fun cargarRutinas() {
        rutinas = rutinaRepo.getRutinas()
    }

    fun onEvent(event: GymEvent) {
        when (event) {
            is GymEvent.OnPlayVideo -> {
                // 1. Sumamos una sesión
                sesiones += 1

                // 2. Extraemos los minutos del string (Ej: "45 min • 6 Ejercicios" -> 45)
                val minutos = extraerMinutos(event.rutina.detalles)
                tiempoTotalMinutos += minutos

                // 3. Comprobamos la racha (Solo suma 1 si hoy no hemos entrenado)
                val hoy = LocalDate.now()
                if (ultimaFechaEntrenamiento != hoy) {
                    racha += 1
                    ultimaFechaEntrenamiento = hoy
                }
            }
        }
    }

    // Busca números seguidos de la palabra "min" en el texto
    private fun extraerMinutos(detalles: String): Int {
        val regex = Regex("(\\d+)\\s*min")
        val match = regex.find(detalles)
        return match?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }

    // Convierte 130 minutos en "2h 10m"
    fun formatearTiempo(): String {
        val horas = tiempoTotalMinutos / 60
        val mins = tiempoTotalMinutos % 60
        return if (horas > 0) "${horas}h ${mins}m" else "${mins}m"
    }
}