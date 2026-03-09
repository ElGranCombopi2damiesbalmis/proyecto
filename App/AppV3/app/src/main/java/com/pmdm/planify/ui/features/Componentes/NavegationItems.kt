package com.pmdm.planify.ui.features.Componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

val TextSecondary = Color(0xFF64748B)
val TextPrimary = Color(0xFF1C1C0D)
val SurfaceBackground = Color(0xFFFFFFFF)

@Composable
fun PlanifyHeader(
    nombreUsuario: String = "Andrea",
    fraseBienvenida: String = "Hola, de nuevo",
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp), // Un poco más de espacio inferior
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = fraseBienvenida,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
            Text(
                text = nombreUsuario,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier.clickable { onProfileClick() }
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = Color.LightGray
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.White)
                }
            }
            // Indicador de estado online (verde)
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(Color(0xFF22C55E), CircleShape)
                    .border(2.dp, SurfaceBackground, CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

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