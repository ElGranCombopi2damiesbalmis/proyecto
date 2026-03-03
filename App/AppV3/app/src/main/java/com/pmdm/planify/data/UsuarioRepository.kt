package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.UsuarioDaoMock
import com.pmdm.planify.models.Usuario
import javax.inject.Inject

class UsuarioRepository @Inject constructor(){
    private val dao = UsuarioDaoMock()

    // Este método ahora es dinámico: devuelve el usuario que hizo login
    fun get(): Usuario = dao.usuario.toUsuario()

    fun getUsuarioByEmail(email: String): Usuario? {
        return UsuarioDaoMock.listaUsuarios
            .find { it.correo.equals(email, ignoreCase = true) }
            ?.toUsuario()
    }

    // Permite al sistema de Login establecer quién es el usuario actual
    fun establecerSesion(email: String) {
        UsuarioDaoMock.emailSesionActiva = email
    }

    fun update(usuario: Usuario) {
        val mock = usuario.toUsuarioMock()
        val index = UsuarioDaoMock.listaUsuarios.indexOfFirst { it.correo == mock.correo }

        if (index != -1) {
            UsuarioDaoMock.listaUsuarios[index] = mock
        }
    }
}