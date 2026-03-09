package com.pmdm.planify.ui.features.Tareas // Ajusta tu package

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.daomocks.TareaDaoMock
import com.pmdm.planify.data.mocks.TareaMock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// Asegúrate de que TareaUiState esté definido (puede estar en este mismo archivo fuera de la clase)
data class TareaUiState(
    val tareas: List<TareaMock> = emptyList(),
    val isLoading: Boolean = false,
    val filtroSeleccionado: String = "Todos"
)

@HiltViewModel
class TareaViewModel @Inject constructor(
    // Si ya tienes un Repositorio real, cámbialo aquí.
    // Por ahora dejamos el DAO Mock como pediste.
    // private val repository: TareaRepository
) : ViewModel() {

    // El Mock DAO lo inicializamos dentro o lo recibimos por Hilt
    private val tareaDao = TareaDaoMock()

    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState: StateFlow<TareaUiState> = _uiState.asStateFlow()

    init {
        cargarTareas()
    }

    private fun cargarTareas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // Simulamos la carga desde el DAO
            val listaTareas = tareaDao.tareas
            _uiState.update {
                it.copy(tareas = listaTareas, isLoading = false)
            }
        }
    }

    fun onTareaCheckedChange(tareaId: String, completada: Boolean) {
        _uiState.update { currentState ->
            val nuevasTareas = currentState.tareas.map {
                if (it.id == tareaId) it.copy(completada = completada) else it
            }
            currentState.copy(tareas = nuevasTareas)
        }
    }

    fun cambiarFiltro(nuevoFiltro: String) {
        _uiState.update { it.copy(filtroSeleccionado = nuevoFiltro) }
    }

    // Esta función la llamamos desde el Screen pasando el estado recolectado
    fun getTareasFiltradas(): List<TareaMock> {
        val estado = _uiState.value
        return if (estado.filtroSeleccionado == "Todos") {
            estado.tareas
        } else {
            // Filtramos por el nombre de la etiqueta (Gimnasio, Finanzas, etc.)
            estado.tareas.filter {
                it.etiqueta.name.equals(estado.filtroSeleccionado, ignoreCase = true)
            }
        }
    }
}