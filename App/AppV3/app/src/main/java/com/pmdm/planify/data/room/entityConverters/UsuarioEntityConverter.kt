package com.pmdm.planify.data

import com.pmdm.planify.data.room.UsuarioEntity
import com.pmdm.planify.models.Usuario

// --- De Modelo a Entity ---
// Solo se persisten los campos básicos del perfil.
// Las tareas, economía y estado de ánimo viven en sus propias tablas.
fun Usuario.toUsuarioEntity() = UsuarioEntity(
    nombre = nombre,
    correo = email,
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil
)

// --- De Entity a Modelo ---
// Los campos complejos se cargan por separado desde sus repositorios.
fun UsuarioEntity.toUsuario() = Usuario(
    nombre = nombre,
    email = correo,
    telefono = telefono,
    calle = calle,
    fotoPerfil = fotoPerfil
)