package com.pmdm.planify.ui.features.Tareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.daomocks.TareaDaoMock
import com.pmdm.planify.data.mocks.TareaMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TareaViewModel(
    // En una app real usarías un Repositorio, aquí usamos tu Mock por coherencia
    private val tareaDao: TareaDaoMock = TareaDaoMock()
) : ViewModel() {

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
        // Actualizamos la lista localmente para que la UI reaccione instantáneamente
        val nuevasTareas = _uiState.value.tareas.map {
            if (it.id == tareaId) it.copy(completada = completada) else it
        }
        _uiState.update { it.copy(tareas = nuevasTareas) }

        // Aquí llamarías al DAO para persistir el cambio:
        // tareaDao.update(tareaId, completada)
    }

    fun cambiarFiltro(nuevoFiltro: String) {
        _uiState.update { it.copy(filtroSeleccionado = nuevoFiltro) }
    }

    // Función para obtener las tareas filtradas que se mostrarán
    fun getTareasFiltradas(): List<TareaMock> {
        val estado = _uiState.value
        return if (estado.filtroSeleccionado == "Todos") {
            estado.tareas
        } else {
            estado.tareas.filter { it.etiqueta.name.equals(estado.filtroSeleccionado, ignoreCase = true) }
        }
    }
}