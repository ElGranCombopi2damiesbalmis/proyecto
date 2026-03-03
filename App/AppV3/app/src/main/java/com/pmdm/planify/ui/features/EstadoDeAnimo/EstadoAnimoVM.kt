package com.pmdm.planify.ui.features.EstadoDeAnimo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.models.EstadoAnimo
import com.pmdm.planify.models.IconoEstadoAnimo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EstadoAnimoVM @Inject constructor(
    private val repository: EstadoAnimoRepository
) : ViewModel() {

    var estadoAnimo by mutableStateOf(EstadoAnimo())
        private set

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        estadoAnimo = repository.get()
    }

    fun onEvent(event: EstadoAnimoEvent) {
        when (event) {
            is EstadoAnimoEvent.OnSelectMood -> {
                // Registramos el ánimo en la fecha indicada (ej. hoy)
                repository.registrar(event.date, event.mood)
                cargarDatos() // Refrescamos la UI
            }
        }
    }

    // Función de ayuda para obtener el ánimo de un día concreto
    fun getMoodForDate(date: LocalDate): IconoEstadoAnimo? {
        return estadoAnimo.registroAnimo[date]
    }
}