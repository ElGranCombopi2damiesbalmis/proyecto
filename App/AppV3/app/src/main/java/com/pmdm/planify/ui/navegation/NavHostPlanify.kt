package com.pmdm.planify.ui.navegation

import GastosScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.ui.LoginScreen
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
        composable<EstadoDeAnimoRoute> {
            EstadoDeAnimoScreen(
                onBackClick = { vm.onPlanifyEvent(PlanifyEvent.OnBack) }
            )
        }

        // Agrega el resto de tus pantallas aquí (Gym, Settings, etc.) usando composable<TuRuta> {}
    }
}