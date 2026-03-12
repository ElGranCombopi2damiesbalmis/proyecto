package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.data.room.UsuarioEntity
import com.pmdm.planify.models.Login
import com.pmdm.planify.models.Usuario
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject

class LoginRepository @Inject constructor(@ApplicationContext context: Context) {

    private val dao = PlanifyDB.getDatabase(context).usuarioDao()

    suspend fun autenticar(email: String, pass: String): Login? {
        val passHash = pass.hashCode().toString()
        val usuario  = dao.getByCorrro(email) ?: return null
        if (usuario.password != passHash) return null
        return Login(email = usuario.correo, password = "", token = "tk-${usuario.id}", esNuevoUsuario = false)
    }

    suspend fun registrarNuevo(nombre: String, email: String, pass: String): Login {
        val passHash = pass.hashCode().toString()
        val token    = "tk-${UUID.randomUUID()}"
        dao.insert(UsuarioEntity(nombre = nombre, correo = email, password = passHash, telefono = "", calle = "", fotoPerfil = null))
        return Login(email = email, password = "", token = token, esNuevoUsuario = true)
    }
}