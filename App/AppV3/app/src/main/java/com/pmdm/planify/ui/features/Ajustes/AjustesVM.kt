package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.models.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AjustesVM @Inject constructor(
    private val usuarioRepo: UsuarioRepository
) : ViewModel() {

    // 1. Estado del Usuario Logueado
    var usuarioState by mutableStateOf<Usuario?>(null)
        private set

    // 2. Estados temporales para Editar Perfil (para no guardar hasta darle a "Guardar")
    var nombreEdit by mutableStateOf("")
    var telefonoEdit by mutableStateOf("")
    var calleEdit by mutableStateOf("")

    // 3. Estados para Notificaciones (Clave -> Booleano)
    var notificacionesState = mutableStateMapOf(
        "todas" to true,
        "tareas" to true,
        "gastos" to false,
        "gym" to true
    )

    // 4. Estados para Privacidad (Clave -> Booleano)
    var privacidadState = mutableStateMapOf(
        "perfil_publico" to false,
        "mostrar_animo" to true,
        "compartir_datos" to false
    )

    // 5. Rutas de navegación (se configuran en el NavHost)
    var onNavigateToLogin: () -> Unit = {}
    var onNavigateToEditarPerfil: () -> Unit = {}
    var onNavigateToNotificaciones: () -> Unit = {}
    var onNavigateToPrivacidad: () -> Unit = {}
    var onBack: () -> Unit = {}

    init {
        cargarUsuario()
    }

    private fun cargarUsuario() {
        viewModelScope.launch {
            val user = usuarioRepo.get()
            usuarioState = user
            // Inicializamos los campos de edición con los datos reales
            nombreEdit = user.nombre
            telefonoEdit = user.telefono
            calleEdit = user.calle
        }
    }

    fun onEvent(event: AjustesEvent) {
        when (event) {
            is AjustesEvent.OnLogout -> {
                usuarioRepo.establecerSesion("") // Borramos sesión
                onNavigateToLogin()
            }

            // --- NAVEGACIÓN ---
            is AjustesEvent.OnBack -> onBack()
            is AjustesEvent.OnNavigateToEditarPerfil -> onNavigateToEditarPerfil()
            is AjustesEvent.OnNavigateToNotificaciones -> onNavigateToNotificaciones()
            is AjustesEvent.OnNavigateToPrivacidad -> onNavigateToPrivacidad()

            // --- EDICIÓN DE PERFIL ---
            is AjustesEvent.OnNombreChange -> nombreEdit = event.nombre
            is AjustesEvent.OnTelefonoChange -> telefonoEdit = event.telefono
            is AjustesEvent.OnCalleChange -> calleEdit = event.calle
            is AjustesEvent.OnGuardarPerfil -> {
                usuarioState?.let { currentUser ->
                    // Creamos una copia actualizada del usuario
                    val updatedUser = currentUser.copy(
                        nombre = nombreEdit,
                        telefono = telefonoEdit,
                        calle = calleEdit
                    )
                    // Guardamos en el repositorio
                    usuarioRepo.update(updatedUser)
                    usuarioState = updatedUser // Actualizamos la UI
                    onBack() // Volvemos a la pantalla anterior
                }
            }

            // --- SWITCHES ---
            is AjustesEvent.OnNotificacionChange -> notificacionesState[event.clave] = event.activo
            is AjustesEvent.OnPrivacidadChange -> privacidadState[event.clave] = event.activo
        }
    }
}