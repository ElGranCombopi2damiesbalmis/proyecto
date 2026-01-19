package com.pmdm.appV1.data.daomocks

import com.pmdm.appV1.data.mocks.LoginMock

class LoginDaoMock {
    // Lista de credenciales válidas
    val credencialesValidas = mutableListOf(
        LoginMock("admin@ejemplo.com", "1234", "tk-998877"),
        LoginMock("usuario@test.com", "password", "tk-112233")
    )
}