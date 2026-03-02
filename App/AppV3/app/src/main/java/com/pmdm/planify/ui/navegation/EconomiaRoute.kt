package com.pmdm.planify.ui.navegation

import GastosScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.AnalisisDeGastos.GastosScreen
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import kotlinx.serialization.Serializable

@Serializable
object EconomiaRoute

fun NavGraphBuilder.gastosDestination(
    vm: AnalisisDeGastosViewModel,
    navController: NavHostController,
    onNavigateToNuevaTransaccion: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    composable<EconomiaRoute> {
        GastosScreen(
            vm = vm,
            navController = navController,
            onNavigateToNuevaTransaccion = onNavigateToNuevaTransaccion,
            onNavigateToSettings = onNavigateToSettings
        )
    }
}