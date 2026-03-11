package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.models.Usuario
import javax.inject.Inject

class UsuarioRepository @Inject constructor(context: Context) {

    private val dao = PlanifyDB.getDatabase(context).usuarioDao()

    // Devuelve el usuario por su correo (usado tras el login)
    suspend fun get(correo: String): Usuario? =
        dao.getByCorrro(correo)?.toUsuario()

    suspend fun getAll(): List<Usuario> =
        dao.getAll().map { it.toUsuario() }

    suspend fun insert(usuario: Usuario) =
        dao.insert(usuario.toUsuarioEntity())

    suspend fun update(usuario: Usuario) =
        dao.update(usuario.toUsuarioEntity())

    suspend fun count(): Int =
        dao.count()
}