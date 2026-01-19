package com.pmdm.appV1.data

import com.pmdm.appV1.data.mocks.LoginMock
import com.pmdm.appV1.models.Login

fun LoginMock.toLoginUser() = Login(
    email = this.email,
    token = this.authToken,
    esNuevoUsuario = false
)