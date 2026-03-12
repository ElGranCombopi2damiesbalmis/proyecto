package com.pmdm.planify.ui

import android.accounts.AccountManager
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.pmdm.planify.R
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.ui.features.Login.LoginViewModel
private val PrimaryYellow   = Color(0xFFFACC15)
private val BackgroundWhite = Color(0xFFFFFFFF)
private val TextGray        = Color(0xFF64748B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(vm: LoginViewModel) {

    val googleAccountLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val email = result.data?.getStringExtra(AccountManager.KEY_ACCOUNT_NAME) ?: return@rememberLauncherForActivityResult
            vm.onGoogleAccountSelected(email)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundWhite).padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(60.dp))
        Text("¡Bienvenido de nuevo!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text("Introduce tus datos para continuar", fontSize = 14.sp, color = TextGray, modifier = Modifier.padding(top = 8.dp))
        Spacer(Modifier.height(40.dp))

        OutlinedTextField(
            value = vm.email, onValueChange = { vm.onEmailChanged(it) },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = vm.errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryYellow, focusedLabelColor = Color.Black, cursorColor = PrimaryYellow, errorBorderColor = Color.Red)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = vm.password, onValueChange = { vm.onPasswordChanged(it) },
            label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp),
            isError = vm.errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryYellow, focusedLabelColor = Color.Black, cursorColor = PrimaryYellow, errorBorderColor = Color.Red)
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            TextButton(onClick = { }) { Text("¿Has olvidado tu contraseña?", color = TextGray, fontSize = 12.sp) }
        }
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { vm.onLoginClick() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow)
        ) { Text("Iniciar Sesión", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp) }

        vm.errorMessage?.let { Text(it, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp)) }

        Spacer(Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
            Text(" O continuar con ", color = TextGray, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp))
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
        }
        Spacer(Modifier.height(24.dp))

        OutlinedButton(
            onClick = {
                val intent = AccountManager.newChooseAccountIntent(null, null, arrayOf("com.google"), null, null, null, null)
                googleAccountLauncher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
        ) {
            Icon(painter = painterResource(id = R.drawable.google_logo), contentDescription = "Google", modifier = Modifier.size(22.dp), tint = Color.Unspecified)
            Spacer(Modifier.width(12.dp))
            Text("Continuar con Google", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.weight(1f))
        Row(modifier = Modifier.padding(bottom = 32.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("¿No tienes una cuenta?", color = TextGray)
            TextButton(onClick = { vm.abrirDialogoRegistro() }, contentPadding = PaddingValues(start = 4.dp)) {
                Text("Regístrate", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }

    if (vm.mostrarDialogoRegistro) { RegistroDialog(vm = vm) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroDialog(vm: LoginViewModel) {
    var verPassword        by remember { mutableStateOf(false) }
    var verPasswordConfirm by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { vm.cerrarDialogoRegistro() }) {
        Card(shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text("Crear cuenta", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text("Rellena los datos para registrarte", fontSize = 13.sp, color = TextGray)

                // Nombre
                OutlinedTextField(
                    value = vm.registroNombre, onValueChange = { vm.onRegistroNombreChange(it) },
                    label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp), isError = vm.registroError != null,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryYellow, focusedLabelColor = Color.Black, cursorColor = PrimaryYellow)
                )
                // Email
                OutlinedTextField(
                    value = vm.registroEmail, onValueChange = { vm.onRegistroEmailChange(it) },
                    label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = vm.registroError != null,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryYellow, focusedLabelColor = Color.Black, cursorColor = PrimaryYellow)
                )
                // Contraseña
                OutlinedTextField(
                    value = vm.registroPassword, onValueChange = { vm.onRegistroPasswordChange(it) },
                    label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = if (verPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = { IconButton(onClick = { verPassword = !verPassword }) { Icon(if (verPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility, null, tint = TextGray) } },
                    isError = vm.registroError != null,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryYellow, focusedLabelColor = Color.Black, cursorColor = PrimaryYellow)
                )
                // Confirmar contraseña
                OutlinedTextField(
                    value = vm.registroPasswordConfirm, onValueChange = { vm.onRegistroPasswordConfirmChange(it) },
                    label = { Text("Confirmar contraseña") }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = if (verPasswordConfirm) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = { IconButton(onClick = { verPasswordConfirm = !verPasswordConfirm }) { Icon(if (verPasswordConfirm) Icons.Default.VisibilityOff else Icons.Default.Visibility, null, tint = TextGray) } },
                    isError = vm.registroError != null,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryYellow, focusedLabelColor = Color.Black, cursorColor = PrimaryYellow)
                )

                vm.registroError?.let { Text(it, color = Color.Red, fontSize = 12.sp) }

                if (vm.registroExito) {
                    Text("✅ ¡Cuenta creada! Ya puedes iniciar sesión.", color = Color(0xFF16A34A), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Button(onClick = { vm.cerrarDialogoRegistro() }, modifier = Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow)) {
                        Text("Cerrar", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedButton(onClick = { vm.cerrarDialogoRegistro() }, modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(12.dp)) { Text("Cancelar", color = Color.Black) }
                        Button(onClick = { vm.onRegistrarClick() }, modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow)) {
                            Text("Registrarse", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}