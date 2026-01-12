package com.pmdm.appV1.ui.features.Ajustes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email // Usamos Email que suele venir en el core
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera // Requiere librería Extended
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// --- COLORES (Coherencia con Ajustes) ---
private val AppPrimary = Color(0xFFF2F20D)
private val AppBackground = Color(0xFFF8F8F5) // Background-light del HTML
private val AppSurface = Color(0xFFFFFFFF)
private val AppTextPrimary = Color(0xFF1C1C0D)
private val AppTextSecondary = Color(0xFF57534E) // Stone-600 approx
private val AppBorder = Color(0xFFE5E7EB) // Stone-200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    // Estados del formulario
    var name by remember { mutableStateOf("Sofia Rodriguez") }
    var email by remember { mutableStateOf("sofia.rod@example.com") }
    var password by remember { mutableStateOf("Password123") }

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Editar Perfil",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = AppTextPrimary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = AppTextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppBackground
                )
            )
        },
        // Botones fijos en la parte inferior (Footer)
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppBackground)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppPrimary,
                        contentColor = AppTextPrimary
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Guardar Cambios", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- SECCIÓN FOTO ---
            Spacer(modifier = Modifier.height(16.dp))
            ProfilePhotoSection()

            Spacer(modifier = Modifier.height(32.dp))

            // --- FORMULARIO ---
            // Nombre
            CustomTextField(
                label = "Nombre de usuario",
                value = name,
                onValueChange = { name = it },
                icon = Icons.Default.Person,
                placeholder = "Tu nombre"
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Email
            CustomTextField(
                label = "Correo electrónico",
                value = email,
                onValueChange = { email = it },
                icon = Icons.Default.Email,
                placeholder = "tu@email.com",
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Contraseña (Input especial solo lectura o edición)
            CustomTextField(
                label = "Contraseña",
                value = password,
                onValueChange = { password = it },
                icon = Icons.Default.Lock,
                isPassword = true,
                // El diseño muestra un lápiz al final del input de password
                trailingIcon = {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFF5F5F4), CircleShape) // Stone-100
                            .clickable { /* Lógica cambiar pass */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            modifier = Modifier.size(16.dp),
                            tint = AppPrimary.copy(alpha = 0.8f).compositeOver(Color.Black) // Un amarillo oscurecido o marrón
                        )
                    }
                }
            )

            // Espacio extra al final para que el scroll no quede pegado
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

// ------------------------------------------
// COMPONENTES AUXILIARES
// ------------------------------------------

@Composable
fun ProfilePhotoSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.BottomEnd) {
            // Foto Circular
            // TODO: Reemplazar el Box gris por AsyncImage(model = url)
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray) // Placeholder
                    .border(4.dp, Color.White, CircleShape)
            ) {
                // Simulación de imagen
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    tint = Color.White
                )
            }

            // FAB Cámara
            Box(
                modifier = Modifier
                    .offset(x = (-4).dp, y = (-4).dp)
                    .size(40.dp)
                    .background(AppPrimary, CircleShape)
                    .border(3.dp, AppBackground, CircleShape)
                    .clickable { /* Cambiar foto */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Cambiar foto",
                    modifier = Modifier.size(20.dp),
                    tint = AppTextPrimary
                )
            }
        }

        TextButton(onClick = { /* Cambiar foto */ }) {
            Text(
                text = "Cambiar foto",
                color = Color(0xFFD9D90C), // Un amarillo un poco más oscuro para texto
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String = "",
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Etiqueta fuera del input (estilo UI Kit)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = AppTextSecondary
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
        )

        // Campo de Texto
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppSurface,
                unfocusedContainerColor = AppSurface,
                disabledContainerColor = AppSurface,
                focusedBorderColor = AppPrimary,
                unfocusedBorderColor = AppBorder,
                cursorColor = AppPrimary,
                focusedTextColor = AppTextPrimary,
                unfocusedTextColor = AppTextPrimary
            ),
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.LightGray
                )
            },
            trailingIcon = trailingIcon,
            placeholder = {
                Text(text = placeholder, color = Color.LightGray)
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilPreview() {
    EditarPerfilScreen()
}