package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoDeAnimoScreen
import kotlinx.serialization.Serializable

@Serializable
object EstadoDeAnimoRoute

fun NavGraphBuilder.animoDestination() {
    composable<EstadoDeAnimoRoute> {
        EstadoDeAnimoScreen(
            vm = TODO()
        )
    }
}