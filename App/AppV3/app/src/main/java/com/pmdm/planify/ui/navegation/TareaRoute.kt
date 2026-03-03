package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen
import kotlinx.serialization.Serializable

@Serializable
object TareaRoute

sealed class Routes(val route: String) {
    object Principal : Routes("principal")
    object Tareas : Routes("tareas")
    // Aquí añadirías el resto: Gastos, Gym, etc.
}