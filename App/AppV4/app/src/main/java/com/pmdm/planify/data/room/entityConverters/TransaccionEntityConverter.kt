
package com.pmdm.planify.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.ui.graphics.vector.ImageVector
import com.pmdm.planify.data.room.TransaccionEntity
import com.pmdm.planify.models.Transaccion

fun Transaccion.toTransaccionEntity() = TransaccionEntity(
    id = id,
    nombre = nombre,
    fecha = fecha,
    categoria = categoria,
    cantidad = cantidad,
    tipo = tipo,
    iconNombre = icon.toNombre()
)

fun TransaccionEntity.toTransaccion() = Transaccion(
    id = id,
    nombre = nombre,
    fecha = fecha,
    categoria = categoria,
    cantidad = cantidad,
    tipo = tipo,
    icon = iconNombre.toImageVector()
)

private fun ImageVector.toNombre(): String = name.substringAfterLast('.')

private fun String.toImageVector(): ImageVector = when (this) {
    "Restaurant" -> Icons.Default.Restaurant
    "DirectionsCar" -> Icons.Default.DirectionsCar
    "Home" -> Icons.Default.Home
    "FitnessCenter" -> Icons.Default.FitnessCenter
    "SportsEsports" -> Icons.Default.SportsEsports
    "Payments" -> Icons.Default.Payments
    "AttachMoney" -> Icons.Default.AttachMoney
    "ShoppingCart" -> Icons.Default.ShoppingCart
    "MoreHoriz" -> Icons.Default.MoreHoriz
    else -> Icons.Default.Help
}
