package com.pmdm.planify.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object TransaccionRoute

fun NavGraphBuilder.transaccionDestination(
    onBack: () -> Unit
) {
    composable<TransaccionRoute> {
        // Aquí iría tu Screen de "Añadir Gasto"
        // Por ahora, si no lo tienes, puedes poner un placeholder:
        Text("Pantalla de Nueva Transacción", modifier = Modifier.clickable { onBack() })
    }
}