
package com.pmdm.planify.data

import com.pmdm.planify.data.room.UsuarioEntity
import com.pmdm.planify.models.Usuario

fun Usuario.toUsuarioEntity() = UsuarioEntity(
    id = id,
    nombre = nombre,
    correo = email,
    password = password,
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil
)

fun UsuarioEntity.toUsuario() = Usuario(
    id = id,
    nombre = nombre,
    email = correo,
    password = password,
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil
)
