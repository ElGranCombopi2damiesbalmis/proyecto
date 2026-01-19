package com.pmdm.planify.models

import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime
import java.util.UUID

data class Transaccion(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val fecha: LocalDateTime?, // O LocalDate
    val categoria: String,
    val cantidad: Double,
    val tipo: TipoTransaccion,
    val icon: ImageVector
)
