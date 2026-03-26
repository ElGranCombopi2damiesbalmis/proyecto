package com.pmdm.planify.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.LoginRepository
import com.pmdm.planify.data.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    // ── Login ─────────────────────────────────────────────────────────────────
    var email        by mutableStateOf("")
        private set
    var password     by mutableStateOf("")
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var onLoginSuccess:       (() -> Unit)? = null
    var onGoogleLoginSuccess: (() -> Unit)? = null

    fun onEmailChanged(v: String)    { email = v;    errorMessage = null }
    fun onPasswordChanged(v: String) { password = v; errorMessage = null }

    fun onLoginClick() {
        viewModelScope.launch {
            val resultado = loginRepository.autenticar(email, password)
            if (resultado != null) { errorMessage = null; onLoginSuccess?.invoke() }
            else errorMessage = "Email o contraseña incorrectos"
        }
    }

    fun onGoogleAccountSelected(email: String) {
        viewModelScope.launch {
            runCatching {
                loginRepository.autenticarConGoogle(email)
            }.onSuccess {
                errorMessage = null
                onGoogleLoginSuccess?.invoke()
            }.onFailure {
                errorMessage = "No se pudo iniciar sesión con Google"
            }
        }
    }

    // ── Registro ──────────────────────────────────────────────────────────────
    var mostrarDialogoRegistro  by mutableStateOf(false)
        private set
    var registroNombre          by mutableStateOf("")
        private set
    var registroEmail           by mutableStateOf("")
        private set
    var registroPassword        by mutableStateOf("")
        private set
    var registroPasswordConfirm by mutableStateOf("")
        private set
    var registroError           by mutableStateOf<String?>(null)
        private set
    var registroExito           by mutableStateOf(false)
        private set

    fun abrirDialogoRegistro() {
        registroNombre = ""; registroEmail = ""; registroPassword = ""
        registroPasswordConfirm = ""; registroError = null; registroExito = false
        mostrarDialogoRegistro = true
    }
    fun cerrarDialogoRegistro() { mostrarDialogoRegistro = false }

    fun onRegistroNombreChange(v: String)          { registroNombre = v;          registroError = null }
    fun onRegistroEmailChange(v: String)           { registroEmail = v;           registroError = null }
    fun onRegistroPasswordChange(v: String)        { registroPassword = v;        registroError = null }
    fun onRegistroPasswordConfirmChange(v: String) { registroPasswordConfirm = v; registroError = null }

    fun onRegistrarClick() {
        when {
            registroNombre.isBlank()              -> { registroError = "El nombre no puede estar vacío"; return }
            registroEmail.isBlank()               -> { registroError = "El email no puede estar vacío"; return }
            !registroEmail.contains("@")          -> { registroError = "Introduce un email válido"; return }
            registroPassword.length < 6           -> { registroError = "La contraseña debe tener al menos 6 caracteres"; return }
            registroPassword != registroPasswordConfirm -> { registroError = "Las contraseñas no coinciden"; return }
        }
        viewModelScope.launch {
            try {
                loginRepository.registrarNuevo(registroNombre.trim(), registroEmail.trim(), registroPassword)
                registroExito = true; registroError = null
            } catch (e: Exception) {
                registroError = "Este email ya está registrado"
            }
        }
    }
}