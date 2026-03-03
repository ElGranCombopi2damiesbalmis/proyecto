package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen
import kotlinx.serialization.Serializable

@Serializable
object TareaRoute

fun NavGraphBuilder.tareasDestination() {
    composable<TareaRoute> {
        TaskManagerScreen()
    }
}