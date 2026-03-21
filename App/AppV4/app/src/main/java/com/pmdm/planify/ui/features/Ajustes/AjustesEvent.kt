package com.pmdm.planify.ui.features.Ajustes

sealed class AjustesEvent {
    // Navegación
    object OnBack : AjustesEvent()
    object OnLogout : AjustesEvent()
    object OnNavigateToEditarPerfil : AjustesEvent()
    object OnNavigateToNotificaciones : AjustesEvent()
    object OnNavigateToPrivacidad : AjustesEvent()

    // Editar Perfil
    data class OnNombreChange(val nombre: String) : AjustesEvent()
    data class OnTelefonoChange(val telefono: String) : AjustesEvent()
    data class OnCalleChange(val calle: String) : AjustesEvent()
    object OnGuardarPerfil : AjustesEvent()

    // Preferencias (Simuladas en el VM ya que el modelo actual no las tiene)
    data class OnNotificacionChange(val clave: String, val activo: Boolean) : AjustesEvent()
    data class OnPrivacidadChange(val clave: String, val activo: Boolean) : AjustesEvent()
}