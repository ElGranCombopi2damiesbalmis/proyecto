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
import androidx.compose.material.icons.filled.Image // Icono para el placeholder
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- PALETA DE COLORES EXACTA (Extraída del CSS) ---
private val MdSysColorPrimary = Color(0xFF725C00)
private val MdSysColorOnPrimary = Color(0xFFFFFFFF)
private val MdSysColorPrimaryContainer = Color(0xFFFFDF90)
private val MdSysColorOnPrimaryContainer = Color(0xFF231B00)
private val MdSysColorSecondaryContainer = Color(0xFFF5E1BB)
private val MdSysColorOnSecondaryContainer = Color(0xFF241A04)
private val MdSysColorSurface = Color(0xFFFFFBFF)
private val MdSysColorOnSurface = Color(0xFF1E1B16)
private val MdSysColorSurfaceVariant = Color(0xFFEBE2CF)
private val MdSysColorOnSurfaceVariant = Color(0xFF4C4739)
private val MdSysColorOutline = Color(0xFF7D7767)
private val MdSysColorSurfaceContainer = Color(0xFFF7F1E2)      // Fondo Stats
private val MdSysColorSurfaceContainerHigh = Color(0xFFEFEBDC)  // Fondo Cards

// Colores de Badges
private val BadgeOrangeBg = Color(0xFFF97316) // orange-500
private val BadgeGreenBg = Color(0xFF16A34A)  // green-600

@Composable
fun RutinasGimnasioScreen(
    onBackClick: () -> Unit = {}
) {
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
                IconButton(onClick = { /* Menú */ }, modifier = Modifier.size(24.dp)) {
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
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // 1. STATS ROW (Scroll Horizontal)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(
                        icon = Icons.Default.FitnessCenter,
                        label = "Sesiones",
                        value = "4"
                    )
                    StatCard(
                        icon = Icons.Default.Schedule,
                        label = "Tiempo",
                        value = "3h 20m"
                    )
                    StatCard(
                        icon = Icons.Default.LocalFireDepartment,
                        label = "Racha",
                        value = "12 días"
                    )
                }
            }

            // 2. BUTTON: INICIAR NUEVA RUTINA
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Button(
                        onClick = { /* Acción */ },
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

            // 3. SECCIÓN: MIS RUTINAS
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

            // 4. LISTA DE TARJETAS
            items(getGymRoutines()) { routine ->
                GymRoutineCard(routine)
            }
        }
    }
}

// ---------------------------------------------------------
// COMPONENTES UI REUTILIZABLES
// ---------------------------------------------------------

@Composable
fun StatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MdSysColorPrimary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = MdSysColorOnSurfaceVariant
                )
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                color = MdSysColorOnSurface
            )
        )
    }
}

@Composable
fun GymRoutineCard(routine: GymRoutineData) {
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
            // IMAGEN DE FONDO (Placeholder Nativo)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp)
            ) {
                // 1. Fondo de color simulando imagen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray) // Color base oscuro
                ) {
                    // Icono central para indicar que aquí iría la foto
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                    )
                }

                // 2. Gradiente para legibilidad del texto
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

                // 3. Contenido sobre la imagen
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    if (routine.badgeText != null) {
                        ContainerBadge(text = routine.badgeText, color = routine.badgeColor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text(
                        text = routine.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            // CONTENIDO DEL BODY
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = routine.details,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MdSysColorOnSurfaceVariant
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Botón Iniciar
                    Button(
                        onClick = { /* Start */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MdSysColorPrimary,
                            contentColor = MdSysColorOnPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Iniciar")
                    }

                    // Botón Editar
                    OutlinedButton(
                        onClick = { /* Edit */ },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        border = androidx.compose.foundation.BorderStroke(1.dp, MdSysColorOutline.copy(alpha = 0.3f)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MdSysColorPrimary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
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
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
        )
    }
}

@Composable
fun GymBottomNavBar() {
    Surface(
        color = MdSysColorSurfaceContainer.copy(alpha = 0.95f),
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(MdSysColorOutline.copy(alpha = 0.05f)))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavBarItem(icon = Icons.Default.CheckCircle, label = "Tareas", isSelected = false)

                // Item Seleccionado (Gimnasio)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .width(64.dp)
                            .height(32.dp)
                            .clip(RoundedCornerShape(100))
                            .background(MdSysColorSecondaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = MdSysColorOnSecondaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Gimnasio",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MdSysColorOnSurface,
                            fontSize = 12.sp
                        )
                    )
                }

                NavBarItem(icon = Icons.Default.AttachMoney, label = "Gastos", isSelected = false)
                NavBarItem(icon = Icons.Default.Mood, label = "Ánimo", isSelected = false)
            }
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
fun NavBarItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(64.dp).clickable(onClick = {})
    ) {
        Box(
            modifier = Modifier.height(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MdSysColorOnSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = MdSysColorOnSurfaceVariant,
                fontSize = 12.sp
            )
        )
    }
}

// --- DATA MODEL ---

data class GymRoutineData(
    val title: String,
    val details: String,
    // val imageUrl: String, // Comentado para no causar confusión, usamos placeholder
    val badgeText: String? = null,
    val badgeColor: Color = Color.Transparent
)

fun getGymRoutines(): List<GymRoutineData> {
    return listOf(
        GymRoutineData(
            title = "Día de Pierna",
            details = "45 min • 6 Ejercicios • Enfocado en fuerza",
            badgeText = "Intenso",
            badgeColor = BadgeOrangeBg
        ),
        GymRoutineData(
            title = "Torso y Brazos",
            details = "50 min • 8 Ejercicios • Hipertrofia",
            badgeText = null
        ),
        GymRoutineData(
            title = "Cardio HIIT",
            details = "25 min • 4 Ejercicios • Resistencia",
            badgeText = "Rápido",
            badgeColor = BadgeGreenBg
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RutinasGimnasioPreview() {
    RutinasGimnasioScreen()
}