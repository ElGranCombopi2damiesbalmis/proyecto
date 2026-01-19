package com.pmdm.appV2.data.mocks

import com.pmdm.appV2.models.*
import java.util.UUID

data class UsuarioMock(
    val id: String = UUID.randomUUID().toString(),
    var nombre: String = "",
    var correo: String = "",
    var telefono: String = "",
    var calle: String = "",
    var fotoPerfil: String? = null,

    var tareas: MutableList<Tarea> = mutableListOf(),
    var economia: Economia = Economia(),
    var estadoAnimo: EstadoAnimo = EstadoAnimo(),
    var home: Home = Home()
) {
    constructor(nombre: String, correo: String, telefono: String, calle: String) : this() {
        this.nombre = nombre
        this.correo = correo
        this.telefono = telefono
        this.calle = calle
        this.home.fraseBienvenida = "Bienvenido, $nombre"
    }
}