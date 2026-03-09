package com.pmdm.planify.ui.features.Economia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.pmdm.planify.data.TransaccionRepository
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject
// Categorías disponibles con su icono asociado
data class CategoriaItem(val nombre: String, val icono: ImageVector)

val CATEGORIAS = listOf(
    CategoriaItem("Comida",      Icons.Default.Restaurant),
    CategoriaItem("Transporte",  Icons.Default.DirectionsCar),
    CategoriaItem("Hogar",       Icons.Default.Home),
    CategoriaItem("Salud",       Icons.Default.FitnessCenter),
    CategoriaItem("Ocio",        Icons.Default.SportsEsports),
    CategoriaItem("Nómina",      Icons.Default.Payments),
    CategoriaItem("Varios",      Icons.Default.MoreHoriz)
)

@HiltViewModel
class AnalisisDeGastosViewModel @Inject constructor(
    private val transaccionRepository: TransaccionRepository
) : ViewModel() {

    // ── Lista y filtro ────────────────────────────────────────────────────────
    var categoriaSeleccionada by mutableStateOf("Todo")
        private set

    private var todasLasTransacciones by mutableStateOf(transaccionRepository.getAll())

    val transaccionesFiltradas: List<Transaccion>
        get() = if (categoriaSeleccionada == "Todo") todasLasTransacciones
        else todasLasTransacciones.filter { it.categoria == categoriaSeleccionada }

    val gastoTotal: Double
        get() = todasLasTransacciones.filter { it.tipo == TipoTransaccion.GASTO }.sumOf { it.cantidad }

    val ingresoTotal: Double
        get() = todasLasTransacciones.filter { it.tipo == TipoTransaccion.INGRESO }.sumOf { it.cantidad }

    fun onCategoriaSelected(cat: String) { categoriaSeleccionada = cat }

    fun onEliminarTransaccion(t: Transaccion) {
        transaccionRepository.delete(t)
        todasLasTransacciones = transaccionRepository.getAll()
    }

    // ── Estado del diálogo ────────────────────────────────────────────────────
    var mostrarDialogo by mutableStateOf(false)
        private set

    var nombreNueva     by mutableStateOf("")
    var cantidadNueva   by mutableStateOf("")
    var categoriaNueva  by mutableStateOf(CATEGORIAS.first())
    var tipoNueva       by mutableStateOf(TipoTransaccion.GASTO)
    var errorNombre     by mutableStateOf(false)
    var errorCantidad   by mutableStateOf(false)

    fun abrirDialogo() {
        nombreNueva    = ""
        cantidadNueva  = ""
        categoriaNueva = CATEGORIAS.first()
        tipoNueva      = TipoTransaccion.GASTO
        errorNombre    = false
        errorCantidad  = false
        mostrarDialogo = true
    }

    fun cerrarDialogo() { mostrarDialogo = false }

    fun onNombreChange(v: String)   { nombreNueva = v;   errorNombre = false }
    fun onCantidadChange(v: String) { cantidadNueva = v; errorCantidad = false }
    fun onCategoriaDialogoChange(c: CategoriaItem) { categoriaNueva = c }
    fun onTipoChange(t: TipoTransaccion) { tipoNueva = t }

    fun guardarTransaccion() {
        errorNombre   = nombreNueva.isBlank()
        errorCantidad = cantidadNueva.toDoubleOrNull() == null || cantidadNueva.isBlank()
        if (errorNombre || errorCantidad) return

        val nueva = Transaccion(
            nombre    = nombreNueva.trim(),
            cantidad  = cantidadNueva.toDouble(),
            fecha     = LocalDateTime.now(),
            categoria = categoriaNueva.nombre,
            tipo      = tipoNueva,
            icon      = categoriaNueva.icono
        )
        transaccionRepository.insert(nueva)
        todasLasTransacciones = transaccionRepository.getAll()
        mostrarDialogo = false
    }
}