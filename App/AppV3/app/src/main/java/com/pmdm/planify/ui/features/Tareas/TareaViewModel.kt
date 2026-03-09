package com.pmdm.planify.ui.features.Tareas // Ajusta tu package

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.TareaRepository
import com.pmdm.planify.data.daomocks.TareaDaoMock
import com.pmdm.planify.data.mocks.TareaMock
import com.pmdm.planify.data.toTareaMock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TareaUiState(
    val tareas: List<TareaMock> = emptyList(),
    val isLoading: Boolean = false,
    val filtroSeleccionado: String = "Todos"
)

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val tareaRepository: TareaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState: StateFlow<TareaUiState> = _uiState.asStateFlow()

    init {
        cargarTareas()
    }

    private fun cargarTareas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val listaTareas = tareaRepository.getAll().map { it.toTareaMock() }
            _uiState.update { it.copy(tareas = listaTareas, isLoading = false) }
        }
    }

    fun onTareaCheckedChange(tareaId: String, completada: Boolean) {
        viewModelScope.launch {
            val tarea = tareaRepository.get(tareaId)
            tarea?.let {
                tareaRepository.update(it.copy(completada = completada))
            }
            _uiState.update { currentState ->
                val nuevasTareas = currentState.tareas.map {
                    if (it.id == tareaId) it.copy(completada = completada) else it
                }
                currentState.copy(tareas = nuevasTareas)
            }
        }
    }

    fun cambiarFiltro(nuevoFiltro: String) {
        _uiState.update { it.copy(filtroSeleccionado = nuevoFiltro) }
    }

    fun getTareasFiltradas(): List<TareaMock> {
        val estado = _uiState.value
        return if (estado.filtroSeleccionado == "Todos") {
            estado.tareas
        } else {
            estado.tareas.filter {
                it.etiqueta.name.equals(estado.filtroSeleccionado, ignoreCase = true)
            }
        }
    }
}