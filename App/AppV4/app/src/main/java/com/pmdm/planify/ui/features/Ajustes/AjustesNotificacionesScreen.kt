package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesNotificacionesScreen(vm: AjustesVM) {
    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notificaciones", fontWeight = FontWeight.Bold, color = AppTextPrimary) },
                navigationIcon = {
                    IconButton(onClick = { vm.onEvent(AjustesEvent.OnBack) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = AppTextPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AppBackground)
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(20.dp)) {

            Text("Preferencias", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))
            Column(modifier = Modifier.fillMaxWidth().background(AppSurface, RoundedCornerShape(20.dp)).padding(16.dp)) {

                SwitchItem("Pausar Todas", vm.notificacionesState["todas"] ?: false) {
                    vm.onEvent(AjustesEvent.OnNotificacionChange("todas", it))
                }
                Spacer(modifier = Modifier.height(8.dp))
                SwitchItem("Recordatorios de Tareas", vm.notificacionesState["tareas"] ?: true) {
                    vm.onEvent(AjustesEvent.OnNotificacionChange("tareas", it))
                }
                Spacer(modifier = Modifier.height(8.dp))
                SwitchItem("Alertas de Gastos", vm.notificacionesState["gastos"] ?: false) {
                    vm.onEvent(AjustesEvent.OnNotificacionChange("gastos", it))
                }
            }
        }
    }
}

@Composable
fun SwitchItem(title: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, color = AppTextPrimary, fontWeight = FontWeight.Medium)
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = AppTextPrimary, checkedTrackColor = AppPrimary)
        )
    }
}