package com.pmdm.planify.ui.navegation

import androidx.compose.runtime.Composable

@Composable
fun NavHostPlanify() {
    val nc = rememberNavController()
    val vm = hiltViewModel<PlanifyViewModel>() // Reemplaza por tu ViewModel real

    // Configuración de eventos de navegación desde el VM
    vm.onNavigateToEconomia = { nc.navigate(EconomiaRoute) }
    vm.onNavigateToTransaccion = { nc.navigate(TransaccionRoute) }
    vm.onNavigateToEstadoDeAnimo = { nc.navigate(EstadoDeAnimoRoute) }
    vm.onNavigateToSettings = { nc.navigate(SettingsRoute) }
    vm.onNavigateToTarea = { nc.navigate(TareaRoute) }
    vm.onBack = { nc.popBackStack() }

    NavHost(
        navController = nc,
        startDestination = LoginRoute
    ) {
        loginDestination(vm = vm)
        economiaDestination(vm = vm)
        transaccionDestination(vm = vm)
        estadoDeAnimoDestination(vm = vm)
        settingsDestination(vm = vm)
        tareaDestination(vm = vm)
    }
}