package com.pmdm.appV2.data

import com.pmdm.appV2.data.mocks.UsuarioMock
import com.pmdm.appV2.models.Usuario

// --- De Modelo a Mock ---
fun Usuario.toUsuarioMock() = UsuarioMock(
    id = id,
    nombre = nombre,
    correo = correo,
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil,
    // Como en tu estructura actual UsuarioMock usa los mismos modelos internos (Tarea, Economia...),
    // pasamos las referencias directamente.
    tareas = tareas,
    economia = economia,
    estadoAnimo = estadoAnimo,
    home = home
)

// --- De Mock a Modelo ---
fun UsuarioMock.toUsuario() = Usuario(
    id = id,
    nombre = nombre,
    correo = correo,
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil,
    tareas = tareas,
    economia = economia,
    estadoAnimo = estadoAnimo,
    home = home
)