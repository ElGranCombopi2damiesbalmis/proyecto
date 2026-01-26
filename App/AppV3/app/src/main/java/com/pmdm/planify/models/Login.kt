package com.pmdm.planify.models

data class Login(
    val email: String,
    var password: String = "",
    val token: String,
    val esNuevoUsuario: Boolean = false
)