package com.pmdm.appV1.data

import com.pmdm.appV1.data.daomocks.LoginDaoMock
import com.pmdm.appV1.data.mocks.LoginMock
import com.pmdm.appV1.models.Login
import java.util.UUID

class LoginRepository {
    private val dao = LoginDaoMock()

    /**
     * Valida las credenciales contra el Mock
     * Retorna el usuario si tiene éxito, null si falla
     */
    fun autenticar(email: String, pass: String): Login? {
        val mockEncontrado = dao.credencialesValidas.find {
            it.email == email && it.contrasenia == pass
        }

        return mockEncontrado?.toLoginUser()
    }

    /**
     * Simulación de registro
     */
    fun registrarNuevo(email: String, pass: String): Login {
        val nuevoMock = LoginMock(email, pass, "tk-${UUID.randomUUID()}")
        dao.credencialesValidas.add(nuevoMock)
        return nuevoMock.toLoginUser().copy(esNuevoUsuario = true)
    }
}