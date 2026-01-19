package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.UsuarioDaoMock
import com.pmdm.planify.models.Usuario

class UsuarioRepository {
    // Instancia del DAO simulado (simula la conexión a DB)
    private val dao = UsuarioDaoMock()

    // Obtener el usuario principal (simulando sesión iniciada)
    // Accedemos a la propiedad 'usuario' del DAO Mock y la convertimos al modelo real
    fun get(): Usuario = dao.usuario.toUsuario()

    // Simulación de "Insertar" o Guardar
    // En este contexto Mock, como el usuario ya existe en memoria, actualizamos sus valores
    fun insert(usuario: Usuario) {
        val mock = usuario.toUsuarioMock()

        // Actualizamos los campos en memoria del DAO
        dao.usuario.nombre = mock.nombre
        dao.usuario.correo = mock.correo
        dao.usuario.telefono = mock.telefono
        dao.usuario.calle = mock.calle
        dao.usuario.fotoPerfil = mock.fotoPerfil
        dao.usuario.tareas = mock.tareas
        dao.usuario.economia = mock.economia
        dao.usuario.estadoAnimo = mock.estadoAnimo
        dao.usuario.home = mock.home
    }

    // Actualizar datos del usuario (alias de insert en este caso simplificado)
    fun update(usuario: Usuario) {
        insert(usuario)
    }
}