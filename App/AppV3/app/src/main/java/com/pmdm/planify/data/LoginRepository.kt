package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.LoginDaoMock
import com.pmdm.planify.data.daomocks.UsuarioDaoMock
import com.pmdm.planify.data.mocks.LoginMock
import com.pmdm.planify.data.mocks.UsuarioMock
import com.pmdm.planify.models.Login
import java.util.UUID
import javax.inject.Inject

class LoginRepository @Inject constructor(){
    private val loginDao = LoginDaoMock()
    private val usuarioDao = UsuarioDaoMock() // Conectamos con el DAO de usuarios

    fun autenticar(email: String, pass: String): Login? {
        // 1. Convertimos la contraseña que el usuario escribe a su HashCode
        val passHashIngresado = pass.hashCode().toString()

        // 2. Buscamos en la lista estática del DAO
        val mockEncontrado = LoginDaoMock.credencialesValidas.find {
            // Comparamos email e ignoramos mayúsculas por seguridad
            it.email.equals(email, ignoreCase = true) &&
                    // Comparamos el HASH calculado con el HASH guardado (sin volver a hashear)
                    it.contrasenia == passHashIngresado
        }

        if (mockEncontrado != null) {
            // Establecemos la sesión en el repositorio de usuario
            UsuarioRepository().establecerSesion(email)
            return mockEncontrado.toLoginUser()
        }
        return null
    }

    fun registrarNuevo(email: String, pass: String): Login {
        val passHash = pass.hashCode().toString()
        val token = "tk-${UUID.randomUUID()}"

        // 1. Guardar credenciales
        val nuevoLogin = LoginMock(email, passHash, token)
        LoginDaoMock.credencialesValidas.add(nuevoLogin)

        // 2. Crear perfil de usuario vacío vinculado a ese email
        val nuevoUsuario = UsuarioMock(nombre = "Nuevo Usuario", correo = email, "", "")
        // Asumiendo que añades una lista de usuarios en UsuarioDaoMock:
        UsuarioDaoMock.listaUsuarios.add(nuevoUsuario)

        return nuevoLogin.toLoginUser().copy(esNuevoUsuario = true)
    }
}