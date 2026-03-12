package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.LoginMock

class LoginDaoMock {
    companion object {
        // Lo hacemos estático (singleton) para que persista durante la ejecución
        val credencialesValidas = mutableListOf(
            // "1234".hashCode() es 1509442
            LoginMock("ayman@email.com", "2112444630", "tk-session-001"),
            LoginMock("victor@ejemplo.com", "-1206291356", "tk-session-002")
        )
    }.com
}