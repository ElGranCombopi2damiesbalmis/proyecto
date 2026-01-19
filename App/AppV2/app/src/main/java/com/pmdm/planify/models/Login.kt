package com.pmdm.appV1.models

data class Login(
    val email: String,
    val token: String,
    val esNuevoUsuario: Boolean = false
)