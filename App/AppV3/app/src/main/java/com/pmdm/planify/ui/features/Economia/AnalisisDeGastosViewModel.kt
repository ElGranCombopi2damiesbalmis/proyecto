package com.pmdm.planify.ui.features.Economia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AnalisisDeGastosViewModel @Inject constructor() : ViewModel() {

    // --- Estado de la UI ---
    var categoriaSeleccionada by mutableStateOf("Todo")
        private set

    // Lista de transacciones (Mock que luego vendrá de Room)
    private var todasLasTransacciones by mutableStateOf(getMockTransactions())

    // Propiedad calculada para la lista filtrada
    val transaccionesFiltradas: List<Transaccion>
        get() = if (categoriaSeleccionada == "Todo") {
            todasLasTransacciones
        } else {
            todasLasTransacciones.filter { it.categoria == categoriaSeleccionada }
        }

    // Cálculo dinámico del total de gastos
    val gastoTotal: Double
        get() = todasLasTransacciones
            .filter { it.tipo == TipoTransaccion.GASTO }
            .sumOf { it.cantidad }

    // --- Eventos ---
    fun onCategoriaSelected(categoria: String) {
        categoriaSeleccionada = categoria
    }

    private fun getMockTransactions(): List<Transaccion> {
        val now = LocalDateTime.now()
        return listOf(
            Transaccion(nombre = "Suscripción Gym", cantidad = 45.0, fecha = now.minusDays(1), categoria = "Salud", tipo = TipoTransaccion.GASTO, icon = Icons.Default.FitnessCenter),
            Transaccion(nombre = "Compra Mercadona", cantidad = 82.30, fecha = now.minusDays(2), categoria = "Comida", tipo = TipoTransaccion.GASTO, icon = Icons.Default.ShoppingCart),
            Transaccion(nombre = "Nómina Empresa", cantidad = 1850.0, fecha = now.minusDays(5), categoria = "Hogar", tipo = TipoTransaccion.INGRESO, icon = Icons.Default.Payments),
            Transaccion(nombre = "Gasolina", cantidad = 60.0, fecha = now.minusDays(3), categoria = "Transporte", tipo = TipoTransaccion.GASTO, icon = Icons.Default.DirectionsCar),
            Transaccion(nombre = "Cena Restaurante", cantidad = 35.50, fecha = now.minusDays(4), categoria = "Comida", tipo = TipoTransaccion.GASTO, icon = Icons.Default.Restaurant)
        )
    }
}