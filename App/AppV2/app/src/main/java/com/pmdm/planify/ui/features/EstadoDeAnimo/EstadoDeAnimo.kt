package com.pmdm.planify.ui.features.EstadoDeAnimo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter     // Requiere Material Icons Extended
import androidx.compose.material.icons.filled.Mood              // Requiere Material Icons Extended
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Payments          // Requiere Material Icons Extended
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- COLORES EXACTOS (Extraídos del HTML/Imagen) ---
private val AppPrimary = Color(0xFFF9F506)      // Amarillo Neón Principal
private val AppBackground = Color(0xFFFFFFFF)
private val AppTextPrimary = Color(0xFF0F172A)  // Slate-900
private val AppTextSecondary = Color(0xFF94A3B8)// Slate-400
private val AppGreenText = Color(0xFF16A34A)    // Green-600
private val AppSurfaceColor = Color(0xFFFFFFFF)

// Colores de Estados de Ánimo
private val MoodGreatBg = Color(0xFFFEF08A) // Amarillo pastel fuerte
private val MoodGoodBg = Color(0xFFFEF9C3)  // Amarillo muy pálido
private val MoodSadBg = Color(0xFFFFEDD5)   // Naranja pálido
private val MoodAngryBg = Color(0xFFFEE2E2) // Rojo pálido

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstadoDeAnimoScreen(
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Estado de Ánimo",
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
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir",
                            tint = AppTextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppBackground
                )
            )
        },
        bottomBar = {
            BottomNavBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción Nuevo Registro */ },
                containerColor = AppPrimary,
                contentColor = AppTextPrimary,
                shape = CircleShape,
                modifier = Modifier
                    .size(64.dp)
                    .offset(y = 40.dp) // Truco visual para que flote un poco sobre el nav bar si es necesario
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 120.dp) // Espacio extra inferior para el FAB y Nav
        ) {

            // --- HEADER DEL MES ---
            MonthSelectorSection()

            Spacer(modifier = Modifier.height(24.dp))

            // --- CALENDARIO (GRID 7x5) ---
            CalendarGridSection()

            Spacer(modifier = Modifier.height(32.dp))

            // --- TÍTULO RESUMEN ---
            Text(
                text = "Resumen Mensual",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = AppTextPrimary
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- TARJETA DE RESUMEN ---
            SummaryCardSection()
        }
    }
}

// ------------------------------------------
// SECCIÓN 1: SELECTOR DE MES
// ------------------------------------------
@Composable
fun MonthSelectorSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón Izquierda
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFFF8FAFC), CircleShape) // Slate-50
        ) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, null, tint = AppTextPrimary)
        }

        // Texto Central
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Octubre 2023",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Text(
                text = "31 REGISTROS",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = AppTextSecondary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            )
        }

        // Botón Derecha
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFFF8FAFC), CircleShape)
        ) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = AppTextPrimary)
        }
    }
}

// ------------------------------------------
// SECCIÓN 2: CALENDARIO GRID
// ------------------------------------------
@Composable
fun CalendarGridSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        // Cabecera: Días de la semana
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            val days = listOf("DOM", "LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB")
            days.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFCBD5E1), // Slate-300
                        fontSize = 11.sp
                    )
                )
            }
        }

        // Datos simulados para el mes (31 días comenzando en Domingo)
        // Patrón visual aproximado a la imagen
        val moodData = remember {
            listOf(
                MoodDay(1, "🤩", MoodGreatBg), MoodDay(2, "😊", MoodGoodBg), MoodDay(3, "😔", MoodSadBg),
                MoodDay(4, "😊", MoodGoodBg), MoodDay(5, "🤩", MoodGreatBg, isSelected = true), MoodDay(6, "😊", MoodGoodBg), MoodDay(7, "😡", MoodAngryBg),
                MoodDay(8, "😊", MoodGoodBg), MoodDay(9, "🤩", MoodGreatBg), MoodDay(10, "😔", MoodSadBg),
                MoodDay(11, "😊", MoodGoodBg), MoodDay(12, "😊", MoodGoodBg), MoodDay(13, "😡", MoodAngryBg), MoodDay(14, "🤩", MoodGreatBg),
                MoodDay(15, "😊", MoodGoodBg), MoodDay(16, "😔", MoodSadBg), MoodDay(17, "🤩", MoodGreatBg),
                MoodDay(18, "😊", MoodGoodBg), MoodDay(19, "😊", MoodGoodBg), MoodDay(20, "🤩", MoodGreatBg), MoodDay(21, "😔", MoodSadBg),
                MoodDay(22, "😊", MoodGoodBg), MoodDay(23, "🤩", MoodGreatBg), MoodDay(24, "😊", MoodGoodBg),
                MoodDay(25, "😔", MoodSadBg), MoodDay(26, "😔", MoodSadBg), MoodDay(27, "😊", MoodGoodBg), MoodDay(28, "🤩", MoodGreatBg),
                MoodDay(29, "😊", MoodGoodBg), MoodDay(30, "😊", MoodGoodBg), MoodDay(31, "🤩", MoodGreatBg)
            )
        }

        // Renderizado del Grid (Filas de 7)
        moodData.chunked(7).forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                // Rellenar días de la semana
                for (i in 0 until 7) {
                    if (i < week.size) {
                        val day = week[i]
                        MoodDayItem(day, Modifier.weight(1f))
                    } else {
                        Spacer(modifier = Modifier.weight(1f)) // Espacio vacío si el mes termina
                    }
                }
            }
        }
    }
}

data class MoodDay(val number: Int, val emoji: String, val color: Color, val isSelected: Boolean = false)

@Composable
fun MoodDayItem(day: MoodDay, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(42.dp) // Tamaño del círculo
                // Borde condicional si está seleccionado (Día 5)
                .then(
                    if (day.isSelected) Modifier.border(2.5.dp, AppPrimary, CircleShape).padding(2.dp)
                    else Modifier
                )
                .background(day.color, CircleShape)
                .then(
                    // Sombra suave solo si es un día destacado "Great" o seleccionado
                    if (day.isSelected || day.emoji == "🤩") Modifier.shadow(2.dp, CircleShape, spotColor = day.color)
                    else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = day.emoji, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = day.number.toString(),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 10.sp,
                fontWeight = if (day.isSelected) FontWeight.Black else FontWeight.Medium,
                color = if (day.isSelected) AppTextPrimary else AppTextSecondary
            )
        )
    }
}

// ------------------------------------------
// SECCIÓN 3: CARD RESUMEN
// ------------------------------------------
@Composable
fun SummaryCardSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // 1. Header del Card (Cara Grande + Texto)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(AppPrimary, CircleShape)
                        .shadow(4.dp, CircleShape, spotColor = AppPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🙂", fontSize = 32.sp) // Cara sonriente
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "ÁNIMO PROMEDIO",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = AppTextSecondary,
                            letterSpacing = 1.sp
                        )
                    )
                    Text(
                        text = "Mayormente Bien",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = AppTextPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Texto Descriptivo con Span Verde
            Text(
                text = buildAnnotatedString {
                    append("¡Excelente progreso! Has tenido un ")
                    withStyle(style = SpanStyle(color = AppGreenText, fontWeight = FontWeight.Bold)) {
                        append("12% más de días positivos")
                    }
                    append(" que el mes pasado. Tu ánimo se mantiene estable.")
                },
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF475569), // Slate-600
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Barra de Progreso (Estabilidad)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ESTABILIDAD ANÍMICA",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = AppTextSecondary
                    )
                )
                Text(
                    text = "84%",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = AppTextPrimary
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color(0xFFF1F5F9))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.84f)
                        .fillMaxHeight()
                        .background(AppPrimary)
                        .shadow(4.dp, shape = RoundedCornerShape(100))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Gráfica de Barras (Distribución)
            Text(
                text = "DISTRIBUCIÓN POR ESTADOS",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = AppTextSecondary
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp), // Altura total de la zona de gráfica
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                // Barra 1: 🙂 (65%)
                MoodBar(fraction = 0.65f, color = AppPrimary, emoji = "🙂")
                // Barra 2: 🤩 (85%) - Un poco más oscuro
                MoodBar(fraction = 0.85f, color = Color(0xFFD1CD00), emoji = "🤩")
                // Barra 3: 😔 (35%)
                MoodBar(fraction = 0.35f, color = Color(0xFFFFEDD5), emoji = "😔")
                // Barra 4: 😡 (15%)
                MoodBar(fraction = 0.15f, color = Color(0xFFFECACA), emoji = "😵")
            }
        }
    }
}

@Composable
fun RowScope.MoodBar(fraction: Float, color: Color, emoji: String) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // La barra visual
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction) // Altura dinámica
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = emoji, fontSize = 18.sp)
    }
}

// ------------------------------------------
// BOTTOM NAVIGATION CUSTOM
// ------------------------------------------
@Composable
fun BottomNavBar() {
    NavigationBar(
        containerColor = Color.White.copy(alpha = 0.98f),
        tonalElevation = 10.dp,
        modifier = Modifier.height(80.dp)
    ) {
        // Item 1
        NavigationBarItem(
            icon = { Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Tareas", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = AppTextSecondary.copy(alpha = 0.4f),
                unselectedTextColor = AppTextSecondary.copy(alpha = 0.4f),
                indicatorColor = Color.Transparent
            )
        )
        // Item 2
        NavigationBarItem(
            icon = { Icon(Icons.Default.FitnessCenter, contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Gimnasio", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = AppTextSecondary.copy(alpha = 0.4f),
                unselectedTextColor = AppTextSecondary.copy(alpha = 0.4f),
                indicatorColor = Color.Transparent
            )
        )
        // Item 3 (Seleccionado)
        NavigationBarItem(
            icon = { Icon(Icons.Default.Mood, contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Ánimo", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = true,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppTextPrimary, // Negro puro
                selectedTextColor = AppTextPrimary,
                indicatorColor = Color.Transparent // Sin burbuja de fondo
            )
        )
        // Item 4
        NavigationBarItem(
            icon = { Icon(Icons.Default.Payments, contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Gastos", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = AppTextSecondary.copy(alpha = 0.4f),
                unselectedTextColor = AppTextSecondary.copy(alpha = 0.4f),
                indicatorColor = Color.Transparent
            )
        )
        // Item 5
        NavigationBarItem(
            icon = { Icon(Icons.Default.MoreHoriz, contentDescription = null, modifier = Modifier.size(24.dp)) },
            label = { Text("Más", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = AppTextSecondary.copy(alpha = 0.4f),
                unselectedTextColor = AppTextSecondary.copy(alpha = 0.4f),
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true, heightDp = 1100)
@Composable
fun EstadoDeAnimoPreview() {
    EstadoDeAnimoScreen()
}