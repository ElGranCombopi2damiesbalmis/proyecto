package com.pmdm.planify.data

import android.content.Context
import com.pmdm.planify.data.room.PlanifyDB
import com.pmdm.planify.data.room.UsuarioEntity
import com.pmdm.planify.models.Login
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.MessageDigest
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val userSessionRepository: UserSessionRepository
) {

    private val dao = PlanifyDB.getDatabase(context).usuarioDao()

    // --- FUNCIÓN DE CIFRADO SHA-256 ---
    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    suspend fun autenticar(email: String, pass: String): Login? {
        val passHash = hashPassword(pass)
        // Usamos el método de tu DAO (getByCorrro tiene un pequeño typo en su nombre original, lo mantenemos igual)
        val usuario = dao.getByCorrro(email.trim()) ?: return null

        if (usuario.password != passHash) return null

        userSessionRepository.setCurrentUserEmail(usuario.correo)
        return Login(email = usuario.correo, password = "", esNuevoUsuario = false)
    }

    suspend fun registrarNuevo(nombre: String, email: String, pass: String): Login {
        if (dao.getByCorrro(email.trim()) != null) throw IllegalArgumentException("email_duplicado")

        val passHash = hashPassword(pass)

        dao.insert(
            UsuarioEntity(
                nombre = nombre.trim(),
                correo = email.trim(),
                password = passHash,
                telefono = "",
                calle = "",
                fotoPerfil = null
            )
        )
        return Login(email = email.trim(), password = "", esNuevoUsuario = true)
    }

    suspend fun autenticarConGoogle(email: String): Login {
        val cleanEmail = email.trim()
        var usuario = dao.getByCorrro(cleanEmail)

        // Si es la primera vez que entra con este Google, le creamos la cuenta automáticamente
        if (usuario == null) {
            // Extraemos un nombre bonito del email (ej: juan.perez@gmail -> Juan Perez)
            val nombreDerivado = cleanEmail.substringBefore("@").split('.', '_', '-')
                .filter { it.isNotBlank() }
                .joinToString(" ") { parte -> parte.replaceFirstChar { c -> c.uppercase() } }
                .ifBlank { "Usuario" }

            usuario = UsuarioEntity(
                nombre = nombreDerivado,
                correo = cleanEmail,
                password = "", // Los usuarios de Google no necesitan contraseña local
                telefono = "",
                calle = "",
                fotoPerfil = null
            )
            dao.insert(usuario)
        }

        userSessionRepository.setCurrentUserEmail(cleanEmail)
        return Login(email = cleanEmail, password = "", esNuevoUsuario = false)
    }
}