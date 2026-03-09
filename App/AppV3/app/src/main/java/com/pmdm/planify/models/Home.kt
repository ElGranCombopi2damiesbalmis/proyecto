package com.pmdm.planify.models

data class Home(
    val nombreUsuario: String = "",
    var fraseBienvenida: String = "Hola",
    var notificacionesPendientes: Int = 0
)
