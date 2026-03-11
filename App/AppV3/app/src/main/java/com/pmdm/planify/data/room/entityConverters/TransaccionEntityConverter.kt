package com.pmdm.planify.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.pmdm.planify.data.room.TransaccionEntity
import com.pmdm.planify.models.Transaccion

// --- De Modelo a Entity ---
fun Transaccion.toTransaccionEntity() = TransaccionEntity(
    id = id,
    nombre = nombre,
    fecha = fecha,
    categoria = categoria,
    cantidad = cantidad,
    tipo = tipo,
    iconNombre = icon.toNombre()
)

// --- De Entity a Modelo ---
fun TransaccionEntity.toTransaccion() = Transaccion(
    id = id,
    nombre = nombre,
    fecha = fecha,
    categoria = categoria,
    cantidad = cantidad,
    tipo = tipo,
    icon = iconNombre.toImageVector()
)

// --- Helpers para ImageVector ↔ String ---

// Extrae el nombre corto del icono, ej: "Filled.Restaurant" -> "Restaurant"
private fun ImageVector.toNombre(): String = name.substringAfterLast(".")

private fun String.toImageVector(): ImageVector = when (this) {
    "Restaurant"   -> Icons.Default.Restaurant
    "FitnessCenter" -> Icons.Default.FitnessCenter
    "LocalTaxi"    -> Icons.Default.LocalTaxi
    "AttachMoney"  -> Icons.Default.AttachMoney
    "ShoppingCart" -> Icons.Default.ShoppingCart
    else           -> Icons.Default.Help // Icono por defecto si no se reconoce
}