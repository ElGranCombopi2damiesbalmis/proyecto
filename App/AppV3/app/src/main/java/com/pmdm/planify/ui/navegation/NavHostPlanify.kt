package com.pmdm.planify.ui.navegation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.ui.features.AnalisisDeGastos.GastosScreen
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import com.pmdm.planify.ui.features.Ajustes.AjustesNotificacionesScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesPerfilScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesPrivacidadScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesVM
import com.pmdm.planify.ui.features.Ajustes.EditarPerfilScreen
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoDeAnimoScreen
import com.pmdm.planify.ui.features.Login.LoginViewModel
import com.pmdm.planify.ui.features.PlanifyEvent
import com.pmdm.planify.ui.features.PlanifyViewModel
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen

@Composable
fun NavHostPlanify() {
    val nc = rememberNavController()
    val loginVm: LoginViewModel = hiltViewModel()
    val gastosVm: AnalisisDeGastosViewModel = hiltViewModel()

    vm.onBack = { nc.popBackStack() }
    val ajustesVm = hiltViewModel<AjustesVM>()

    ajustesVm.onBack = { nc.popBackStack() }
    ajustesVm.onNavigateToLogin = {
        nc.navigate(LoginRoute) { popUpTo(0) }
    }
    ajustesVm.onNavigateToEditarPerfil = { nc.navigate(EditarPerfilRoute) }
    ajustesVm.onNavigateToNotificaciones = { nc.navigate(NotificacionesRoute) }
    ajustesVm.onNavigateToPrivacidad = { nc.navigate(PrivacidadRoute) }

    NavHost(
        navController = nc,
        startDestination = LoginRoute
    ) {
        loginDestination(
            vm = loginVm,
            onNavigateToHome = {
                nc.navigate(HomeRoute) {
                    popUpTo(LoginRoute) { inclusive = true }
                }
            }
        )

        inicioDestination()

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

        composable<TareaRoute> {
            TaskManagerScreen(navController = nc)
        }

        animoDestination()

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

        composable<SettingsRoute> {
            AjustesPerfilScreen(vm = ajustesVm)
        }
        composable<EditarPerfilRoute> {
            EditarPerfilScreen(vm = ajustesVm)
        }
        composable<NotificacionesRoute> {
            AjustesNotificacionesScreen(vm = ajustesVm)
        }
        composable<PrivacidadRoute> {
            AjustesPrivacidadScreen(vm = ajustesVm)
        }
    }
}