package com.pmdm.planify.ui.features.EstadoDeAnimo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.data.UserSessionRepository
import com.pmdm.planify.models.EstadoAnimo
import com.pmdm.planify.models.IconoEstadoAnimo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject
@HiltViewModel
class EstadoAnimoVM @Inject constructor(
    private val repository: EstadoAnimoRepository,
    private val usuarioRepo: UsuarioRepository,
    private val sessionRepo: UserSessionRepository
) : ViewModel() {

    var estadoAnimo    by mutableStateOf(EstadoAnimo())
        private set
    var mesActual      by mutableStateOf(YearMonth.now())
        private set
    var nombreUsuario  by mutableStateOf("")
        private set

    // Callback para notificar al Home cuando cambia el ánimo
    var onAnimoCambiado: (() -> Unit)? = null

    init { cargarDatos() }

    fun cargarDatos() {
        viewModelScope.launch {
            estadoAnimo   = repository.get()
            nombreUsuario = (usuarioRepo.getCurrent(sessionRepo) ?: usuarioRepo.getAll().firstOrNull())?.nombre ?: ""
        }
    }

    fun mesSiguiente() { mesActual = mesActual.plusMonths(1) }
    fun mesAnterior()  { mesActual = mesActual.minusMonths(1) }

    // Días del mes actual que tienen registro
    fun registrosDelMes(): Map<LocalDate, IconoEstadoAnimo> =
        estadoAnimo.registroAnimo.filter { YearMonth.from(it.key) == mesActual }

    // Conteo de registros en el mes
    fun totalRegistrosMes(): Int = registrosDelMes().size

    // Estadísticas del mes: cuántos días de cada ánimo
    fun distribucionMes(): Map<IconoEstadoAnimo, Int> {
        val registros = registrosDelMes().values
        return IconoEstadoAnimo.entries.associateWith { icono -> registros.count { it == icono } }
    }

    // Promedio del mes como texto
    fun promedioMes(): Pair<String, IconoEstadoAnimo?> {
        val registros = registrosDelMes()
        if (registros.isEmpty()) return Pair("Sin registros", null)
        val pesos = mapOf(IconoEstadoAnimo.GENIAL to 5, IconoEstadoAnimo.BIEN to 4, IconoEstadoAnimo.NORMAL to 3, IconoEstadoAnimo.MAL to 2, IconoEstadoAnimo.MUYMAL to 1)
        val media = registros.values.mapNotNull { pesos[it] }.average()
        return when {
            media >= 4.5 -> Pair("Excelente", IconoEstadoAnimo.GENIAL)
            media >= 3.5 -> Pair("Mayormente Bien", IconoEstadoAnimo.BIEN)
            media >= 2.5 -> Pair("Regular", IconoEstadoAnimo.NORMAL)
            media >= 1.5 -> Pair("Complicado", IconoEstadoAnimo.MAL)
            else         -> Pair("Muy difícil", IconoEstadoAnimo.MUYMAL)
        }
    }

    fun onEvent(event: EstadoAnimoEvent) {
        when (event) {
            is EstadoAnimoEvent.OnSelectMood -> {
                viewModelScope.launch {
                    repository.registrar(event.date, event.mood)
                    estadoAnimo = repository.get()
                    onAnimoCambiado?.invoke() // Notificar al Home
                }
            }
        }
    }

    fun getMoodForDate(date: LocalDate): IconoEstadoAnimo? = estadoAnimo.registroAnimo[date]
}