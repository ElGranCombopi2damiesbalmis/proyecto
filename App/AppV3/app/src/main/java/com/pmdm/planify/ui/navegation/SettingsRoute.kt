package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.Ajustes.AjustesPerfilScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavGraphBuilder.settingsDestination(
    onBack: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToPrivacy: () -> Unit,
    onLogout: () -> Unit
) {
    composable<SettingsRoute> {
        AjustesPerfilScreen(
            onBackClick = onBack,
            /*onEditProfileClick = onNavigateToEditProfile,
            onNotificationsClick = onNavigateToNotifications,
            onPrivacyClick = onNavigateToPrivacy,*/
            onLogoutClick = onLogout
        )
    }
}

@Serializable
object EditarPerfilRoute // Pantalla de Editar Perfil

@Serializable
object NotificacionesRoute // Pantalla de Notificaciones

@Serializable
object PrivacidadRoute // Pantalla de Privacidad

