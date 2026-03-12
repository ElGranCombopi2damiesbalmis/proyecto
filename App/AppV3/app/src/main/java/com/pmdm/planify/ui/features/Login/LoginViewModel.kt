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
        viewModelScope.launch {
            val resultado = loginRepository.autenticar(email, password)
            if (resultado != null) {
                errorMessage = null
                onLoginSuccess?.invoke()
            } else {
                errorMessage = "Email o contraseña incorrectos"
            }
        }
    }
}