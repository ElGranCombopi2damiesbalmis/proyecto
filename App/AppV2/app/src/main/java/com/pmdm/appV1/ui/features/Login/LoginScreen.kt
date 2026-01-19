package com.pmdm.appV1.ui

import androidx.compose.foundation.Image
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
import com.pmdm.appV1.data.LoginRepository
import com.pmdm.appV1.R

// Colores basados en tu diseño
val PrimaryYellow = Color(0xFFFACC15)
val BackgroundWhite = Color(0xFFFFFFFF)
val TextGray = Color(0xFF64748B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val repository = remember { LoginRepository() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Título Principal
        Text(
            text = "¡Bienvenido de nuevo!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Introduce tus datos para continuar",
            fontSize = 14.sp,
            color = TextGray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryYellow,
                focusedLabelColor = Color.Black,
                cursorColor = PrimaryYellow
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryYellow,
                focusedLabelColor = Color.Black,
                cursorColor = PrimaryYellow
            )
        )

        // Olvidé mi contraseña
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            TextButton(onClick = { /* TODO */ }) {
                Text("¿Has olvidado tu contraseña?", color = TextGray, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Iniciar Sesión (AMARILLO)
        Button(
            onClick = {
                val user = repository.autenticar(email, password)
                if (user != null) {
                    onLoginSuccess()
                } else {
                    errorMessage = "Email o contraseña incorrectos"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow)
        ) {
            Text("Iniciar Sesión", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        if (errorMessage != null) {
            Text(errorMessage!!, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Divisor "O continuar con"
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
            Text(" O continuar con ", color = TextGray, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp))
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones Redes Sociales (Blancos con borde)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SocialButton(
                text = "Google",
                iconRes = R.drawable.google_logo, // Asegúrate de tener el recurso o usa un Icon genérico
                modifier = Modifier.weight(1f)
            )
            SocialButton(
                text = "Facebook",
                iconRes = R.drawable.fb_logo,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Registro
        Row(modifier = Modifier.padding(bottom = 32.dp)) {
            Text("¿No tienes una cuenta?", color = TextGray)
            Text(
                " Regístrate",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun SocialButton(
    text: String,
    iconRes: Int, // Cambiamos el tipo a Int para recibir el R.drawable
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = { /* Acción */ },
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Usamos Icon con painterResource para cargar el drawable
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Logo $text",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified // Importante: Unspecified para que el logo mantenga sus colores originales
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    // Usamos una lambda vacía para la navegación en la previa
    LoginScreen(onLoginSuccess = { })
}