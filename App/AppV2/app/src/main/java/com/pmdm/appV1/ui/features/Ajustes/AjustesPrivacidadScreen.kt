package com.pmdm.appV1.ui.features.Ajustes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.OpenInNew // Icono de link externo
import androidx.compose.material.icons.filled.Analytics           // Requiere Extended
import androidx.compose.material.icons.filled.DeleteForever       // Requiere Extended
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PhotoCamera         // Requiere Extended
import androidx.compose.material.icons.filled.Policy              // Requiere Extended
import androidx.compose.material.icons.filled.Security            // Para el header
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale // Importación estándar para escalar el switch
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- COLORES ---
private val AppPrimary = Color(0xFFF2F20D)
private val AppBackground = Color(0xFFFFFFFF) // Fondo blanco limpio según diseño
private val AppTextPrimary = Color(0xFF1C1C0D)
private val AppTextSecondary = Color(0xFF6B6B5F)
private val AppIconBg = Color(0xFFF3F4F6)     // Fondo gris claro para los iconos
private val AppRedText = Color(0xFFDC2626)
private val AppRedBg = Color(0xFFFEF2F2)
private val AppRedBorder = Color(0xFFFECACA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesPrivacidadScreen(
    onBackClick: () -> Unit = {}
) {
    // Estados de permisos
    var cameraEnabled by remember { mutableStateOf(true) }
    var locationEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var analyticsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Privacidad",
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- HEADER (Escudo Amarillo) ---
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(AppPrimary.copy(alpha = 0.2f), RoundedCornerShape(100)), // Círculo suave
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    tint = Color(0xFFCA8A04), // Un tono dorado más oscuro para el icono
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Gestiona cómo compartes tus datos y controla los permisos para tus rutinas y finanzas.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = AppTextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- SECCIÓN: PERMISOS ---
            PrivacySectionTitle("PERMISOS DE LA APP")

            PrivacySwitchItem(
                icon = Icons.Default.PhotoCamera,
                title = "Cámara",
                subtitle = "Escanear comidas y progreso físico",
                checked = cameraEnabled,
                onCheckedChange = { cameraEnabled = it }
            )

            PrivacySwitchItem(
                icon = Icons.Default.LocationOn,
                title = "Ubicación",
                subtitle = "Encontrar gimnasios cercanos",
                checked = locationEnabled,
                onCheckedChange = { locationEnabled = it }
            )

            PrivacySwitchItem(
                icon = Icons.Default.Notifications,
                title = "Notificaciones",
                subtitle = "Alertas de gastos y hábitos",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray.copy(alpha = 0.1f))

            // --- SECCIÓN: GESTIÓN DE DATOS ---
            PrivacySectionTitle("GESTIÓN DE DATOS")

            PrivacySwitchItem(
                icon = Icons.Default.Analytics,
                title = "Análisis de uso",
                subtitle = "Ayúdanos a mejorar la app",
                checked = analyticsEnabled,
                onCheckedChange = { analyticsEnabled = it }
            )

            PrivacyActionItem(
                icon = Icons.Default.Download,
                title = "Exportar mis datos",
                subtitle = "Descargar historial completo",
                trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                onClick = { /* Exportar logic */ }
            )

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Gray.copy(alpha = 0.1f))

            // --- SECCIÓN: OTROS ---
            PrivacySectionTitle("OTROS")

            PrivacyActionItem(
                icon = Icons.Default.Policy,
                title = "Política de Privacidad",
                trailingIcon = Icons.AutoMirrored.Filled.OpenInNew, // Icono de link externo
                isLink = true,
                onClick = { /* Abrir web */ }
            )

            PrivacyActionItem(
                icon = Icons.Default.Description,
                title = "Términos de Servicio",
                trailingIcon = Icons.AutoMirrored.Filled.OpenInNew,
                isLink = true,
                onClick = { /* Abrir web */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- BOTÓN ELIMINAR CUENTA ---
            Button(
                onClick = { /* Confirmar eliminación */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppRedBg,
                    contentColor = AppRedText
                ),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, AppRedBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteForever,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Eliminar mi cuenta", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Versión 2.4.0 • Pixel 8 Build",
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

// ------------------------------------------
// COMPONENTES REUTILIZABLES
// ------------------------------------------

@Composable
fun PrivacySectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = AppTextSecondary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 8.dp)
    )
}

@Composable
fun PrivacySwitchItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono cuadrado redondeado (Squircle)
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(AppIconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppTextPrimary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold, // Más negrita que en notificaciones
                    color = AppTextPrimary
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = AppTextSecondary,
                    lineHeight = 16.sp
                )
            )
        }

        // Reutilizamos el Switch personalizado (inline logic here for simplicity)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.scale(0.9f),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = AppPrimary,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color(0xFFE5E7EB),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun PrivacyActionItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    trailingIcon: ImageVector,
    isLink: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp), // Padding vertical un poco menor para acciones
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono con borde si es link externo, o relleno si es acción principal
        val bgModifier = if (isLink) {
            Modifier
                .size(48.dp)
                .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(14.dp))
        } else {
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(AppIconBg)
        }

        Box(
            modifier = bgModifier,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppTextPrimary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = AppTextPrimary
                )
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = AppTextSecondary
                    )
                )
            }
        }

        Icon(
            imageVector = trailingIcon,
            contentDescription = null,
            tint = Color(0xFF9CA3AF) // Gris suave para flechas/links
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacidadPreview() {
    AjustesPrivacidadScreen()
}