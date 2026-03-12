package com.pmdm.planify.ui.features.VentanaPrincipal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.models.Tarea
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader
import com.pmdm.planify.ui.features.Componentes.SurfaceBackground
import com.pmdm.planify.ui.navegation.EconomiaRoute
import com.pmdm.planify.ui.navegation.EstadoDeAnimoRoute
import com.pmdm.planify.ui.navegation.GymRoute
import com.pmdm.planify.ui.navegation.SettingsRoute
import com.pmdm.planify.ui.navegation.TareaRoute

// ── Colores ───────────────────────────────────────────────────────────────────
private val PrimaryLime      = Color(0xFFE2E722)
private val OnPrimary        = Color(0xFF1C1C0D)
private val PrimaryContainer = Color(0xFFF2F590)
private val SurfaceBg        = Color(0xFFFFFBFE)
private val SurfaceContainer = Color(0xFFF3F4F6)
private val TextPrimary      = Color(0xFF1C1B1F)
private val TextSecondary    = Color(0xFF757575)
private val MoodAngry        = Color(0xFFEF4444)
private val MoodSad          = Color(0xFFFB923C)
private val MoodFine         = Color(0xFFEAB308)
private val MoodGreat        = Color(0xFFE2E722)
private val FinanceGreen     = Color(0xFF16A34A)
private val FinanceRed       = Color(0xFFEF4444)

// ── SCREEN PRINCIPAL ─────────────────────────────────────────────────────────
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = SurfaceBg,
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
                    nombreUsuario   = state.nombreUsuario,
                    fraseBienvenida = state.fraseBienvenida,
                    onProfileClick  = { navController.navigate(SettingsRoute) }
                )
            }

            // ── Estado de ánimo ───────────────────────────────────────────────
            item {
                MoodSection(
                    animoSeleccionado = state.animoHoy,
                    onMoodClick       = { navController.navigate(EstadoDeAnimoRoute) }
                )
            }

            // ── Tareas ────────────────────────────────────────────────────────
            item {
                TareasSection(
                    pendientes    = state.tareasPendientesCount,
                    completadas   = state.tareasCompletadasCount,
                    proximasTareas = state.proximasTareas,
                    onVerTodas    = { navController.navigate(TareaRoute) }
                )
            }

            // ── Gym ───────────────────────────────────────────────────────────
            item {
                WorkoutSection(
                    rutinaNombre   = state.ultimaRutinaNombre,
                    rutinaDetalles = state.ultimaRutinaDetalles,
                    sesiones       = state.totalSesionesGym,
                    onVerRutinas   = { navController.navigate(GymRoute) }
                )
            }

            // ── Finanzas ──────────────────────────────────────────────────────
            item {
                FinanceSection(
                    gastoTotal    = state.gastoTotal,
                    ingresoTotal  = state.ingresoTotal,
                    barras        = state.ultimasTransaccionesLabels,
                    onVerFinanzas = { navController.navigate(EconomiaRoute) }
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// ── SECCIÓN ÁNIMO ─────────────────────────────────────────────────────────────
@Composable
fun MoodSection(
    animoSeleccionado: IconoEstadoAnimo?,
    onMoodClick: () -> Unit
) {
    val moodItems = listOf(
        Triple(Icons.Outlined.SentimentVeryDissatisfied, "Enfadado", MoodAngry  ) to IconoEstadoAnimo.MUYMAL,
        Triple(Icons.Outlined.SentimentDissatisfied,     "Triste",   MoodSad   ) to IconoEstadoAnimo.MAL,
        Triple(Icons.Outlined.SentimentSatisfied,        "Bien",     MoodFine  ) to IconoEstadoAnimo.BIEN,
        Triple(Icons.Filled.SentimentVerySatisfied,      "Genial",   MoodGreat ) to IconoEstadoAnimo.GENIAL
    )

    Card(
        colors    = CardDefaults.cardColors(containerColor = PrimaryContainer.copy(alpha = 0.4f)),
        shape     = RoundedCornerShape(28.dp),
        modifier  = Modifier.fillMaxWidth().clickable { onMoodClick() },
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "¿Cómo te sientes hoy?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                if (animoSeleccionado != null) {
                    Surface(color = PrimaryLime, shape = RoundedCornerShape(50)) {
                        Text(
                            animoSeleccionado.name,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnPrimary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                moodItems.forEach { (triple, icono) ->
                    val (icon, label, color) = triple
                    val isSelected = animoSeleccionado == icono
                    MoodItem(icon, label, color, isSelected)
                }
            }
        }
    }
}

@Composable
fun MoodItem(icon: ImageVector, label: String, color: Color, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier       = Modifier.size(56.dp),
            shape          = CircleShape,
            color          = if (isSelected) PrimaryLime else SurfaceBg,
            shadowElevation = 2.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, label, tint = if (isSelected) OnPrimary else color, modifier = Modifier.size(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            label,
            fontSize   = 11.sp,
            color      = TextSecondary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

// ── SECCIÓN TAREAS ────────────────────────────────────────────────────────────
@Composable
fun TareasSection(
    pendientes: Int,
    completadas: Int,
    proximasTareas: List<Tarea>,
    onVerTodas: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Text("Tareas de hoy",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(color = PrimaryContainer, shape = RoundedCornerShape(50)) {
                    Text("$pendientes pendientes",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimary)
                }
                TextButton(onClick = onVerTodas, contentPadding = PaddingValues(0.dp)) {
                    Text("Ver todas", fontSize = 12.sp, color = TextSecondary)
                }
            }
        }

        // Barra de progreso completadas/total
        val total = pendientes + completadas
        if (total > 0) {
            val progreso = completadas.toFloat() / total.toFloat()
            Column {
                LinearProgressIndicator(
                    progress = { progreso },
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(50)),
                    color    = PrimaryLime,
                    trackColor = SurfaceContainer
                )
                Text(
                    "$completadas de $total completadas",
                    fontSize = 11.sp,
                    color    = TextSecondary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        if (proximasTareas.isEmpty()) {
            Text("¡Sin tareas pendientes! 🎉", color = TextSecondary, fontSize = 14.sp)
        } else {
            proximasTareas.forEach { tarea -> TareaRow(tarea = tarea) }
        }
    }
}

@Composable
fun TareaRow(tarea: Tarea) {
    Surface(
        color    = SurfaceBg,
        shape    = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 1.dp
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
                    tarea.titulo,
                    fontWeight     = FontWeight.SemiBold,
                    textDecoration = if (tarea.completada) TextDecoration.LineThrough else null,
                    color          = if (tarea.completada) TextSecondary else TextPrimary
                )
                Text(tarea.etiqueta.name, fontSize = 12.sp, color = TextSecondary)
            }
        }
    }
}

// ── SECCIÓN GYM ───────────────────────────────────────────────────────────────
@Composable
fun WorkoutSection(
    rutinaNombre: String,
    rutinaDetalles: String,
    sesiones: Int,
    onVerRutinas: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF1C1B16))
            .clickable { onVerRutinas() }
    ) {
        Box(modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(0.85f)))
        ))
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color  = Color.Black.copy(0.4f),
                    shape  = RoundedCornerShape(50),
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
                // Sesiones badge
                if (sesiones > 0) {
                    Surface(color = PrimaryLime, shape = RoundedCornerShape(50)) {
                        Text(
                            "$sesiones rutinas",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 11.sp, fontWeight = FontWeight.Bold, color = OnPrimary
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    if (rutinaDetalles.isNotEmpty()) {
                        Text(rutinaDetalles, color = Color.White.copy(0.6f), fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    Text(
                        rutinaNombre,
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 30.sp
                    )
                }
                Surface(color = PrimaryLime, shape = RoundedCornerShape(16.dp), modifier = Modifier.size(56.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Filled.PlayArrow, "Ir al gym", tint = OnPrimary, modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }
}

// ── SECCIÓN FINANZAS ──────────────────────────────────────────────────────────
@Composable
fun FinanceSection(
    gastoTotal: Double,
    ingresoTotal: Double,
    barras: List<Pair<String, Double>>,
    onVerFinanzas: () -> Unit
) {
    Card(
        colors   = CardDefaults.cardColors(containerColor = SurfaceContainer),
        shape    = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth().clickable { onVerFinanzas() }
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color    = PrimaryContainer,
                        shape    = RoundedCornerShape(12.dp),
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
                    Text(
                        "$${String.format("%.2f", gastoTotal)}",
                        fontWeight = FontWeight.Bold,
                        fontSize   = 22.sp,
                        color      = TextPrimary
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.TrendingUp, null, tint = FinanceGreen, modifier = Modifier.size(14.dp))
                        Text(
                            " $${String.format("%.2f", ingresoTotal)} ingresos",
                            color    = FinanceGreen,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Mini gráfico con las últimas transacciones reales
            if (barras.isNotEmpty()) {
                val maxCantidad = barras.maxOfOrNull { it.second } ?: 1.0
                Row(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.Bottom
                ) {
                    barras.forEachIndexed { index, (label, cantidad) ->
                        val fraccion = (cantidad / maxCantidad).toFloat().coerceIn(0.1f, 1f)
                        val esUltimo = index == barras.lastIndex
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(fraccion)
                                    .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                    .background(if (esUltimo) PrimaryLime else PrimaryLime.copy(0.35f))
                            )
                        }
                    }
                }
            } else {
                // Sin datos: placeholder visual
                Row(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.Bottom
                ) {
                    listOf(0.4f, 0.65f, 0.3f, 0.85f, 0.55f, 0.2f, 0.2f).forEachIndexed { i, f ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(f)
                                    .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                    .background(if (i == 4) PrimaryLime else PrimaryLime.copy(0.3f))
                            )
                        }
                    }
                }
            }
        }
    }
}