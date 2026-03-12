package com.pmdm.planify.ui.navegation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.pmdm.planify.ui.features.AnalisisDeGastos.GastosScreen
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoAnimoVM
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoDeAnimoScreen
import com.pmdm.planify.ui.features.Login.LoginViewModel
import com.pmdm.planify.ui.features.RutinasDeGimnasio.GymVM
import com.pmdm.planify.ui.features.RutinasDeGimnasio.RutinasGimnasioScreen
import com.pmdm.planify.ui.features.Tareas.TareaViewModel
import com.pmdm.planify.ui.features.Tareas.TaskManagerScreen
import com.pmdm.planify.ui.features.VentanaPrincipal.DashboardScreen
import com.pmdm.planify.ui.features.VentanaPrincipal.HomeViewModel

@Composable
fun NavHostPlanify() {
    val nc = rememberNavController()

    // ── ViewModels (instancia única compartida en todo el NavHost) ─────────────
    val loginVm:   LoginViewModel             = hiltViewModel()
    val homeVm:    HomeViewModel              = hiltViewModel()
    val gastosVm:  AnalisisDeGastosViewModel  = hiltViewModel()
    val gymVm:     GymVM                      = hiltViewModel()
    val tareasVm:  TareaViewModel             = hiltViewModel()
    val animoVm:   EstadoAnimoVM              = hiltViewModel()
    val ajustesVm: AjustesVM                  = hiltViewModel()

    // ── Callbacks de sincronización con Home ──────────────────────────────────
    // Al guardar tarea o transacción → Home se refresca automáticamente
    tareasVm.onTareaGuardada          = { homeVm.cargarDatos() }
    gastosVm.onTransaccionGuardada    = { homeVm.cargarDatos() }
    animoVm.onAnimoCambiado           = { homeVm.cargarDatos() }

    // ── Ajustes ────────────────────────────────────────────────────────────────
    ajustesVm.onBack                     = { nc.popBackStack() }
    ajustesVm.onNavigateToLogin          = { nc.navigate(LoginRoute) { popUpTo(0) { inclusive = true } } }
    ajustesVm.onNavigateToEditarPerfil   = { nc.navigate(EditarPerfilRoute) }
    ajustesVm.onNavigateToNotificaciones = { nc.navigate(NotificacionesRoute) }
    ajustesVm.onNavigateToPrivacidad     = { nc.navigate(PrivacidadRoute) }

    NavHost(navController = nc, startDestination = LoginRoute) {

        // ── LOGIN ──────────────────────────────────────────────────────────────
        composable<LoginRoute> {
            loginVm.onLoginSuccess = {
                homeVm.cargarDatos()
                animoVm.cargarDatos()
                nc.navigate(HomeRoute) { popUpTo(LoginRoute) { inclusive = true } }
            }
            loginVm.onGoogleLoginSuccess = {
                homeVm.cargarDatos()
                animoVm.cargarDatos()
                nc.navigate(HomeRoute) { popUpTo(LoginRoute) { inclusive = true } }
            }
            LoginScreen(vm = loginVm)
        }

        // ── HOME ───────────────────────────────────────────────────────────────
        composable<HomeRoute> {
            DashboardScreen(
                navController = nc,
                viewModel     = homeVm,
                animoVm       = animoVm   // Compartido para sincronizar ánimo
            )
        }

        // ── TAREAS ─────────────────────────────────────────────────────────────
        composable<TareaRoute> {
            TaskManagerScreen(navController = nc, viewModel = tareasVm)
        }

        // ── GYM ────────────────────────────────────────────────────────────────
        composable<GymRoute> {
            RutinasGimnasioScreen(navController = nc, vm = gymVm)
        }

        // ── ECONOMÍA ───────────────────────────────────────────────────────────
        composable<EconomiaRoute> {
            GastosScreen(
                navController              = nc,
                vm                         = gastosVm,
                onNavigateToNuevaTransaccion = { nc.navigate(TransaccionRoute) },
                onNavigateToSettings       = { nc.navigate(SettingsRoute) }
            )
        }

        composable<TransaccionRoute> {
            Box(modifier = Modifier.fillMaxSize().clickable { nc.popBackStack() }, contentAlignment = Alignment.Center) {
                Text("Nueva Transacción\n(Pulsa para volver)")
            }
        }

        // ── ESTADO DE ÁNIMO ────────────────────────────────────────────────────
        composable<EstadoDeAnimoRoute> {
            EstadoDeAnimoScreen(navController = nc, vm = animoVm)
        }

        // ── AJUSTES ────────────────────────────────────────────────────────────
        composable<SettingsRoute>      { AjustesPerfilScreen(vm = ajustesVm) }
        composable<EditarPerfilRoute>  { EditarPerfilScreen(vm = ajustesVm) }
        composable<NotificacionesRoute>{ AjustesNotificacionesScreen(vm = ajustesVm) }
        composable<PrivacidadRoute>    { AjustesPrivacidadScreen(vm = ajustesVm) }
    }
}