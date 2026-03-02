package com.pmdm.planify.ui.navegation

import GastosScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.ui.LoginScreen
import com.pmdm.planify.ui.features.AnalisisDeGastos.GastosScreen
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoDeAnimoScreen
import com.pmdm.planify.ui.features.Login.LoginViewModel
import com.pmdm.planify.ui.features.PlanifyEvent
import com.pmdm.planify.ui.features.PlanifyViewModel
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen

@Composable
fun NavHostPlanify() {
    val nc = rememberNavController()
    // Instanciamos el ViewModel usando Hilt
    val loginVm: LoginViewModel = hiltViewModel()
    val gastosVm: AnalisisDeGastosViewModel = hiltViewModel()

    /*vm.onNavigateToEconomia = { nc.navigate(EconomiaRoute) }
    vm.onNavigateToTransaccion = { nc.navigate(TransaccionRoute) }
    vm.onNavigateToEstadoAnimo = { nc.navigate(EstadoDeAnimoRoute) }
    vm.onNavigateToSettings = { nc.navigate(SettingsRoute) }
    vm.onNavigateToTarea = { nc.navigate(TareaRoute) }
    vm.onBack = { nc.popBackStack() }*/

    NavHost(
        navController = nc,
        startDestination = LoginRoute
    ) {
        // --- AUTH ---
        loginDestination(
            vm = loginVm,
            onNavigateToHome = {
                nc.navigate(InicioRoute) {
                    popUpTo(LoginRoute) { inclusive = true } // Limpia el historial
                }
            }
        )

        inicioDestination(
            onNavigateToSettings = { nc.navigate(SettingsRoute) }
        )

        tareasDestination()

        gymDestination(
            onBack = { nc.popBackStack() }
        )

        composable<EconomiaRoute> {
            GastosScreen(
                vm = gastosVm,
                onNavigateToNuevaTransaccion = { nc.navigate(TransaccionRoute) },
                onNavigateToSettings = { nc.navigate(SettingsRoute) },
                // PASAMOS EL NAVCONTROLLER PARA LA BOTTOM BAR
                navController = nc
            )
        }

        animoDestination()

        // --- AJUSTES Y PERFIL ---
        settingsDestination(
            onBack = { nc.popBackStack() },
            onNavigateToEditProfile = { nc.navigate(EditarPerfilRoute) },
            onNavigateToNotifications = { nc.navigate(NotificacionesRoute) },
            onNavigateToPrivacy = { nc.navigate(PrivacidadRoute) },
            onLogout = {
                nc.navigate(LoginRoute) {
                    popUpTo(0) { inclusive = true }
                }
            }
        )

        transaccionDestination(
            onBack = { nc.popBackStack() }
        )

        editarPerfilDestination(
            onBack = { nc.popBackStack() }
        )

        notificacionesDestination(
            onBack = { nc.popBackStack() }
        )

        privacidadDestination(
            onBack = { nc.popBackStack() }
        )
    }
}