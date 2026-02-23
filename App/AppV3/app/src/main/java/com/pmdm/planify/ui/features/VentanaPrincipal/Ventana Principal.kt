package com.pmdm.planify.ui.features.VentanaPrincipal

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.planify.ui.features.PlanifyViewModel
import com.pmdm.planify.ui.navegation.EconomiaRoute
import com.pmdm.planify.ui.navegation.EstadoAnimoRoute
import com.pmdm.planify.ui.navegation.GymRoute
import com.pmdm.planify.ui.navegation.HomeRoute
import com.pmdm.planify.ui.navegation.SettingsRoute
import com.pmdm.planify.ui.navegation.TareaRoute

// --- Colores extraídos de tu diseño ---
val PrimaryLime = Color(0xFFE2E722)
val OnPrimary = Color(0xFF1C1C0D)
val PrimaryContainer = Color(0xFFF2F5A0)
val SurfaceBackground = Color(0xFFFFFFFF)
val SurfaceContainer = Color(0xFFF3F4F6)
val TextPrimary = Color(0xFF111827)
val TextSecondary = Color(0xFF6B7280)
val MoodAngry = Color(0xFFEF4444)
val MoodSad = Color(0xFF3B82F6)
val MoodFine = Color(0xFF10B981)

@Composable
fun DashboardScreen(vm: PlanifyViewModel) {
    val usuario = vm.usuarioState
    val tareas = vm.listaTareas.take(2) // Tomamos solo las 2 primeras para el resumen
    val saldo = vm.economiaState?.saldo ?: 0.0

    Scaffold(
        containerColor = Color(0xFFFAFAFA),
        bottomBar = { PlanifyBottomBar(HomeRoute, vm.navigateTo) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // 1. Header Real
            HeaderSection(nombre = usuario?.nombre ?: "Usuario", onProfileClick = { vm.navigateTo(
                SettingsRoute
            ) })

            // 2. Estado de Ánimo
            MoodSection()

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Tareas (Resumen)
            TasksSection(tareas = tareas, onVerTodo = { vm.navigateTo(TareaRoute) })

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Workout (Gym)
            WorkoutSection(onClick = { vm.navigateTo(GymRoute) })

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Finanzas Real
            FinanceSection(saldoTotal = saldo, onClick = { vm.navigateTo(EconomiaRoute) })

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun HeaderSection(nombre: String, onProfileClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Hola, de nuevo", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            Text(nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = TextPrimary)
        }
        Surface(
            modifier = Modifier.size(48.dp).clickable { onProfileClick() },
            shape = CircleShape,
            color = Color.LightGray
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun MoodSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = PrimaryContainer.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("¿Cómo te sientes hoy?", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                MoodItem(Icons.Outlined.SentimentVeryDissatisfied, "Enfadado", MoodAngry, false)
                MoodItem(Icons.Outlined.SentimentDissatisfied, "Triste", MoodSad, false)
                MoodItem(Icons.Outlined.SentimentSatisfied, "Bien", MoodFine, false)
                MoodItem(Icons.Filled.SentimentVerySatisfied, "Genial", OnPrimary, true)
            }
        }
    }
}

@Composable
fun MoodItem(icon: ImageVector, label: String, color: Color, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            color = if (isSelected) PrimaryLime else SurfaceBackground,
            shadowElevation = 2.dp
        ) {
            Icon(icon, label, tint = color, modifier = Modifier.padding(12.dp))
        }
        Text(label, fontSize = 11.sp, color = TextSecondary, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun TasksSection(tareas: List<com.pmdm.planify.models.Tarea>, onVerTodo: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text("Mis Tareas", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Medium)
                TextButton(onClick = onVerTodo) { Text("Ver Todo", color = OnPrimary, fontWeight = FontWeight.Bold) }
            }

            // Calendario Strip Simple
            Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), Arrangement.SpaceBetween) {
                listOf("L", "M", "X", "J", "V", "S", "D").forEachIndexed { i, d ->
                    CalendarDayItem(d, (12 + i).toString(), i == 2)
                }
            }

            // Lista de tareas real
            tareas.forEach { tarea ->
                TaskItem(tarea.titulo, tarea.descripcion, Icons.Rounded.Videocam, tarea.completada)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CalendarDayItem(day: String, date: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(day, fontSize = 11.sp, color = TextSecondary)
        Box(
            modifier = Modifier.size(36.dp).background(if (isSelected) PrimaryLime else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(date, fontWeight = FontWeight.Bold, color = if (isSelected) OnPrimary else TextSecondary)
        }
    }
}

@Composable
fun TaskItem(title: String, subtitle: String, icon: ImageVector, isCompleted: Boolean) {
    Surface(
        color = SurfaceBackground,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(16.dp), Alignment.CenterVertically) {
            Icon(
                if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                null,
                tint = if (isCompleted) PrimaryLime else Color.LightGray
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(title, fontWeight = FontWeight.SemiBold, textDecoration = if (isCompleted) TextDecoration.LineThrough else null)
                Text(subtitle, fontSize = 12.sp, color = TextSecondary)
            }
        }
    }
}

@Composable
fun WorkoutSection(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(28.dp))
            .background(Color.DarkGray).clickable { onClick() }
    ) {
        Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(0.7f)))))
        Column(Modifier.fillMaxSize().padding(20.dp), Arrangement.SpaceBetween) {
            Surface(color = Color.Black.copy(0.3f), shape = CircleShape) {
                Text("Rutina de Hoy", color = PrimaryLime, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), fontSize = 12.sp)
            }
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.Bottom) {
                Text("Pierna y\nGlúteos", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp)
                Surface(color = PrimaryLime, shape = RoundedCornerShape(12.dp)) {
                    Icon(Icons.Filled.PlayArrow, null, Modifier.padding(8.dp), tint = OnPrimary)
                }
            }
        }
    }
}

@Composable
fun FinanceSection(saldoTotal: Double, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Row {
                    Surface(color = PrimaryLime, shape = RoundedCornerShape(12.dp), modifier = Modifier.size(48.dp)) {
                        Icon(Icons.Outlined.AccountBalanceWallet, null, Modifier.padding(12.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Finanzas", fontWeight = FontWeight.Bold)
                        Text("Saldo actual", color = TextSecondary, fontSize = 12.sp)
                    }
                }
                Text("$${saldoTotal}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth().height(60.dp), Arrangement.SpaceEvenly, Alignment.Bottom) {
                FinanceBar(0.4f, false); FinanceBar(0.7f, false); FinanceBar(0.9f, true); FinanceBar(0.5f, false)
            }
        }
    }
}

@Composable
fun PlanifyBottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = SurfaceContainer,
        tonalElevation = 0.dp
    ) {
        // Lista de (Etiqueta, Icono, Ruta)
        val items = listOf(
            Triple("Inicio", Icons.Filled.Home, HomeRoute),
            Triple("Tareas", Icons.Filled.CalendarMonth, TareaRoute),
            Triple("Gym", Icons.Filled.FitnessCenter, GymRoute),
            Triple("Gastos", Icons.Filled.Payments, GastosRoute),
            Triple("Ánimo", Icons.Filled.SentimentSatisfied, EstadoAnimoRoute)
        )

        items.forEach { (label, icon, route) ->
            val isSelected = currentRoute == route

            NavigationBarItem(
                selected = isSelected,
                onClick = { if (!isSelected) onNavigate(route) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = OnPrimary,
                    selectedTextColor = TextPrimary,
                    indicatorColor = PrimaryLime,
                    unselectedIconColor = TextSecondary.copy(alpha = 0.6f),
                    unselectedTextColor = TextSecondary.copy(alpha = 0.6f)
                )
            )
        }
    }
}

@Composable
fun FinanceBar(height: Float, isToday: Boolean) {
    Box(Modifier.fillMaxHeight(height).width(40.dp).clip(RoundedCornerShape(8.dp))
        .background(if (isToday) PrimaryLime else Color.White))
}

@Preview(showBackground = true, heightDp = 1000)
@Composable
fun DashboardPreview() {
    MaterialTheme {
        DashboardScreen()
    }
}