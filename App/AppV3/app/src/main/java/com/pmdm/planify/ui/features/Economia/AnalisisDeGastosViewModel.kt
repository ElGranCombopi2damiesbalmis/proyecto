package com.pmdm.planify.ui.features.Economia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.pmdm.planify.data.TransaccionRepository
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AnalisisDeGastosViewModel @Inject constructor(
    private val transaccionRepository: TransaccionRepository
) : ViewModel() {

    var categoriaSeleccionada by mutableStateOf("Todo")
        private set

    private var todasLasTransacciones by mutableStateOf(transaccionRepository.getAll())

    val transaccionesFiltradas: List<Transaccion>
        get() = if (categoriaSeleccionada == "Todo") {
            todasLasTransacciones
        } else {
            todasLasTransacciones.filter { it.categoria == categoriaSeleccionada }
        }

    val gastoTotal: Double
        get() = todasLasTransacciones
            .filter { it.tipo == TipoTransaccion.GASTO }
            .sumOf { it.cantidad }

    val ingresoTotal: Double
        get() = todasLasTransacciones
            .filter { it.tipo == TipoTransaccion.INGRESO }
            .sumOf { it.cantidad }

    fun onCategoriaSelected(categoria: String) {
        categoriaSeleccionada = categoria
    }

    fun onEliminarTransaccion(transaccion: Transaccion) {
        transaccionRepository.delete(transaccion)
        todasLasTransacciones = transaccionRepository.getAll()
    }

    fun onRefresh() {
        todasLasTransacciones = transaccionRepository.getAll()
    }
}