package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.data.UserSessionRepository
import com.pmdm.planify.models.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AjustesVM @Inject constructor(
    private val usuarioRepo: UsuarioRepository,
    private val sessionRepo: UserSessionRepository
) : ViewModel() {

    var usuarioState by mutableStateOf<Usuario?>(null)
        private set

    var nombreEdit   by mutableStateOf("")
    var telefonoEdit by mutableStateOf("")
    var calleEdit    by mutableStateOf("")

    var notificacionesState = mutableStateMapOf(
        "todas"  to true,
        "tareas" to true,
        "gastos" to false,
        "gym"    to true
    )

    var privacidadState = mutableStateMapOf(
        "perfil_publico"  to false,
        "mostrar_animo"   to true,
        "compartir_datos" to false
    )

    // Lambdas de navegación — se configuran en el NavHost
    var onNavigateToLogin: () -> Unit = {}
    var onNavigateToEditarPerfil: () -> Unit = {}
    var onNavigateToNotificaciones: () -> Unit = {}
    var onNavigateToPrivacidad: () -> Unit = {}
    var onBack: () -> Unit = {}
    var onPerfilActualizado: () -> Unit = {}

    init { cargarUsuario() }

    private fun cargarUsuario() {
        viewModelScope.launch {
            // getAll() devuelve la lista; cogemos el primero disponible
            val user = usuarioRepo.getCurrent(sessionRepo) ?: usuarioRepo.getAll().firstOrNull()
            usuarioState = user
            nombreEdit   = user?.nombre   ?: ""
            telefonoEdit = user?.telefono ?: ""
            calleEdit    = user?.calle    ?: ""
        }
    }

    fun onEvent(event: AjustesEvent) {
        when (event) {

            is AjustesEvent.OnLogout -> {
                sessionRepo.clearSession()
                onNavigateToLogin()
            }

            is AjustesEvent.OnBack                    -> onBack()
            is AjustesEvent.OnNavigateToEditarPerfil  -> onNavigateToEditarPerfil()
            is AjustesEvent.OnNavigateToNotificaciones-> onNavigateToNotificaciones()
            is AjustesEvent.OnNavigateToPrivacidad    -> onNavigateToPrivacidad()

            is AjustesEvent.OnNombreChange   -> nombreEdit   = event.nombre
            is AjustesEvent.OnTelefonoChange -> telefonoEdit = event.telefono
            is AjustesEvent.OnCalleChange    -> calleEdit    = event.calle

            is AjustesEvent.OnGuardarPerfil -> {
                usuarioState?.let { currentUser ->
                    val updatedUser = currentUser.copy(
                        nombre   = nombreEdit,
                        telefono = telefonoEdit,
                        calle    = calleEdit
                    )
                    viewModelScope.launch {
                        usuarioRepo.update(updatedUser)
                        usuarioState = updatedUser
                        onPerfilActualizado()
                        onBack()
                    }
                }
            }

            is AjustesEvent.OnNotificacionChange -> notificacionesState[event.clave] = event.activo
            is AjustesEvent.OnPrivacidadChange   -> privacidadState[event.clave]     = event.activo
        }
    }
}