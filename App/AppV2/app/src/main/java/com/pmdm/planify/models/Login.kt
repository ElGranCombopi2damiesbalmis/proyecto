package com.pmdm.planify.models

data class Login(
    val email: String,
    val token: String,
    val esNuevoUsuario: Boolean = false
)