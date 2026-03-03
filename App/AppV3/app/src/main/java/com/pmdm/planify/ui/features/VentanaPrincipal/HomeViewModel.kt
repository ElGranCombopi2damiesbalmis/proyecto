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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    // Estado privado que maneja los datos de la Home
    private val _uiState = MutableStateFlow(Home())
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

    /**
     * Ejemplo de cómo actualizar los datos desde la UI
     */
    fun onFraseBienvenidaChanged(nuevaFrase: String) {
        val currentHome = _uiState.value
        val updatedHome = currentHome.copy(fraseBienvenida = nuevaFrase)

        repository.update(updatedHome)
        _uiState.update { updatedHome }
    }
}