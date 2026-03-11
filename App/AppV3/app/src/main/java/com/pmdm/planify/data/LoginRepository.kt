package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.data.room.UsuarioEntity
import com.pmdm.planify.models.Login
import com.pmdm.planify.models.Usuario
import java.util.UUID
import javax.inject.Inject

class LoginRepository @Inject constructor(context: Context) {

    private val dao = PlanifyDB.getDatabase(context).usuarioDao()

    suspend fun autenticar(email: String, pass: String): Login? {
        val passHash = pass.hashCode().toString()
        val usuario = dao.getByCorrro(email) ?: return null

        if (usuario.password != passHash) return null

        return Login(
            email = usuario.correo,
            password = "",          // No devolvemos la contraseña a la UI
            token = "tk-${usuario.id}",
            esNuevoUsuario = false
        )
    }

    suspend fun registrarNuevo(email: String, pass: String): Login {
        val passHash = pass.hashCode().toString()
        val token = "tk-${UUID.randomUUID()}"

        // Crea el perfil del nuevo usuario y lo persiste en Room
        val nuevoUsuario = UsuarioEntity(
            nombre = "Nuevo Usuario",
            correo = email,
            password = passHash,
            telefono = "",
            calle = "",
            fotoPerfil = null
        )
        dao.insert(nuevoUsuario)

        return Login(
            email = email,
            password = "",
            token = token,
            esNuevoUsuario = true
        )
    }
}