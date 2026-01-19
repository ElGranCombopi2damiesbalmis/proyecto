package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.LoginMock
import com.pmdm.planify.models.Login

fun LoginMock.toLoginUser() = Login(
    email = this.email,
    token = this.authToken,
    esNuevoUsuario = false
)