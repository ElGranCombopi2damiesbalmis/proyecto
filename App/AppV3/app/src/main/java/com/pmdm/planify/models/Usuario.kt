package com.pmdm.planify.models

import java.util.UUID

data class Usuario(
    var nombre: String = "",
    var email: String = "",
    var password: String = "",
    var telefono: String = "",
    var calle: String = "",
    var fotoPerfil: String? = null,

    var tareas: MutableList<Tarea> = mutableListOf(),
    var economia: Economia = Economia(),
    var estadoAnimo: EstadoAnimo = EstadoAnimo(),
    var home: Home = Home()
) {
    constructor(nombre: String, email: String, telefono: String, calle: String) : this() {
        this.nombre = nombre
        this.email = email
        this.telefono = telefono
        this.calle = calle
        this.home.fraseBienvenida = "Bienvenido, $nombre"
    }
}