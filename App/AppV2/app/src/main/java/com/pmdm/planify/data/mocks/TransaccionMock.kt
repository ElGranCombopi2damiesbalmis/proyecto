package com.pmdm.appV1.data.mocks

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

enum class TipoTransaccion { INGRESO, GASTO }

data class Transaccion(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val fecha: String, // O LocalDate
    val categoria: String,
    val cantidad: Double,
    val tipo: com.pmdm.appV1.data.mocks.TipoTransaccion,
    val icon: ImageVector
)