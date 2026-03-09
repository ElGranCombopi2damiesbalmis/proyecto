package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.HomeMock
import com.pmdm.planify.models.Home

// --- De Modelo a Mock ---
fun Home.toHomeMock() = HomeMock(
    fraseBienvenida = fraseBienvenida,
    notificacionesPendientes = notificacionesPendientes
)

// --- De Mock a Modelo ---
fun HomeMock.toHome() = Home(
    nombreUsuario = "",          // El nombre viene del UsuarioRepository, no del HomeMock
    fraseBienvenida = fraseBienvenida,
    notificacionesPendientes = notificacionesPendientes
)