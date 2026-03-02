package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.LoginScreen
import com.pmdm.planify.ui.features.Login.LoginViewModel
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavGraphBuilder.loginDestination(
    vm: LoginViewModel,
    onNavigateToHome: () -> Unit
) {
    composable<LoginRoute> {
        // Configuramos la acción de éxito del VM
        vm.onLoginSuccess = onNavigateToHome

        LoginScreen(vm = vm)
    }
}