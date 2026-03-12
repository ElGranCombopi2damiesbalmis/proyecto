package com.pmdm.planify.ui.features

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.EconomiaRepository
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.data.HomeRepository
import com.pmdm.planify.data.LoginRepository
import com.pmdm.planify.data.TareaRepository
import com.pmdm.planify.data.TransaccionRepository
import com.pmdm.planify.data.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.pmdm.planify.models.Economia
import com.pmdm.planify.models.Tarea
import com.pmdm.planify.models.EstadoAnimo
import com.pmdm.planify.models.Home
import com.pmdm.planify.models.Usuario

// Clase necesaria para la UI del menú
data class ItemMenuDesplegable(val icono: ImageVector, val texto: String, val accion: () -> Unit)

@HiltViewModel
class PlanifyViewModel @Inject constructor(
    private val economiaRepo: EconomiaRepository,
    private val tareaRepo: TareaRepository,
    private val estadoAnimoRepo: EstadoAnimoRepository,
    private val homeRepo: HomeRepository,
    private val loginRepo: LoginRepository,
    private val transaccionRepo: TransaccionRepository,
    private val usuarioRepo: UsuarioRepository
) : ViewModel() {

    var economiaState    by mutableStateOf<Economia?>(null)
    var listaTareas      by mutableStateOf(listOf<Tarea>())
    var estadoAnimoState by mutableStateOf<EstadoAnimo?>(null)
    var homeState        by mutableStateOf<Home?>(null)
    var usuarioState     by mutableStateOf<Usuario?>(null)

    var expandidoState    by mutableStateOf(false)
    var tareaSeleccionada by mutableStateOf<Tarea?>(null)

    var onNavigateToEconomia:    () -> Unit = {}
    var onNavigateToTransaccion: () -> Unit = {}
    var onNavigateToEstadoAnimo: () -> Unit = {}
    var onNavigateToSettings:    () -> Unit = {}
    var onNavigateToTarea:       () -> Unit = {}
    var onBack:                  () -> Unit = {}

    init { actualizarTodo() }

    private fun actualizarTodo() {
        viewModelScope.launch {
            // getAll().firstOrNull() en vez de get(correo) que requiere parámetro
            usuarioState     = usuarioRepo.getAll().firstOrNull()
            economiaState    = economiaRepo.get()
            listaTareas      = tareaRepo.getAll()
            estadoAnimoState = estadoAnimoRepo.get()
            homeState        = homeRepo.get()
        }
    }

    val descripcionEIconos = listOf(
        ItemMenuDesplegable(Icons.Default.Home,        "Inicio",  { onBack();               expandidoState = false }),
        ItemMenuDesplegable(Icons.Default.AttachMoney, "Cartera", { onNavigateToEconomia();  expandidoState = false }),
        ItemMenuDesplegable(Icons.Default.Checklist,   "Tareas",  { onNavigateToTarea();     expandidoState = false }),
        ItemMenuDesplegable(Icons.Default.Face,        "Ánimo",   { onNavigateToEstadoAnimo(); expandidoState = false })
    )

    fun onPlanifyEvent(event: PlanifyEvent) {
        when (event) {

            is PlanifyEvent.OnLoginClick -> {
                // autenticar() es suspend → necesita coroutine
                viewModelScope.launch {
                    val user = loginRepo.autenticar(event.email, event.pass)
                    if (user != null) {
                        actualizarTodo()
                        onNavigateToEconomia()
                    }
                }
            }

            is PlanifyEvent.OnGuardarTransaccion -> {
                viewModelScope.launch {
                    actualizarTodo()
                    onBack()
                }
            }

            is PlanifyEvent.OnCambiarCheckTarea -> {
                viewModelScope.launch {
                    val tarea = tareaRepo.get(event.id.toString())
                    tarea?.let {
                        tareaRepo.update(it.copy(completada = event.completada))
                        listaTareas = tareaRepo.getAll()
                    }
                }
            }

            is PlanifyEvent.OnCambiarAnimo -> {
                viewModelScope.launch {
                    estadoAnimoRepo.registrar(java.time.LocalDate.now(), event.nuevoIcono)
                    estadoAnimoState = estadoAnimoRepo.get()
                }
            }

            PlanifyEvent.OnAbrirMenu  -> expandidoState = true
            PlanifyEvent.OnCerrarMenu -> expandidoState = false
            PlanifyEvent.OnBack       -> onBack()

            else -> { /* Otros eventos */ }
        }
    }
}