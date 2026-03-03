package com.pmdm.planify.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var onLoginSuccess: (() -> Unit)? = null

    fun onEmailChanged(newValue: String) {
        email = newValue
        errorMessage = null
    }

    fun onPasswordChanged(newValue: String) {
        password = newValue
        errorMessage = null
    }

    fun onLoginClick() {
        // 1. Buscamos el usuario en el Mock/Repositorio
        val usuarioEncontrado = usuarioRepository.getUsuarioByEmail(email)

        // 2. Validamos credenciales
        if (usuarioEncontrado != null && usuarioEncontrado.password == password) {
            errorMessage = null
            // 3. Establecemos la sesión activa
            usuarioRepository.establecerSesion(email)
            // 4. Ejecutamos la navegación
            onLoginSuccess?.invoke()
        } else {
            errorMessage = "Email o contraseña incorrectos"
        }
    }
}