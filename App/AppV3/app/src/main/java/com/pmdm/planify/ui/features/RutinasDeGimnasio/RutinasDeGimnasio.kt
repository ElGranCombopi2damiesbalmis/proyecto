package com.pmdm.planify.ui.features.RutinasDeGimnasio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.models.Rutina
import com.pmdm.planify.models.TipoEtiquetaRutina
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader

// --- COLORES DEL TEMA GIMNASIO ---
private val MdSysColorPrimary = Color(0xFF725C00)
private val MdSysColorOnPrimary = Color(0xFFFFFFFF)
private val MdSysColorSurface = Color(0xFFFFFBFF)
private val MdSysColorOnSurface = Color(0xFF1E1B16)
private val MdSysColorOnSurfaceVariant = Color(0xFF4C4739)
private val MdSysColorOutline = Color(0xFF7D7767)
private val MdSysColorSurfaceContainer = Color(0xFFF7F1E2)
private val MdSysColorSurfaceContainerHigh = Color(0xFFEFEBDC)
private val BadgeOrangeBg = Color(0xFFF97316)
private val BadgeGreenBg = Color(0xFF16A34A)

// --- COMPONENTE PRINCIPAL (Conexión NavHost) ---
@Composable
fun RutinasGimnasioScreen(
    navController: NavHostController,
    vm: GymVM // Asegúrate de que GymVM esté inyectado con hiltViewModel() en el NavHost
) {
    val uriHandler = LocalUriHandler.current

    RutinasGimnasioContent(
        navController = navController,
        rutinas = vm.rutinas,
        sesiones = vm.sesiones.toString(),
        tiempoFormateado = vm.formatearTiempo(),
        racha = "${vm.racha} días",
        onPlayVideo = { rutina ->
            vm.onEvent(GymEvent.OnPlayVideo(rutina))
            if (rutina.videoUrl.isNotEmpty()) {
                uriHandler.openUri(rutina.videoUrl)
            }
        }
    )
}

@Composable
fun RutinasGimnasioContent(
    navController: NavHostController,
    rutinas: List<Rutina>,
    sesiones: String,
    tiempoFormateado: String,
    racha: String,
    onPlayVideo: (Rutina) -> Unit
) {
    Scaffold(
        containerColor = MdSysColorSurface,
        bottomBar = { PlanifyBottomBar(navController) } // Usamos la barra común
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // HEADER CENTRALIZADO
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    PlanifyHeader(
                        nombreUsuario = "Andrea",
                        fraseBienvenida = "Tu entrenamiento",
                        onProfileClick = { /* navController.navigate(SettingsRoute) */ }
                    )
                }
            }

            // ESTADÍSTICAS DINÁMICAS
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(Icons.Default.FitnessCenter, "Sesiones", sesiones)
                    StatCard(Icons.Default.Schedule, "Tiempo", tiempoFormateado)
                    StatCard(Icons.Default.LocalFireDepartment, "Racha", racha)
                }
            }

            // TÍTULO SECCIÓN
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mis Rutinas",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MdSysColorOnSurface
                        )
                    )
                    TextButton(onClick = { }) {
                        Text("Ver todo", color = MdSysColorPrimary)
                    }
                }
            }

            // LISTA DE RUTINAS
            items(rutinas) { rutina ->
                GymRoutineCard(
                    rutina = rutina,
                    onStartClick = { onPlayVideo(rutina) }
                )
            }
        }
    }
}

// --- COMPONENTES INTERNOS ---

@Composable
fun GymRoutineCard(rutina: Rutina, onStartClick: () -> Unit) {
    val badgeColor = when (rutina.tipoEtiqueta) {
        TipoEtiquetaRutina.INTENSO -> BadgeOrangeBg
        TipoEtiquetaRutina.RAPIDO -> BadgeGreenBg
        else -> Color.Transparent
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MdSysColorSurfaceContainerHigh),
        elevation = CardDefaults.cardElevation(0.dp) // Diseño plano más moderno
    ) {
        Column {
            // IMAGEN / BANNER
            Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
                    Icon(Icons.Default.Image, null, tint = Color.White.copy(0.2f), modifier = Modifier.align(Alignment.Center).size(40.dp))
                }
                // Degradado para que el texto se vea bien
                Box(modifier = Modifier.fillMaxSize().background(
                    Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(0.7f)))
                ))
                Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                    if (rutina.tipoEtiqueta != TipoEtiquetaRutina.NINGUNA) {
                        ContainerBadge(text = rutina.etiquetaTexto ?: "", color = badgeColor)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    Text(rutina.nombre, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(rutina.detalles, color = MdSysColorOnSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onStartClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MdSysColorPrimary)
                ) {
                    Icon(Icons.Default.PlayArrow, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Ver Video")
                }
            }
        }
    }
}

@Composable
fun StatCard(icon: ImageVector, label: String, value: String) {
    Column(
        modifier = Modifier
            .width(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MdSysColorSurfaceContainer)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, null, tint = MdSysColorPrimary, modifier = Modifier.size(18.dp))
            Text(label, style = MaterialTheme.typography.labelMedium, color = MdSysColorOnSurfaceVariant)
        }
        Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MdSysColorOnSurface)
    }
}

@Composable
fun ContainerBadge(text: String, color: Color) {
    Surface(
        color = color,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text.uppercase(),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall.copy(color = Color.White, fontWeight = FontWeight.Bold)
        )
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun GymPreview() {
    val fakeNav = rememberNavController()
    RutinasGimnasioContent(
        navController = fakeNav,
        rutinas = emptyList(),
        sesiones = "12",
        tiempoFormateado = "5h 20m",
        racha = "4 días",
        onPlayVideo = {}
    )
}