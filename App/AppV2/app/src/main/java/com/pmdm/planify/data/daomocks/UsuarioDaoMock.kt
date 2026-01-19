package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.UsuarioMock

class UsuarioDaoMock {
    // Simulamos la tabla de usuarios con un solo registro
    val usuario = UsuarioMock(
        nombre = "Juan Pérez",
        correo = "juan@email.com",
        telefono = "123456789",
        calle = "Calle Principal 123"
    )
}