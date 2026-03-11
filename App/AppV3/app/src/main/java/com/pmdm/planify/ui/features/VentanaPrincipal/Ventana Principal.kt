package com.pmdm.planify.ui.features.VentanaPrincipal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.models.Tarea
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader
import com.pmdm.planify.ui.features.Componentes.SurfaceBackground
import com.pmdm.planify.ui.navegation.SettingsRoute

// --- Colores ---
val PrimaryLime = Color(0xFFE2E722)
val OnPrimary = Color(0xFF1C1C0D)
val PrimaryContainer = Color(0xFFF2F590)
val SurfaceBackground = Color(0xFFFFFBFE)
val SurfaceContainer = Color(0xFFF3F4F6)
val SurfaceVariant = Color(0xFFE7E3EB)   // definido aquí, ya no se importa de otro package
val TextPrimary = Color(0xFF1C1B1F)
val TextSecondary = Color(0xFF757575)

val MoodAngry = Color(0xFFEF4444)
val MoodSad = Color(0xFFFB923C)
val MoodFine = Color(0xFFEAB308)
val MoodGreat = Color(0xFFE2E722)

// --- Componente Principal ---
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = SurfaceBackground,
        bottomBar = { PlanifyBottomBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                PlanifyHeader(
                    nombreUsuario = state.nombreUsuario,
                    fraseBienvenida = state.fraseBienvenida,
                    onProfileClick = { navController.navigate(SettingsRoute) }
                )
            }

            item { MoodSection() }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Tareas de hoy",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            color = PrimaryContainer,
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                "${state.tareasPendientesCount} pendientes",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnPrimary
                            )
                        }
                    }

                    if (state.proximasTareas.isEmpty()) {
                        Text(
                            "¡Sin tareas pendientes! 🎉",
                            color = TextSecondary,
                            fontSize = 14.sp
                        )
                    } else {
                        state.proximasTareas.forEach { tarea ->
                            TareaRow(tarea = tarea)
                        }
                    }
                }
            }

            item { WorkoutSection() }
            item { FinanceSection() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun TareaRow(tarea: Tarea) {
    Surface(
        color = SurfaceBackground,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            if (tarea.completada) {
                Box(
                    modifier = Modifier.size(24.dp).background(Color(0xFFD4E157), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Check, null, tint = Color.Black, modifier = Modifier.size(16.dp))
                }
            } else {
                Box(modifier = Modifier.size(24.dp).border(2.dp, Color.LightGray, CircleShape))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tarea.titulo,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = if (tarea.completada) TextDecoration.LineThrough else null,
                    color = if (tarea.completada) TextSecondary else TextPrimary
                )
                Text(
                    text = tarea.etiqueta.name,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun MoodSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = PrimaryContainer.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                "¿Cómo te sientes hoy?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
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
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, label, tint = color, modifier = Modifier.size(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = TextSecondary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun WorkoutSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color.DarkGray)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(0.8f)))
        ))
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Surface(
                    color = Color.Black.copy(0.4f),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.White.copy(0.2f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.FitnessCenter, null, tint = PrimaryLime, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Rutina de Hoy", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("DÍA 4", color = PrimaryLime, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Pierna y\nGlúteos", color = Color.White, fontSize = 32.sp, lineHeight = 36.sp)
                }
                Surface(color = PrimaryLime, shape = RoundedCornerShape(16.dp), modifier = Modifier.size(56.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Filled.PlayArrow, "Start", tint = OnPrimary, modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FinanceSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = PrimaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Outlined.AccountBalanceWallet, null, tint = OnPrimary)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Finanzas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Este mes", color = TextSecondary, fontSize = 12.sp)
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Total gastado", color = TextSecondary, fontSize = 12.sp)
                    Text("$845.00", fontWeight = FontWeight.Normal, fontSize = 24.sp)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                FinanceBar(0.4f, false)
                FinanceBar(0.65f, false)
                FinanceBar(0.3f, false, color = PrimaryLime.copy(alpha = 0.3f))
                FinanceBar(0.85f, false)
                FinanceBar(0.55f, true)
                FinanceBar(0.2f, false, isFaded = true)
                FinanceBar(0.2f, false, isFaded = true)
            }
        }
    }
}

@Composable
fun FinanceBar(fillFraction: Float, isToday: Boolean, color: Color = Color.White, isFaded: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().width(36.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (isToday) {
            Surface(color = OnPrimary, shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(bottom = 6.dp)) {
                Text("Hoy", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fillFraction)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(if (isToday) PrimaryLime else color.copy(alpha = if (isFaded) 0.5f else 1f))
        )
    }
}

@Preview(showBackground = true, heightDp = 1000)
@Composable
fun DashboardPreview() {
    MaterialTheme {
        Box(modifier = Modifier.background(Color.White)) {
            DashboardScreen(
                navController = rememberNavController(),
                viewModel = HomeViewModel(
                    usuarioRepo = TODO(),
                    tareaRepo = TODO(),
                    estadoAnimoRepo = TODO()
                )
            )
        }
    }
}

