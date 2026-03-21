package com.pmdm.planify.ui.features.Tareas // Ajusta tu package

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.TareaRepository
import com.pmdm.planify.data.daomocks.TareaDaoMock
import com.pmdm.planify.data.mocks.TareaMock
import com.pmdm.planify.data.toTareaMock
import com.pmdm.planify.models.EtiquetaTarea
import com.pmdm.planify.models.Tarea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
data class TareaUiState(
    val tareas: List<TareaMock>       = emptyList(),
    val isLoading: Boolean            = false,
    val filtroSeleccionado: String    = "Todos",
    val mostrarDialogo: Boolean       = false,
    val tituloNueva: String           = "",
    val descripcionNueva: String      = "",
    val etiquetaNueva: EtiquetaTarea  = EtiquetaTarea.OTROS,
    val errorTitulo: Boolean          = false
)

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val tareaRepository: TareaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState: StateFlow<TareaUiState> = _uiState.asStateFlow()

    // Callback para notificar al Home cuando se añade una tarea
    var onTareaGuardada: (() -> Unit)? = null

    init { cargarTareas() }

    fun cargarTareas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val lista = tareaRepository.getAll().map { it.toTareaMock() }
            _uiState.update { it.copy(tareas = lista, isLoading = false) }
        }
    }

    fun onTareaCheckedChange(tareaId: String, completada: Boolean) {
        viewModelScope.launch {
            tareaRepository.get(tareaId)?.let { tareaRepository.update(it.copy(completada = completada)) }
            _uiState.update { s -> s.copy(tareas = s.tareas.map { if (it.id == tareaId) it.copy(completada = completada) else it }) }
            onTareaGuardada?.invoke()
        }
    }

    fun cambiarFiltro(f: String) = _uiState.update { it.copy(filtroSeleccionado = f) }

    fun getTareasFiltradas(): List<TareaMock> {
        val s = _uiState.value
        return if (s.filtroSeleccionado == "Todos") s.tareas
        else s.tareas.filter { it.etiqueta.name.equals(s.filtroSeleccionado, ignoreCase = true) }
    }

    fun abrirDialogo()  = _uiState.update { it.copy(mostrarDialogo = true, tituloNueva = "", descripcionNueva = "", etiquetaNueva = EtiquetaTarea.OTROS, errorTitulo = false) }
    fun cerrarDialogo() = _uiState.update { it.copy(mostrarDialogo = false) }

    fun onTituloChange(v: String)          = _uiState.update { it.copy(tituloNueva = v, errorTitulo = false) }
    fun onDescripcionChange(v: String)     = _uiState.update { it.copy(descripcionNueva = v) }
    fun onEtiquetaChange(e: EtiquetaTarea) = _uiState.update { it.copy(etiquetaNueva = e) }

    fun guardarTarea() {
        val s = _uiState.value
        if (s.tituloNueva.isBlank()) { _uiState.update { it.copy(errorTitulo = true) }; return }
        viewModelScope.launch {
            val nueva = Tarea(titulo = s.tituloNueva.trim(), descripcion = s.descripcionNueva.trim(), etiqueta = s.etiquetaNueva, fecha = LocalDateTime.now())
            tareaRepository.insert(nueva)
            _uiState.update { it.copy(tareas = it.tareas + nueva.toTareaMock(), mostrarDialogo = false) }
            onTareaGuardada?.invoke() // Notificar al Home
        }
    }
}