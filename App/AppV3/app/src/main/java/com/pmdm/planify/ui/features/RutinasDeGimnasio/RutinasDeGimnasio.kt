package com.pmdm.planify.ui.features.RutinasDeGimnasio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
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

// Importaciones de tu capa de datos (Asegúrate de que los paquetes coincidan)
import com.pmdm.planify.data.RutinaRepository
import com.pmdm.planify.models.Rutina
import com.pmdm.planify.models.TipoEtiquetaRutina

// --- Colores del Tema (Extraídos del Design System) ---
private val MdSysColorPrimary = Color(0xFF725C00)
private val MdSysColorOnPrimary = Color(0xFFFFFFFF)
private val MdSysColorPrimaryContainer = Color(0xFFFFDF90)
private val MdSysColorOnPrimaryContainer = Color(0xFF231B00)
private val MdSysColorSecondaryContainer = Color(0xFFF5E1BB)
private val MdSysColorOnSecondaryContainer = Color(0xFF241A04)
private val MdSysColorSurface = Color(0xFFFFFBFF)
private val MdSysColorOnSurface = Color(0xFF1E1B16)
private val MdSysColorOnSurfaceVariant = Color(0xFF4C4739)
private val MdSysColorOutline = Color(0xFF7D7767)
private val MdSysColorSurfaceContainer = Color(0xFFF7F1E2)
private val MdSysColorSurfaceContainerHigh = Color(0xFFEFEBDC)
private val BadgeOrangeBg = Color(0xFFF97316)
private val BadgeGreenBg = Color(0xFF16A34A)

@Composable
fun RutinasGimnasioScreen(
    onBackClick: () -> Unit = {}
) {
    // 1. Instanciamos el Repositorio (Capa de Datos)
    val repository = remember { RutinaRepository() }

    // 2. Obtenemos las rutinas (Conexión Modelo -> Vista)
    // Al ser síncrono, lo obtenemos directamente.
    val rutinas = remember { repository.getRutinas() }

    // 3. Handler para abrir enlaces (YouTube)
    val uriHandler = LocalUriHandler.current

    Scaffold(
        containerColor = MdSysColorSurface,
        topBar = {
            // Header estilo TopAppBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MdSysColorSurface.copy(alpha = 0.9f))
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MdSysColorOnSurface
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Rutinas de Gimnasio",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            color = MdSysColorOnSurface
                        )
                    )
                }
                IconButton(onClick = { /* Acción Menú */ }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Más",
                        tint = MdSysColorOnSurfaceVariant
                    )
                }
            }
        },
        bottomBar = {
            GymBottomNavBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 100.dp), // Espacio para el BottomNav
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // --- SECCIÓN 1: ESTADÍSTICAS ---
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(Icons.Default.FitnessCenter, "Sesiones", "4")
                    StatCard(Icons.Default.Schedule, "Tiempo", "3h 20m")
                    StatCard(Icons.Default.LocalFireDepartment, "Racha", "12 días")
                }
            }

            // --- SECCIÓN 2: BOTÓN NUEVA RUTINA ---
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Button(
                        onClick = { /* Acción futura */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MdSysColorPrimaryContainer,
                            contentColor = MdSysColorOnPrimaryContainer
                        ),
                        shape = RoundedCornerShape(100),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Iniciar Nueva Rutina",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp
                            )
                        )
                    }
                }
            }

            // --- SECCIÓN 3: TÍTULO MIS RUTINAS ---
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mis Rutinas",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = MdSysColorOnSurface
                        )
                    )
                    TextButton(onClick = { /* Ver todo */ }) {
                        Text(
                            text = "Ver todo",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = MdSysColorPrimary
                            )
                        )
                    }
                }
            }

            // --- SECCIÓN 4: LISTA DE TARJETAS (Datos del Repo) ---
            items(rutinas) { rutina ->
                GymRoutineCard(
                    rutina = rutina,
                    onStartClick = {
                        // Acción: Abrir el video de YouTube vinculado en el Mock
                        if (rutina.videoUrl.isNotEmpty()) {
                            uriHandler.openUri(rutina.videoUrl)
                        }
                    }
                )
            }
        }
    }
}

// ---------------------------------------------------------
// COMPONENTES UI
// ---------------------------------------------------------

@Composable
fun GymRoutineCard(
    rutina: Rutina,
    onStartClick: () -> Unit
) {
    // Mapeo de colores según el tipo de etiqueta del modelo
    val badgeColor = when (rutina.tipoEtiqueta) {
        TipoEtiquetaRutina.INTENSO -> BadgeOrangeBg
        TipoEtiquetaRutina.RAPIDO -> BadgeGreenBg
        else -> Color.Transparent
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MdSysColorSurfaceContainerHigh),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // -- IMAGEN DE FONDO --
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp)
            ) {
                // Placeholder (En un caso real usarías AsyncImage(model = rutina.imagenUrl))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                    )
                }

                // Gradiente para mejorar lectura del texto
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                startY = 100f
                            )
                        )
                )

                // Texto sobre la imagen (Nombre y Badge)
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    if (rutina.tipoEtiqueta != TipoEtiquetaRutina.NINGUNA && rutina.etiquetaTexto != null) {
                        ContainerBadge(text = rutina.etiquetaTexto!!, color = badgeColor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text(
                        text = rutina.nombre,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            // -- CONTENIDO INFERIOR --
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = rutina.detalles,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MdSysColorOnSurfaceVariant
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Botón Iniciar / Ver Video
                    Button(
                        onClick = onStartClick,
                        modifier = Modifier.weight(1f).height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MdSysColorPrimary,
                            contentColor = MdSysColorOnPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Ver Video")
                    }

                    // Botón Editar
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        border = androidx.compose.foundation.BorderStroke(1.dp, MdSysColorOutline.copy(alpha = 0.3f)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MdSysColorPrimary)
                    ) {
                        Icon(Icons.Default.Edit, "Editar", modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MdSysColorSurfaceContainer)
            .border(1.dp, MdSysColorOutline.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(16.dp),
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
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.9f))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(text.uppercase(), style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White, letterSpacing = 1.sp))
    }
}

@Composable
fun GymBottomNavBar() {
    Surface(color = MdSysColorSurfaceContainer.copy(alpha = 0.95f), shadowElevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(MdSysColorOutline.copy(alpha = 0.05f)))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp).height(56.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavBarItem(Icons.Default.CheckCircle, "Tareas", false)
                // Item Activo
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.width(64.dp).height(32.dp).clip(RoundedCornerShape(100)).background(MdSysColorSecondaryContainer), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.FitnessCenter, null, tint = MdSysColorOnSecondaryContainer)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Gimnasio", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MdSysColorOnSurface, fontSize = 12.sp))
                }
                NavBarItem(Icons.Default.AttachMoney, "Gastos", false)
                NavBarItem(Icons.Default.Mood, "Ánimo", false)
            }
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
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
@Composable
fun RutinasGimnasioScreenPreview() {
    // Al previsualizar, se usarán los datos por defecto del DAO Mock
    RutinasGimnasioScreen()
}