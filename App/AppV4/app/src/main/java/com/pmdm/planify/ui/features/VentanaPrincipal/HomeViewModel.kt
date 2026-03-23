package com.pmdm.planify.ui.features.VentanaPrincipal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.data.HomeRepository
import com.pmdm.planify.data.RutinaRepository
import com.pmdm.planify.data.TareaRepository
import com.pmdm.planify.data.TransaccionRepository
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.data.UserSessionRepository
import com.pmdm.planify.models.Home
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.models.Tarea
import com.pmdm.planify.models.TipoTransaccion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
data class HomeUiState(
    val nombreUsuario: String                           = "",
    val fraseBienvenida: String                         = "Hola de nuevo",
    val proximasTareas: List<Tarea>                     = emptyList(),
    val tareasPendientesCount: Int                      = 0,
    val tareasCompletadasCount: Int                     = 0,
    val animoHoy: IconoEstadoAnimo?                     = null,
    val gastoTotal: Double                              = 0.0,
    val ingresoTotal: Double                            = 0.0,
    val ultimasTransaccionesLabels: List<Pair<String, Double>> = emptyList(),
    val ultimaRutinaNombre: String                      = "Sin rutinas",
    val ultimaRutinaDetalles: String                    = "",
    val totalSesionesGym: Int                           = 0
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usuarioRepo: UsuarioRepository,
    private val sessionRepo: UserSessionRepository,
    private val tareaRepo: TareaRepository,
    private val estadoAnimoRepo: EstadoAnimoRepository,
    private val transaccionRepo: TransaccionRepository,
    private val rutinaRepo: RutinaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init { cargarDatos() }

    fun cargarDatos() {
        viewModelScope.launch {
            val usuario       = usuarioRepo.getCurrent(sessionRepo) ?: usuarioRepo.getAll().firstOrNull()
            val todasTareas   = tareaRepo.getAll()
            val pendientes    = todasTareas.filter { !it.completada }
            val completadas   = todasTareas.filter { it.completada }
            val estadoAnimo   = estadoAnimoRepo.get()
            val animoHoy      = estadoAnimo.registroAnimo[LocalDate.now()]
            val transacciones = transaccionRepo.getAll()
            val gastoTotal    = transacciones.filter { it.tipo == TipoTransaccion.GASTO }.sumOf { it.cantidad }
            val ingresoTotal  = transacciones.filter { it.tipo == TipoTransaccion.INGRESO }.sumOf { it.cantidad }
            val ultimas7      = transacciones.sortedByDescending { it.fecha }.take(7).map { it.nombre.take(6) to it.cantidad }
            val rutinas       = rutinaRepo.getRutinas()
            val primeraRutina = rutinas.firstOrNull()

            _uiState.update {
                HomeUiState(
                    nombreUsuario          = usuario?.nombre ?: "",
                    fraseBienvenida        = saludo(),
                    proximasTareas         = pendientes.take(2),
                    tareasPendientesCount  = pendientes.size,
                    tareasCompletadasCount = completadas.size,
                    animoHoy               = animoHoy,
                    gastoTotal             = gastoTotal,
                    ingresoTotal           = ingresoTotal,
                    ultimasTransaccionesLabels = ultimas7,
                    ultimaRutinaNombre     = primeraRutina?.nombre ?: "Sin rutinas",
                    ultimaRutinaDetalles   = primeraRutina?.detalles ?: "",
                    totalSesionesGym       = rutinas.size
                )
            }
        }
    }

    private fun saludo(): String = when (LocalDateTime.now().hour) {
        in 6..11  -> "Buenos días"
        in 12..17 -> "Buenas tardes"
        else      -> "Buenas noches"
    }
}