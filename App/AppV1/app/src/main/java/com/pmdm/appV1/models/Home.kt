package com.pmdm.appV1.models

data class Home(
    var fraseBienvenida: String = "",
    var notificacionesPendientes: Int = 0
) {
    // Constructor secundario para conveniencia, similar a tu Java
    constructor(nombreUsuario: String) : this(
        fraseBienvenida = "¡Bienvenido, $nombreUsuario!",
        notificacionesPendientes = 0
    )
}
