package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.features.Ajustes.AjustesPerfilScreen
import kotlinx.serialization.Serializable

@Serializable
<<<<<<< HEAD
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
=======
object SettingsRoute // Pantalla principal de Ajustes (Perfil)

@Serializable
object EditarPerfilRoute // Pantalla de Editar Perfil

@Serializable
object NotificacionesRoute // Pantalla de Notificaciones

@Serializable
object PrivacidadRoute // Pantalla de Privacidad
>>>>>>> 49162f325ab2727e924248109f5c24ce4c1e40ef
