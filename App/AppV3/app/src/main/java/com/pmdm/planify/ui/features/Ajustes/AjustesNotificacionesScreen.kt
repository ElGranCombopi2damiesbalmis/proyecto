package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.AdUnits            // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.Bedtime            // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter      // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.Mood               // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SettingsSuggest    // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.Vibration          // Nota: Requiere librería Extended
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale // <--- IMPORTACIÓN CLAVE AGREGADA
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- COLORES (Design System) ---
private val AppPrimary = Color(0xFFF2F20D)
private val AppBackground = Color(0xFFFCFCF8)
private val AppSurfaceVariant = Color(0xFFF4F4E7)
private val AppTextPrimary = Color(0xFF1C1C0D)
private val AppTextSecondary = Color(0xFF48483B)
private val AppSwitchUncheckedTrack = Color(0xFFE2E2D9)
private val AppSwitchUncheckedBorder = Color(0xFF747775)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesNotificacionesScreen(
    onBackClick: () -> Unit = {}
) {
    // Estados de los switches (Simulados)
    var allNotifications by remember { mutableStateOf(true) }
    var tasksEnabled by remember { mutableStateOf(true) }
    var gymEnabled by remember { mutableStateOf(true) }
    var expensesEnabled by remember { mutableStateOf(false) }
    var moodEnabled by remember { mutableStateOf(true) }
    var sleepModeEnabled by remember { mutableStateOf(false) }

    // Estados de las preferencias (Chips)
    var soundSelected by remember { mutableStateOf(true) }
    var vibrationSelected by remember { mutableStateOf(true) }
    var bannerSelected by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Notificaciones",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = AppTextPrimary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = AppTextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppBackground
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Acción de prueba */ },
                containerColor = AppPrimary,
                contentColor = AppTextPrimary,
                elevation = FloatingActionButtonDefaults.elevation(4.dp)
            ) {
                Icon(imageVector = Icons.Default.SettingsSuggest, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Prueba", fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {

            // --- TARJETA PRINCIPAL (Permitir todas) ---
            Card(
                colors = CardDefaults.cardColors(containerColor = AppSurfaceVariant),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.White, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = AppTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Permitir todas",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = "Gestionar alertas globales",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = AppTextSecondary
                            )
                        )
                    }

                    CustomSwitch(checked = allNotifications, onCheckedChange = { allNotifications = it })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(24.dp))

            // --- CATEGORÍAS ---
            SectionTitle("CATEGORÍAS")

            NotificationItem(
                icon = Icons.Default.CheckCircle,
                title = "Recordatorios de Tareas",
                subtitle = "Fechas límite y pendientes",
                checked = tasksEnabled,
                onCheckedChange = { tasksEnabled = it }
            )

            NotificationItem(
                icon = Icons.Default.FitnessCenter,
                title = "Rutinas de Gimnasio",
                subtitle = "Entrenamientos y progreso",
                checked = gymEnabled,
                onCheckedChange = { gymEnabled = it }
            )

            NotificationItem(
                icon = Icons.Default.AccountBalanceWallet,
                title = "Alertas de Gastos",
                subtitle = "Límites y pagos próximos",
                checked = expensesEnabled,
                onCheckedChange = { expensesEnabled = it }
            )

            NotificationItem(
                icon = Icons.Default.Mood,
                title = "Check-in Diario",
                subtitle = "Registro de estado de ánimo",
                checked = moodEnabled,
                onCheckedChange = { moodEnabled = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(24.dp))

            // --- PREFERENCIAS DE ENTREGA (CHIPS) ---
            SectionTitle("PREFERENCIAS DE ENTREGA")

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                FilterChipCustom(
                    selected = soundSelected,
                    label = "Sonido",
                    icon = Icons.Default.VolumeUp,
                    onClick = { soundSelected = !soundSelected }
                )
                FilterChipCustom(
                    selected = vibrationSelected,
                    label = "Vibración",
                    icon = Icons.Default.Vibration,
                    onClick = { vibrationSelected = !vibrationSelected }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                FilterChipCustom(
                    selected = bannerSelected,
                    label = "Banner",
                    icon = Icons.Default.AdUnits,
                    onClick = { bannerSelected = !bannerSelected }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- MODO DESCANSO (Tarjeta inferior) ---
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6)), // Grisaceo muy suave
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp) // Espacio para el FAB
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Bedtime,
                                contentDescription = null,
                                tint = Color(0xFF6366F1), // Indigo icon
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Modo Descanso",
                                fontWeight = FontWeight.Bold,
                                color = AppTextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Silenciar notificaciones automáticamente entre las 22:00 y las 07:00 para mejorar tu sueño.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = AppTextSecondary,
                                lineHeight = 18.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomSwitch(checked = sleepModeEnabled, onCheckedChange = { sleepModeEnabled = it }, scale = 0.8f)
                }
            }
        }
    }
}

// ------------------------------------------
// COMPONENTES REUTILIZABLES
// ------------------------------------------

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = AppTextSecondary
        ),
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun NotificationItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppTextSecondary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = AppTextPrimary
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = AppTextSecondary
                )
            )
        }

        CustomSwitch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    scale: Float = 1f
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier.scale(scale), // Esto ahora usa androidx.compose.ui.draw.scale
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Black,
            checkedTrackColor = AppPrimary,
            uncheckedThumbColor = Color.Gray,
            uncheckedTrackColor = AppSwitchUncheckedTrack,
            uncheckedBorderColor = AppSwitchUncheckedBorder
        )
    )
}

@Composable
fun FilterChipCustom(
    selected: Boolean,
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    val bgColor = if (selected) AppPrimary.copy(alpha = 0.2f) else Color.Transparent
    val borderColor = if (selected) AppPrimary else Color.LightGray
    val contentColor = AppTextPrimary

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if(selected) AppTextPrimary else Color.Gray,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificacionesPreview() {
    AjustesNotificacionesScreen()
}