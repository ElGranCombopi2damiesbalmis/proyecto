package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.RutinasDeGimnasio.RutinasGimnasioScreen
import kotlinx.serialization.Serializable

@Serializable
object GymRoute

fun NavGraphBuilder.gymDestination(
    onBack: () -> Unit
) {
    composable<GymRoute> {
    }
}