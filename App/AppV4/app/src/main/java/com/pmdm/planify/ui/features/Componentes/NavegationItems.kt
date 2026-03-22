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
import com.pmdm.planify.ui.navigation.EconomiaRoute
import com.pmdm.planify.ui.navigation.TareaRoute
import com.pmdm.planify.ui.navigation.HomeRoute
import com.pmdm.planify.ui.navigation.GymRoute
import com.pmdm.planify.ui.navigation.EstadoDeAnimoRoute

val TextSecondary = Color(0xFF64748B)
val TextPrimary   = Color(0xFF1C1C0D)
val SurfaceBackground = Color(0xFFFFFFFF)

// ─────────────────────────────────────────────────────────────────────────────
// HEADER
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun PlanifyHeader(
    nombreUsuario: String = "Andrea",
    fraseBienvenida: String = "Hola, de nuevo",
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(fraseBienvenida, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            Text(nombreUsuario, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = TextPrimary)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(modifier = Modifier.clickable { onProfileClick() }) {
            Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = Color.LightGray) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.White)
                }
            }
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

// ─────────────────────────────────────────────────────────────────────────────
// BOTTOM BAR
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun PlanifyBottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFFF4F4F5),
        tonalElevation = 0.dp
    ) {
        val items = listOf(
            Triple("Tareas",  Icons.Filled.CalendarMonth,       TareaRoute),
            Triple("Gym",     Icons.Filled.FitnessCenter,        GymRoute),
            Triple("Inicio",  Icons.Filled.Home,                 HomeRoute),
            Triple("Gastos",  Icons.Filled.Payments,             EconomiaRoute),
            Triple("Ánimo",   Icons.Filled.SentimentSatisfied,   EstadoDeAnimoRoute)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { (label, icon, route) ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route?.contains(route::class.simpleName ?: "") == true
            } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor   = Color(0xFF1C1C0D),
                    selectedTextColor   = Color(0xFF1C1C0D),
                    indicatorColor      = Color(0xFFF2F5A9),
                    unselectedIconColor = Color(0xFF64748B),
                    unselectedTextColor = Color(0xFF64748B)
                )
            )
        }
    }
}