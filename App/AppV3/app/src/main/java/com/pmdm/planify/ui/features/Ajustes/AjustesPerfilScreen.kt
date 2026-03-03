package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.pmdm.planify.models.Usuario
import com.pmdm.planify.ui.features.VentanaPrincipal.DashboardBottomBar

// --- DEFINICIÓN DE COLORES ---
val AppPrimary = Color(0xFFF2F20D)
val AppBackground = Color(0xFFFCFCF8)
val AppSurface = Color(0xFFF4F4F0)
val AppTextPrimary = Color(0xFF1C1C0D)
val AppTextSecondary = Color(0xFF737373)
val AppRedText = Color(0xFFDC2626)
val AppRedBg = Color(0xFFFEF2F2)


// 1. COMPONENTE CON ESTADO (El que usas en NavHostPlanify)
@Composable
fun AjustesPerfilScreen(vm: AjustesVM) {
    // Extraemos el estado y se lo pasamos al componente visual
    AjustesPerfilContent(
        usuario = vm.usuarioState,
        onEvent = { event -> vm.onEvent(event) }
    )
}


// 2. COMPONENTE SIN ESTADO (El que dibuja la UI y usamos en el Preview)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesPerfilContent(
    usuario: Usuario?,
    onEvent: (AjustesEvent) -> Unit
) {
    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ajustes", fontWeight = FontWeight.Bold, color = AppTextPrimary) },
                navigationIcon = {
                    IconButton(onClick = { onEvent(AjustesEvent.OnBack) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = AppTextPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AppBackground)
            )
        },
        bottomBar = { DashboardBottomBar(itemSeleccionado = "Ajustes") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- HEADER DINÁMICO ---
            Box(contentAlignment = Alignment.Center) {
                Box(modifier = Modifier.size(112.dp).border(4.dp, AppBackground, CircleShape).clip(CircleShape).background(Color.LightGray)) {
                    Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.fillMaxSize().padding(16.dp), tint = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = usuario?.nombre ?: "Cargando...",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, color = AppTextPrimary)
            )
            Text(
                text = usuario?.email ?: "...",
                style = MaterialTheme.typography.bodyMedium.copy(color = AppTextSecondary, fontWeight = FontWeight.Medium)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- GRUPO: CUENTA ---
            Text("Cuenta", fontWeight = FontWeight.Bold, color = AppTextPrimary, modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp, start = 8.dp))
            Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(AppSurface).padding(8.dp)) {
                SettingsItem(Icons.Default.Person, "Editar Perfil", iconTint = AppTextPrimary, iconBgColor = AppPrimary.copy(alpha = 0.2f)) {
                    onEvent(AjustesEvent.OnNavigateToEditarPerfil)
                }
                SettingsItem(Icons.Default.Notifications, "Notificaciones", iconTint = AppTextPrimary, iconBgColor = AppPrimary.copy(alpha = 0.2f)) {
                    onEvent(AjustesEvent.OnNavigateToNotificaciones)
                }
                SettingsItem(Icons.Default.Lock, "Privacidad", iconTint = AppTextPrimary, iconBgColor = AppPrimary.copy(alpha = 0.2f)) {
                    onEvent(AjustesEvent.OnNavigateToPrivacidad)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- BOTÓN CERRAR SESIÓN ---
            Button(
                onClick = { onEvent(AjustesEvent.OnLogout) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppRedBg, contentColor = AppRedText),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar Sesión", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// --- SUBCOMPONENTES ---

@Composable
fun SettingsItem(icon: ImageVector, title: String, iconTint: Color, iconBgColor: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).clickable(onClick = onClick).padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(40.dp).background(iconBgColor, CircleShape), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = iconTint, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium, color = AppTextPrimary), modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
    }
}


// 3. LA PREVIEW MAGICA
@Preview(showBackground = true)
@Composable
fun AjustesPerfilPreview() {
    // 1. Creamos un usuario de mentira solo para que el Preview tenga datos
    val dummyUser = Usuario(
        nombre = "María González",
        email = "maria.gonzalez@example.com",
        telefono = "600 123 456",
        calle = "Calle Falsa 123"
    )

    MaterialTheme {
        // 2. Llamamos al componente SIN ESTADO, pasándole el usuario falso y eventos vacíos
        AjustesPerfilContent(
            usuario = dummyUser,
            onEvent = {} // No hace nada en el preview
        )
    }
}