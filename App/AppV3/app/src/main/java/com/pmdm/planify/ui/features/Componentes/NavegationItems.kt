package com.pmdm.planify.ui.features.Componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pmdm.planify.ui.navegation.EconomiaRoute
import com.pmdm.planify.ui.navegation.TareaRoute
import com.pmdm.planify.ui.navegation.HomeRoute
import com.pmdm.planify.ui.navegation.GymRoute
import com.pmdm.planify.ui.navegation.EstadoDeAnimoRoute

@Composable
fun PlanifyBottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFFF4F4F5), // Tu SurfaceContainer
        tonalElevation = 0.dp
    ) {
        // Lista de destinos vinculados a tus objetos Route
        val items = listOf(
            Triple("Tareas", Icons.Filled.CalendarMonth, TareaRoute),
            Triple("Gym", Icons.Filled.FitnessCenter, GymRoute),
            Triple("Inicio", Icons.Filled.Home, HomeRoute),
            Triple("Gastos", Icons.Filled.Payments, EconomiaRoute),
            Triple("Ánimo", Icons.Filled.SentimentSatisfied, EstadoDeAnimoRoute)
        )

        // Observamos la ruta actual para saber qué icono iluminar
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { (label, icon, route) ->
            // Verificamos si la ruta actual coincide con este ítem de la lista
            val isSelected = currentDestination?.hierarchy?.any {
                it.route?.contains(route::class.simpleName ?: "") == true
            } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    // Navegación optimizada
                    navController.navigate(route) {
                        // Vuelve a la pantalla inicial del grafo para no acumular historial
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Evita múltiples copias de la misma pantalla si pulsas varias veces
                        launchSingleTop = true
                        // Restaura el estado (scroll, filtros) al volver a la pantalla
                        restoreState = true
                    }
                },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1C1C0D), // OnPrimary
                    selectedTextColor = Color(0xFF1C1C0D), // TextPrimary
                    indicatorColor = Color(0xFFF2F5A9),    // PrimaryContainer
                    unselectedIconColor = Color(0xFF64748B), // TextSecondary
                    unselectedTextColor = Color(0xFF64748B)
                )
            )
        }
    }
}