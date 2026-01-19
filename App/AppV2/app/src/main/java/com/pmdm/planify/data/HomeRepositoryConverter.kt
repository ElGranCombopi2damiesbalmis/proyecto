package com.pmdm.appV2.data

import com.pmdm.appV2.data.mocks.HomeMock
import com.pmdm.appV2.models.Home

// --- De Modelo a Mock ---
fun Home.toHomeMock() = HomeMock(
    fraseBienvenida = fraseBienvenida,
    notificacionesPendientes = notificacionesPendientes
)

// --- De Mock a Modelo ---
fun HomeMock.toHome() = Home(
    fraseBienvenida = fraseBienvenida,
    notificacionesPendientes = notificacionesPendientes
)