package com.pmdm.planify.ui.navegation

import GastosScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.ui.LoginScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesNotificacionesScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesPerfilScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesPrivacidadScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesVM
import com.pmdm.planify.ui.features.Ajustes.EditarPerfilScreen
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoDeAnimoScreen
import com.pmdm.planify.ui.features.PlanifyEvent
import com.pmdm.planify.ui.features.PlanifyViewModel
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen

@Composable
fun NavHostPlanify() {
    val nc = rememberNavController()
    // Instanciamos el ViewModel usando Hilt
    val vm = hiltViewModel<PlanifyViewModel>()

    // Mapeo de navegación del ViewModel -> NavController
    vm.onNavigateToEconomia = { nc.navigate(EconomiaRoute) }
    vm.onNavigateToTransaccion = { nc.navigate(TransaccionRoute) }
    vm.onNavigateToEstadoAnimo = { nc.navigate(EstadoDeAnimoRoute) }
    vm.onNavigateToSettings = { nc.navigate(SettingsRoute) }
    vm.onNavigateToTarea = { nc.navigate(TareaRoute) }
    vm.onBack = { nc.popBackStack() }
    val ajustesVm = hiltViewModel<AjustesVM>()

    // Configuramos su navegación
    ajustesVm.onBack = { nc.popBackStack() }
    ajustesVm.onNavigateToLogin = {
        nc.navigate(LoginRoute) { popUpTo(0) } // Al cerrar sesión limpia la pila
    }
    ajustesVm.onNavigateToEditarPerfil = { nc.navigate(EditarPerfilRoute) }
    ajustesVm.onNavigateToNotificaciones = { nc.navigate(NotificacionesRoute) }
    ajustesVm.onNavigateToPrivacidad = { nc.navigate(PrivacidadRoute) }

    NavHost(
        navController = nc,
        startDestination = LoginRoute
    ) {
        // --- LOGIN ---
        composable<LoginRoute> {
            LoginScreen(
                onLoginClick = { email, pass ->
                    vm.onPlanifyEvent(PlanifyEvent.OnLoginClick(email, pass))
                }
            )
        }

        // --- ECONOMÍA ---
        composable<EconomiaRoute> {
            GastosScreen() // Reemplaza por tu pantalla real si le cambiaste el nombre
        }

        // --- TAREAS ---
        composable<TareaRoute> {
            TaskManagerScreen()
        }

        // --- ÁNIMO ---
        /*composable<EstadoDeAnimoRoute> {
            EstadoDeAnimoScreen(
                onBackClick = { vm.onPlanifyEvent(PlanifyEvent.OnBack) }
            )
        }*/

        // --- RUTAS DE AJUSTES ---
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