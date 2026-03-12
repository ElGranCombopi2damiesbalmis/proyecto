package com.pmdm.planify.ui.features.EstadoDeAnimo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.models.EstadoAnimo
import com.pmdm.planify.models.IconoEstadoAnimo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class EstadoAnimoVM @Inject constructor(
    private val repository: EstadoAnimoRepository
) : ViewModel() {

    var estadoAnimo by mutableStateOf(EstadoAnimo())
        private set

    init { cargarDatos() }

    private fun cargarDatos() {
        viewModelScope.launch {
            estadoAnimo = repository.get()
        }
    }

    fun onEvent(event: EstadoAnimoEvent) {
        when (event) {
            is EstadoAnimoEvent.OnSelectMood -> {
                viewModelScope.launch {
                    repository.registrar(event.date, event.mood)
                    estadoAnimo = repository.get()
                }
            }
        }
    }

    fun getMoodForDate(date: LocalDate): IconoEstadoAnimo? = estadoAnimo.registroAnimo[date]
}