package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.VentanaPrincipal.DashboardScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.inicioDestination() {
    composable<HomeRoute> {
        DashboardScreen() // Nombre de la función en Ventana Principal.kt
    }
}