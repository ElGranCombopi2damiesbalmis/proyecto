package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.UsuarioMock

class UsuarioDaoMock {
    companion object {
        // Lista global de perfiles de usuario
        val listaUsuarios = mutableListOf(
            UsuarioMock(
                nombre = "Ayman",
                correo = "ayman@email.com", // Coincide con Login
                telefono = "600000001",
                calle = "Calle de Ayman 1"
            ),
            UsuarioMock(
                nombre = "Victor",
                correo = "victor@ejemplo.com", // Coincide con Login
                telefono = "600000002",
                calle = "Avenida de Victor 2"
            )
        )

        // Aquí guardaremos el email del usuario que logueó correctamente
        var emailSesionActiva: String? = null
    }

    // Propiedad para obtener los datos del usuario logueado actualmente
    val usuario: UsuarioMock
        get() = listaUsuarios.find { it.correo == emailSesionActiva }
            ?: listaUsuarios[0] // Por defecto el primero si no hay sesión (para evitar errores)
}