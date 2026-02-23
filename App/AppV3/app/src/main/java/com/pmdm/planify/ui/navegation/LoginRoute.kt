package com.pmdm.planify.ui.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.planify.ui.LoginScreen
import com.pmdm.planify.ui.features.PlanifyVM
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute


fun NavGraphBuilder.loginDestination(vm: PlanifyVM) {
    composable<LoginRoute> {
        LoginScreen(
            onLoginSuccess = vm.onNavigateToEconomia
        )
    }
}