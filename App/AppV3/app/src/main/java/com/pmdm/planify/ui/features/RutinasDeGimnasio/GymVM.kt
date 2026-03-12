package com.pmdm.planify.ui.features.RutinasDeGimnasio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.RutinaRepository
import com.pmdm.planify.models.Rutina
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class GymVM @Inject constructor(
    private val rutinaRepo: RutinaRepository
) : ViewModel() {

    var rutinas by mutableStateOf<List<Rutina>>(emptyList())
        private set

    var sesiones           by mutableStateOf(0)
        private set
    var tiempoTotalMinutos by mutableStateOf(0)
        private set
    var racha              by mutableStateOf(0)
        private set

    private var ultimaFechaEntrenamiento: LocalDate? = null

    init { cargarRutinas() }

    private fun cargarRutinas() {
        viewModelScope.launch {
            rutinas = rutinaRepo.getRutinas()
        }
    }

    fun onEvent(event: GymEvent) {
        when (event) {
            is GymEvent.OnPlayVideo -> {
                sesiones += 1
                tiempoTotalMinutos += extraerMinutos(event.rutina.detalles)
                val hoy = LocalDate.now()
                if (ultimaFechaEntrenamiento != hoy) {
                    racha += 1
                    ultimaFechaEntrenamiento = hoy
                }
            }
        }
    }

    private fun extraerMinutos(detalles: String): Int {
        val match = Regex("(\\d+)\\s*min").find(detalles)
        return match?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }

    fun formatearTiempo(): String {
        val horas = tiempoTotalMinutos / 60
        val mins  = tiempoTotalMinutos % 60
        return if (horas > 0) "${horas}h ${mins}m" else "${mins}m"
    }
}