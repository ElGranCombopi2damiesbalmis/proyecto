package com.pmdm.appV1.ui.features.Ajustes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// --- DEFINICIÓN DE COLORES (Design System extraído) ---
private val AppPrimary = Color(0xFFF2F20D)
private val AppBackground = Color(0xFFFCFCF8)
private val AppSurface = Color(0xFFF4F4F0)
private val AppTextPrimary = Color(0xFF1C1C0D)
private val AppTextSecondary = Color(0xFF737373) // Neutral-500 approx
private val AppRedText = Color(0xFFDC2626) // Red-600
private val AppRedBg = Color(0xFFFEF2F2) // Red-50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesPerfilScreen(
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    // Scaffold provee la estructura básica de Material 3
    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Ajustes",
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
        // Contenido scrolleable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp), // Padding lateral general
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- SECCIÓN PERFIL ---
            Spacer(modifier = Modifier.height(16.dp))
            ProfileHeaderSection()

            Spacer(modifier = Modifier.height(32.dp))

            // --- GRUPO: CUENTA ---
            SettingsGroupTitle("Cuenta")
            SettingsGroupContainer {
                SettingsItem(
                    icon = Icons.Default.Person,
                    title = "Editar Perfil",
                    iconTint = AppTextPrimary,
                    iconBgColor = AppPrimary.copy(alpha = 0.2f),
                    onClick = { /* TODO */ }
                )
                SettingsItem(
                    icon = Icons.Default.Notifications,
                    title = "Notificaciones",
                    iconTint = AppTextPrimary,
                    iconBgColor = AppPrimary.copy(alpha = 0.2f),
                    onClick = { /* TODO */ }
                )
                SettingsItem(
                    icon = Icons.Default.Lock,
                    title = "Privacidad",
                    iconTint = AppTextPrimary,
                    iconBgColor = AppPrimary.copy(alpha = 0.2f),
                    showDivider = false, // El último no lleva separador visual si quisieras añadirlo
                    onClick = { /* TODO */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- GRUPO: PREFERENCIAS ---
            SettingsGroupTitle("Preferencias")
            SettingsGroupContainer {
                SettingsItem(
                    icon = Icons.Default.Language,
                    title = "Idioma",
                    valueText = "Español",
                    iconTint = Color.Gray,
                    iconBgColor = Color(0xFFE5E5E5), // Neutral-200
                    onClick = { /* TODO */ }
                )
                SettingsItem(
                    icon = Icons.AutoMirrored.Filled.Help,
                    title = "Ayuda y Soporte",
                    iconTint = Color.Gray,
                    iconBgColor = Color(0xFFE5E5E5),
                    showDivider = false,
                    onClick = { /* TODO */ }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- BOTÓN CERRAR SESIÓN ---
            LogoutButton(onClick = onLogoutClick)

            Spacer(modifier = Modifier.height(24.dp))

            // --- VERSIÓN ---
            Text(
                text = "Versión 1.0.4 (Build 202)",
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

// ------------------------------------------
// COMPONENTES REUTILIZABLES (UI KIT)
// ------------------------------------------

@Composable
fun ProfileHeaderSection() {
    Box(contentAlignment = Alignment.Center) {
        // Efecto de brillo detrás (Gradient Glow)
        Box(
            modifier = Modifier
                .size(110.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(AppPrimary, AppPrimary.copy(alpha = 0.0f))
                    ),
                    shape = CircleShape
                )
                .blur(radius = 20.dp) // Nota: blur requiere Android 12+ o librerías externas.
            // Si no compila, eliminar .blur y bajar alpha.
            // Compose nativo: RenderEffect.
        )

        // Imagen (Placeholder o Coil/Glide)
        // NOTA: Reemplazar 'painterResource' por tu imagen real o AsyncImage
        Box(
            modifier = Modifier
                .size(112.dp)
                .border(4.dp, AppBackground, CircleShape)
                .shadow(4.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.LightGray) // Placeholder
        ) {
            // Aquí iría tu AsyncImage(model = "url", ...)
            // Simulamos la imagen de la chica con un Icono grande por ahora
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(16.dp),
                tint = Color.White
            )
        }

        // Botón Editar (Badge)
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-4).dp, y = (-4).dp)
                .size(36.dp)
                .background(AppPrimary, CircleShape)
                .border(3.dp, AppBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar foto",
                modifier = Modifier.size(16.dp),
                tint = AppTextPrimary
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "María González",
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold,
            color = AppTextPrimary
        )
    )
    Text(
        text = "maria.gonzalez@example.com",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = AppTextSecondary,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
fun SettingsGroupTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = AppTextPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, start = 8.dp)
    )
}

@Composable
fun SettingsGroupContainer(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(AppSurface)
            .padding(8.dp), // Padding interno del grupo
        content = content
    )
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    valueText: String? = null,
    iconTint: Color,
    iconBgColor: Color,
    showDivider: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono con burbuja de color
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconBgColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Texto Título
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = AppTextPrimary
            ),
            modifier = Modifier.weight(1f)
        )

        // Valor opcional (ej: "Español")
        if (valueText != null) {
            Text(
                text = valueText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = AppTextSecondary
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        // Chevron
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.LightGray
        )
    }
}

@Composable
fun LogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppRedBg,
            contentColor = AppRedText
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, AppRedText.copy(alpha = 0.1f)),
        elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp, // Icono logout
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cerrar Sesión",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

// Extensión para simplificar modificadores si es necesario
fun Modifier.blur(radius: Dp): Modifier {
    // Implementación vacía para compatibilidad si no usas Android 12+
    // En producción usarías RenderEffect.createBlurEffect en Modifier.graphicsLayer
    return this
}

@Preview(showBackground = true)
@Composable
fun AjustesScreenPreview() {
    AjustesPerfilScreen()
}