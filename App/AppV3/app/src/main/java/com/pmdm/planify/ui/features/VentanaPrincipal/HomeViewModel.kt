package com.pmdm.planify.ui.features.VentanaPrincipal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.planify.data.HomeRepository
import com.pmdm.planify.models.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class HomeUiState(
    val nombreUsuario: String = "Andrea",
    val fraseBienvenida: String = "¡Hola de nuevo!",
    val tareas: List<String> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    // Estado privado que maneja los datos de la Home
    private val _uiState = MutableStateFlow(Home(
        nombreUsuario = TODO(),
        fraseBienvenida = TODO(),
        notificacionesPendientes = TODO()
    ))
    // Estado público que la UI observará
    val uiState: StateFlow<Home> = _uiState.asStateFlow()

    init {
        // Cargamos los datos iniciales al crear el ViewModel
        loadHomeData()
    }

    private fun loadHomeData() {
        val homeData = repository.get()
        _uiState.update { homeData }
    }

    fun onFraseBienvenidaChanged(nuevaFrase: String) {
        val currentHome = _uiState.value
        val updatedHome = currentHome.copy(fraseBienvenida = nuevaFrase)

        repository.update(updatedHome)
        _uiState.update { updatedHome }
    }
}