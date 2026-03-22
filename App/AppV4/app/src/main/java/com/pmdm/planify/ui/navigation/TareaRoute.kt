package com.pmdm.planify.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen
import kotlinx.serialization.Serializable

@Serializable
object TareaRoute

sealed class Rutas(val ruta: String) {
    object Inicio : Rutas("inicio")
    object Tarea : Rutas("tareas")
    object Gym : Rutas("gym")
    object Gastos : Rutas("gastos")
    object Animo : Rutas("animo")
}