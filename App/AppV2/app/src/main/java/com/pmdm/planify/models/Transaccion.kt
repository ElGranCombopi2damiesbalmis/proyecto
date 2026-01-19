package com.pmdm.planify.models

import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime
import java.util.UUID

enum class TipoTransaccion { INGRESO, GASTO }

data class Transaccion(
    val id: String = java.util.UUID.randomUUID().toString(),
    val nombre: String,
    val fecha: String, // O LocalDate
    val categoria: String,
    val cantidad: Double,
    val tipo: TipoTransaccion,
    val icon: ImageVector
)
