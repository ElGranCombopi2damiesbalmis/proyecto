package com.pmdm.planify.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.planify.R
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.ui.features.Login.LoginViewModel

// Colores unificados
val PrimaryYellow = Color(0xFFFACC15)
val BackgroundWhite = Color(0xFFFFFFFF)
val TextGray = Color(0xFF64748B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(vm: LoginViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "¡Bienvenido de nuevo!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = "Introduce tus datos para continuar", fontSize = 14.sp, color = TextGray, modifier = Modifier.padding(top = 8.dp))

        Spacer(modifier = Modifier.height(40.dp))

        // Email conectado al ViewModel
        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.onEmailChanged(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = vm.errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryYellow,
                focusedLabelColor = Color.Black,
                cursorColor = PrimaryYellow,
                errorBorderColor = Color.Red
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña conectada al ViewModel
        OutlinedTextField(
            value = vm.password,
            onValueChange = { vm.onPasswordChanged(it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            isError = vm.errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryYellow,
                focusedLabelColor = Color.Black,
                cursorColor = PrimaryYellow,
                errorBorderColor = Color.Red
            )
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            TextButton(onClick = { /* TODO: Olvidé contraseña */ }) {
                Text("¿Has olvidado tu contraseña?", color = TextGray, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Iniciar Sesión usando la lógica del VM
        Button(
            onClick = { vm.onLoginClick() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow)
        ) {
            Text("Iniciar Sesión", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        // Mensaje de error dinámico
        vm.errorMessage?.let { msg ->
            Text(
                text = msg,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
            Text(" O continuar con ", color = TextGray, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp))
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SocialButton(text = "Google", iconRes = R.drawable.google_logo, modifier = Modifier.weight(1f))
            SocialButton(text = "Facebook", iconRes = R.drawable.fb_logo, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.padding(bottom = 32.dp)) {
            Text("¿No tienes una cuenta?", color = TextGray)
            Text(" Regístrate", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
        }
    }
}

@Composable
fun SocialButton(text: String, iconRes: Int, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { /* Acción Social Login */ },
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Logo $text",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    // Le pasamos el repositorio manualmente al constructor del VM para el Preview
    val mockRepository = UsuarioRepository()
    val mockVm = LoginViewModel(mockRepository)

    LoginScreen(vm = mockVm)
}