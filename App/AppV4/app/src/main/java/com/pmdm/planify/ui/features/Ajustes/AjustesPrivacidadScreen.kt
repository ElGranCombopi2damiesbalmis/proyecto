package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesPrivacidadScreen(vm: AjustesVM) {
    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Privacidad", fontWeight = FontWeight.Bold, color = AppTextPrimary) },
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

            Text("Opciones de Privacidad", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))
            Column(modifier = Modifier.fillMaxWidth().background(AppSurface, RoundedCornerShape(20.dp)).padding(16.dp)) {

                SwitchItem("Perfil Público", vm.privacidadState["perfil_publico"] ?: false) {
                    vm.onEvent(AjustesEvent.OnPrivacidadChange("perfil_publico", it))
                }
                Spacer(modifier = Modifier.height(8.dp))
                SwitchItem("Mostrar mi Estado de Ánimo", vm.privacidadState["mostrar_animo"] ?: true) {
                    vm.onEvent(AjustesEvent.OnPrivacidadChange("mostrar_animo", it))
                }
                Spacer(modifier = Modifier.height(8.dp))
                SwitchItem("Compartir analíticas anónimas", vm.privacidadState["compartir_datos"] ?: false) {
                    vm.onEvent(AjustesEvent.OnPrivacidadChange("compartir_datos", it))
                }
            }
        }
    }
}