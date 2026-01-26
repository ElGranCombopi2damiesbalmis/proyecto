package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.UsuarioMock
import com.pmdm.planify.models.Usuario

// --- De Modelo a Mock ---
fun Usuario.toUsuarioMock() = UsuarioMock(
    // El modelo Usuario no tiene ID en su constructor principal según tu archivo,
    // pero si UsuarioMock lo requiere, lo manejamos así:
    nombre = nombre,
    correo = email, // MAPEAMOS: email (Modelo) -> correo (Mock)
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil,
    tareas = tareas,
    economia = economia,
    estadoAnimo = estadoAnimo,
    home = home
)

// --- De Mock a Modelo ---
fun UsuarioMock.toUsuario() = Usuario(
    nombre = nombre,
    email = correo, // MAPEAMOS: correo (Mock) -> email (Modelo)
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil,
    tareas = tareas,
    economia = economia,
    estadoAnimo = estadoAnimo,
    home = home
).apply {
    // Si necesitas mantener el mismo ID exacto entre ambos:
    // this.id = id
}