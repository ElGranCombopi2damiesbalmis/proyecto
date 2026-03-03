package com.pmdm.planify.ui.features.RutinasDeGimnasio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
<<<<<<< HEAD
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
=======
>>>>>>> 49162f325ab2727e924248109f5c24ce4c1e40ef
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalFireDepartment
<<<<<<< HEAD
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Payments
=======
>>>>>>> 49162f325ab2727e924248109f5c24ce4c1e40ef
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.planify.models.Rutina
import com.pmdm.planify.models.TipoEtiquetaRutina
import com.pmdm.planify.ui.features.VentanaPrincipal.DashboardBottomBar
import com.pmdm.planify.ui.features.VentanaPrincipal.HeaderSection

// --- Colores del Tema ---
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

// 1. COMPONENTE CON ESTADO (El que usas en el NavHost)
@Composable
fun RutinasGimnasioScreen(vm: GymVM) {
    val uriHandler = LocalUriHandler.current

    RutinasGimnasioContent(
        rutinas = vm.rutinas,
        sesiones = vm.sesiones.toString(),
        tiempoFormateado = vm.formatearTiempo(),
        racha = "${vm.racha} días",
        onPlayVideo = { rutina ->
            // 1. Avisamos al ViewModel para que sume estadísticas
            vm.onEvent(GymEvent.OnPlayVideo(rutina))

            // 2. Abrimos YouTube
            if (rutina.videoUrl.isNotEmpty()) {
                uriHandler.openUri(rutina.videoUrl)
            }
        }
    )
}

// 2. COMPONENTE SIN ESTADO (UI Pura - Ideal para Previews)
@Composable
fun RutinasGimnasioContent(
    rutinas: List<Rutina>,
    sesiones: String,
    tiempoFormateado: String,
    racha: String,
    onPlayVideo: (Rutina) -> Unit
) {
    Scaffold(
        containerColor = MdSysColorSurface,
        bottomBar = { DashboardBottomBar(itemSeleccionado = "Gym") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // HEADER
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    HeaderSection()
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

            // TÍTULO MIS RUTINAS
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mis Rutinas",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium, fontSize = 18.sp, color = MdSysColorOnSurface)
                    )
                    TextButton(onClick = { }) {
                        Text("Ver todo", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium, color = MdSysColorPrimary))
                    }
                }
            }

            // LISTA DE TARJETAS
            items(rutinas) { rutina ->
                GymRoutineCard(
                    rutina = rutina,
                    onStartClick = { onPlayVideo(rutina) } // Disparamos la acción
                )
            }
        }
    }
}

// --- COMPONENTES UI HIJOS ---

@Composable
fun GymRoutineCard(rutina: Rutina, onStartClick: () -> Unit) {
    val badgeColor = when (rutina.tipoEtiqueta) {
        TipoEtiquetaRutina.INTENSO -> BadgeOrangeBg
        TipoEtiquetaRutina.RAPIDO -> BadgeGreenBg
        else -> Color.Transparent
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MdSysColorSurfaceContainerHigh),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(176.dp)) {
                Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
                    Icon(Icons.Default.Image, null, tint = Color.White.copy(alpha = 0.2f), modifier = Modifier.align(Alignment.Center).size(48.dp))
                }
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)), startY = 100f)))
                Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                    if (rutina.tipoEtiqueta != TipoEtiquetaRutina.NINGUNA && rutina.etiquetaTexto != null) {
                        ContainerBadge(text = rutina.etiquetaTexto!!, color = badgeColor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text(rutina.nombre, style = MaterialTheme.typography.headlineSmall.copy(color = Color.White, fontWeight = FontWeight.Normal))
                }
            }

            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(rutina.detalles, style = MaterialTheme.typography.bodyMedium.copy(color = MdSysColorOnSurfaceVariant))

                Button(
                    onClick = onStartClick,
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MdSysColorPrimary, contentColor = MdSysColorOnPrimary),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Ver Video")
                }
            }
        }
    }
}

@Composable
fun StatCard(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Column(
        modifier = Modifier.width(140.dp).clip(RoundedCornerShape(16.dp)).background(MdSysColorSurfaceContainer).border(1.dp, MdSysColorOutline.copy(alpha = 0.05f), RoundedCornerShape(16.dp)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, null, tint = MdSysColorPrimary, modifier = Modifier.size(20.dp))
            Text(label, style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium, color = MdSysColorOnSurfaceVariant))
        }
        Text(value, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Normal, fontSize = 28.sp, color = MdSysColorOnSurface))
    }
}

@Composable
fun ContainerBadge(text: String, color: Color) {
    Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(color.copy(alpha = 0.9f)).padding(horizontal = 8.dp, vertical = 2.dp)) {
        Text(text.uppercase(), style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White, letterSpacing = 1.sp))
    }
}

<<<<<<< HEAD
@Composable
fun GymBottomNavBar() {
    NavigationBar(
        // Colores exactos de Ventana Principal
        containerColor = Color(0xFFF4F4F5), // SurfaceContainer
        contentColor = Color(0xFF64748B),    // TextSecondary
        tonalElevation = 0.dp
    ) {
        // Los 5 ítems estándar de Planify
        val items = listOf(
            Triple("Tareas", Icons.Filled.CalendarMonth, false),
            Triple("Gym", Icons.Filled.FitnessCenter, true),
            Triple("Inicio", Icons.Filled.Home, false),
            Triple("Gastos", Icons.Filled.Payments, false),
            Triple("Ánimo", Icons.Filled.SentimentSatisfied, false)
        )

        items.forEach { (label, icon, isSelected) ->
            NavigationBarItem(
                selected = isSelected,
                onClick = { /* Navegación */ },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1C1C0D), // OnPrimary (Negro)
                    selectedTextColor = Color(0xFF1C1C0D), // TextPrimary
                    indicatorColor = Color(0xFFF2F5A9),    // PrimaryContainer (Amarillo)
                    unselectedIconColor = Color(0xFF64748B),
                    unselectedTextColor = Color(0xFF64748B)
                )
            )
        }
    }
}

@Composable
fun NavBarItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(64.dp).clickable(onClick = {})) {
        Box(modifier = Modifier.height(32.dp), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = MdSysColorOnSurfaceVariant)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium, color = MdSysColorOnSurfaceVariant, fontSize = 12.sp))
    }
}

// ---------------------------------------------------------
// PREVIEW
// ---------------------------------------------------------
@Preview(showBackground = true, heightDp = 1000)
=======
// PREVIEW CON MÁS DATOS FALSOS
@Preview(showBackground = true, heightDp = 1500)
>>>>>>> 49162f325ab2727e924248109f5c24ce4c1e40ef
@Composable
fun RutinasGimnasioScreenPreview() {
    val rutinasFalsas = listOf(
        Rutina(
            nombre = "Día de Pierna",
            detalles = "45 min • 6 Ejercicios • Enfocado en fuerza",
            tipoEtiqueta = TipoEtiquetaRutina.INTENSO,
            etiquetaTexto = "Intenso"
        ),
        Rutina(
            nombre = "Día de Pecho",
            detalles = "55 min • 7 Ejercicios • Enfoque en hipertrofia",
            tipoEtiqueta = TipoEtiquetaRutina.INTENSO,
            etiquetaTexto = "Intenso"
        ),
        Rutina(
            nombre = "Espalda y Bíceps",
            detalles = "60 min • 8 Ejercicios • Amplitud y grosor",
            tipoEtiqueta = TipoEtiquetaRutina.NINGUNA,
            etiquetaTexto = null
        ),
        Rutina(
            nombre = "Cardio HIIT",
            detalles = "25 min • 4 Ejercicios • Resistencia",
            tipoEtiqueta = TipoEtiquetaRutina.RAPIDO,
            etiquetaTexto = "Rápido"
        )
    )

    RutinasGimnasioContent(
        rutinas = rutinasFalsas,
        sesiones = "6", // Datos falsos actualizados para la preview
        tiempoFormateado = "4h 45m",
        racha = "15 días",
        onPlayVideo = {}
    )
}