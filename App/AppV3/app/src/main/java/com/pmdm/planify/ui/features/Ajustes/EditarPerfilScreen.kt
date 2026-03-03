package com.pmdm.planify.ui.features.Ajustes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(vm: AjustesVM) {
    val usuario = vm.usuarioState

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Perfil", fontWeight = FontWeight.Bold, color = AppTextPrimary) },
                navigationIcon = {
                    IconButton(onClick = { vm.onEvent(AjustesEvent.OnBack) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver", tint = AppTextPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AppBackground)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre Editable
            OutlinedTextField(
                value = vm.nombreEdit,
                onValueChange = { vm.onEvent(AjustesEvent.OnNombreChange(it)) },
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Default.Person, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Correo (Solo lectura, normalmente no se cambia tan fácil)
            OutlinedTextField(
                value = usuario?.email ?: "",
                onValueChange = { },
                label = { Text("Correo Electrónico") },
                leadingIcon = { Icon(Icons.Default.Email, null) },
                readOnly = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Teléfono Editable
            OutlinedTextField(
                value = vm.telefonoEdit,
                onValueChange = { vm.onEvent(AjustesEvent.OnTelefonoChange(it)) },
                label = { Text("Teléfono") },
                leadingIcon = { Icon(Icons.Default.Phone, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Calle Editable
            OutlinedTextField(
                value = vm.calleEdit,
                onValueChange = { vm.onEvent(AjustesEvent.OnCalleChange(it)) },
                label = { Text("Dirección (Calle)") },
                leadingIcon = { Icon(Icons.Default.Home, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Botón Guardar
            Button(
                onClick = { vm.onEvent(AjustesEvent.OnGuardarPerfil) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppPrimary, contentColor = AppTextPrimary)
            ) {
                Text("Guardar Cambios", fontWeight = FontWeight.Bold)
            }
        }
    }
}