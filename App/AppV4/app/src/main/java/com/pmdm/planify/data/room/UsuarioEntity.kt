package com.pmdm.planify.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// NOTA: Los campos complejos del Usuario (tareas, economia, estadoAnimo, home)
// se gestionan en sus propias tablas. Esta entidad guarda solo los datos básicos del perfil.
@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "correo")
    val correo: String,
    @ColumnInfo(name = "password")
    val password: String = "",
    @ColumnInfo(name = "telefono")
    val telefono: String,
    @ColumnInfo(name = "calle")
    val calle: String,
    @ColumnInfo(name = "foto_perfil")
    val fotoPerfil: String?
)