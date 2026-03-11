package com.pmdm.planify.ui.features.VentanaPrincipal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.data.HomeRepository
import com.pmdm.planify.data.TareaRepository
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.models.Home
import com.pmdm.planify.models.Tarea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject
data class HomeUiState(
    val nombreUsuario: String = "",
    val fraseBienvenida: String = "Hola de nuevo",
    val proximasTareas: List<Tarea> = emptyList(),
    val tareasPendientesCount: Int = 0,
    val tareasCompletadasCount: Int = 0,
    val animoHoy: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usuarioRepo: UsuarioRepository,
    private val tareaRepo: TareaRepository,
    private val estadoAnimoRepo: EstadoAnimoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        val usuario = usuarioRepo.get()
        val todasLasTareas = tareaRepo.getAll()
        val pendientes = todasLasTareas.filter { !it.completada }
        val completadas = todasLasTareas.filter { it.completada }
        val estadoAnimo = estadoAnimoRepo.get()
        val animoHoy = estadoAnimo.registroAnimo[java.time.LocalDate.now()]?.name ?: ""

        _uiState.update {
            HomeUiState(
                nombreUsuario = usuario.nombre,
                fraseBienvenida = saludo(),
                proximasTareas = pendientes.take(2),
                tareasPendientesCount = pendientes.size,
                tareasCompletadasCount = completadas.size,
                animoHoy = animoHoy
            )
        }
    }

    private fun saludo(): String {
        return when (LocalDateTime.now().hour) {
            in 6..11  -> "Buenos días"
            in 12..17 -> "Buenas tardes"
            else      -> "Buenas noches"
        }
    }
}