package com.pmdm.planify.ui.navegation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import com.pmdm.planify.ui.features.Ajustes.AjustesNotificacionesScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesPerfilScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesPrivacidadScreen
import com.pmdm.planify.ui.features.Ajustes.AjustesVM
import com.pmdm.planify.ui.features.Ajustes.EditarPerfilScreen
import com.pmdm.planify.ui.features.Login.LoginViewModel
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


    val ajustesVm = hiltViewModel<AjustesVM>()
    ajustesVm.onBack = { nc.popBackStack() }

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
        // --- AUTH ---
        /*loginDestination(
            vm = loginVm,
            onNavigateToHome = {
                nc.navigate(InicioRoute) {
                    popUpTo(LoginRoute) { inclusive = true } // Limpia el historial
                }
            }
        )

        inicioDestination(
            onNavigateToSettings = { nc.navigate(SettingsRoute) }
        )*/

        tareasDestination()

        gymDestination(
            onBack = { nc.popBackStack() }
        )

        /*composable<EconomiaRoute> {

            GastosScreen(
                vm = gastosVm,
                onNavigateToNuevaTransaccion = { nc.navigate(TransaccionRoute) },
                onNavigateToSettings = { nc.navigate(SettingsRoute) },
                // PASAMOS EL NAVCONTROLLER PARA LA BOTTOM BAR
                navController = nc

            /*GastosScreen() // Reemplaza por tu pantalla real si le cambiaste el nombre*/
        }*/

        // --- TAREAS ---
        composable<TareaRoute> {
            TaskManagerScreen()
        }

        // --- ÁNIMO ---
        /*composable<EstadoDeAnimoRoute> {
            EstadoDeAnimoScreen(
                onBackClick = { vm.onPlanifyEvent(PlanifyEvent.OnBack) }
>>>>>>> 49162f325ab2727e924248109f5c24ce4c1e40ef
            )
        }*/


        animoDestination()

        transaccionDestination(
            onBack = { nc.popBackStack() }
        )

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