package com.pmdm.planify.data.daomocks

import com.pmdm.planify.data.mocks.LoginMock

class LoginDaoMock {
    companion object {
        val credencialesValidas = mutableListOf(
            LoginMock("ayman@email.com", "2112444630"),
            LoginMock("victor@ejemplo.com", "-1206291356")
        )
    }
}