package com.pmdm.planify.ui.features.EstadoDeAnimo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.ui.features.VentanaPrincipal.DashboardBottomBar
import com.pmdm.planify.ui.features.VentanaPrincipal.HeaderSection
import java.time.LocalDate

// --- COLORES EXACTOS ---
private val AppPrimary = Color(0xFFF9F506)
private val AppBackground = Color(0xFFFFFFFF)
private val AppTextPrimary = Color(0xFF0F172A)
private val AppTextSecondary = Color(0xFF94A3B8)
private val AppGreenText = Color(0xFF16A34A)

// Colores de Estados de Ánimo
private val MoodGreatBg = Color(0xFFFEF08A)
private val MoodGoodBg = Color(0xFFFEF9C3)
private val MoodNormalBg = Color(0xFFF1F5F9)
private val MoodSadBg = Color(0xFFFFEDD5)
private val MoodAngryBg = Color(0xFFFEE2E2)

// 1. COMPONENTE CON ESTADO (Conecta con el ViewModel)
@Composable
fun EstadoDeAnimoScreen(vm: EstadoAnimoVM) {
    // Simplemente pasamos el historial, la lógica de añadir se hace en el Home
    EstadoDeAnimoContent(
        historial = vm.estadoAnimo.registroAnimo
    )
}

// 2. COMPONENTE SIN ESTADO (UI Pura)
@Composable
fun EstadoDeAnimoContent(
    historial: Map<LocalDate, IconoEstadoAnimo>
) {
    Scaffold(
        containerColor = AppBackground,
        bottomBar = {
            DashboardBottomBar(itemSeleccionado = "Ánimo")
        }
        // FAB ELIMINADO: El registro se hace desde la Ventana Principal
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 40.dp)
        ) {

            // --- HEADER DE HOME ---
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                HeaderSection()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- HEADER DEL MES ---
            MonthSelectorSection()

            Spacer(modifier = Modifier.height(24.dp))

            // --- CALENDARIO (Linkado a datos reales) ---
            CalendarGridSection(historial = historial)

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
        IconButton(onClick = {}, modifier = Modifier.size(44.dp).background(Color(0xFFF8FAFC), CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, null, tint = AppTextPrimary)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Octubre 2023", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp))
            Text(text = "31 REGISTROS", style = MaterialTheme.typography.labelSmall.copy(color = AppTextSecondary, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp))
        }
        IconButton(onClick = {}, modifier = Modifier.size(44.dp).background(Color(0xFFF8FAFC), CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = AppTextPrimary)
        }
    }
}

// ------------------------------------------
// SECCIÓN 2: CALENDARIO GRID
// ------------------------------------------
@Composable
fun CalendarGridSection(historial: Map<LocalDate, IconoEstadoAnimo>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        // Cabecera: Días de la semana
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            listOf("DOM", "LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB").forEach { day ->
                Text(text = day, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Color(0xFFCBD5E1), fontSize = 11.sp))
            }
        }

        // Tus datos simulados originales para rellenar visualmente el mes
        val dummyData = listOf(
            MoodDay(1, "🤩", MoodGreatBg), MoodDay(2, "😊", MoodGoodBg), MoodDay(3, "😔", MoodSadBg),
            MoodDay(4, "😊", MoodGoodBg), MoodDay(5, "🤩", MoodGreatBg), MoodDay(6, "😊", MoodGoodBg), MoodDay(7, "😡", MoodAngryBg),
            MoodDay(8, "😊", MoodGoodBg), MoodDay(9, "🤩", MoodGreatBg), MoodDay(10, "😔", MoodSadBg),
            MoodDay(11, "😊", MoodGoodBg), MoodDay(12, "😊", MoodGoodBg), MoodDay(13, "😡", MoodAngryBg), MoodDay(14, "🤩", MoodGreatBg),
            MoodDay(15, "😊", MoodGoodBg), MoodDay(16, "😔", MoodSadBg), MoodDay(17, "🤩", MoodGreatBg),
            MoodDay(18, "😊", MoodGoodBg), MoodDay(19, "😊", MoodGoodBg), MoodDay(20, "🤩", MoodGreatBg), MoodDay(21, "😔", MoodSadBg),
            MoodDay(22, "😊", MoodGoodBg), MoodDay(23, "🤩", MoodGreatBg), MoodDay(24, "😊", MoodGoodBg),
            MoodDay(25, "😔", MoodSadBg), MoodDay(26, "😔", MoodSadBg), MoodDay(27, "😊", MoodGoodBg), MoodDay(28, "🤩", MoodGreatBg),
            MoodDay(29, "😊", MoodGoodBg), MoodDay(30, "😊", MoodGoodBg), MoodDay(31, "🤩", MoodGreatBg)
        )

        val today = LocalDate.now()

        // Fusionamos los datos visuales falsos con el historial real del ViewModel
        val mergedData = dummyData.map { dummyDay ->
            val dateForDay = try { today.withDayOfMonth(dummyDay.number) } catch (e: Exception) { null }
            val realMood = dateForDay?.let { historial[it] }

            if (realMood != null) {
                // Si el usuario guardó un estado real para ese día, reemplazamos el visual
                val (emoji, color) = getEmojiAndColor(realMood)
                MoodDay(dummyDay.number, emoji, color, isSelected = (dummyDay.number == today.dayOfMonth))
            } else {
                // Si no, mostramos el dato de prueba y verificamos si es hoy
                dummyDay.copy(isSelected = (dummyDay.number == today.dayOfMonth))
            }
        }

        // Renderizado del Grid (Filas de 7)
        mergedData.chunked(7).forEach { week ->
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                for (i in 0 until 7) {
                    if (i < week.size) {
                        MoodDayItem(week[i], Modifier.weight(1f))
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

// ------------------------------------------
// SECCIÓN 3: CARD RESUMEN
// ------------------------------------------
@Composable
fun SummaryCardSection() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(64.dp).background(AppPrimary, CircleShape).shadow(4.dp, CircleShape, spotColor = AppPrimary), contentAlignment = Alignment.Center) {
                    Text("🙂", fontSize = 32.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("ÁNIMO PROMEDIO", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AppTextSecondary, letterSpacing = 1.sp))
                    Text("Mayormente Bien", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold, color = AppTextPrimary))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    append("¡Excelente progreso! Has tenido un ")
                    withStyle(style = SpanStyle(color = AppGreenText, fontWeight = FontWeight.Bold)) { append("12% más de días positivos") }
                    append(" que el mes pasado. Tu ánimo se mantiene estable.")
                },
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF475569), lineHeight = 22.sp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("ESTABILIDAD ANÍMICA", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AppTextSecondary))
                Text("84%", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AppTextPrimary))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(100)).background(Color(0xFFF1F5F9))) {
                Box(modifier = Modifier.fillMaxWidth(0.84f).fillMaxHeight().background(AppPrimary).shadow(4.dp, shape = RoundedCornerShape(100)))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("DISTRIBUCIÓN POR ESTADOS", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AppTextSecondary), modifier = Modifier.padding(bottom = 16.dp))

            Row(modifier = Modifier.fillMaxWidth().height(90.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Bottom) {
                MoodBar(fraction = 0.65f, color = AppPrimary, emoji = "🙂")
                MoodBar(fraction = 0.45f, color = Color(0xFFD1CD00), emoji = "🤩")
                MoodBar(fraction = 0.35f, color = Color(0xFFFFEDD5), emoji = "😔")
                MoodBar(fraction = 0.15f, color = Color(0xFFFECACA), emoji = "😵")
            }
        }
    }
}

// ------------------------------------------
// COMPONENTES UI AUXILIARES
// ------------------------------------------

data class MoodDay(val number: Int, val emoji: String, val color: Color, val isSelected: Boolean = false)

@Composable
fun MoodDayItem(day: MoodDay, modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .then(if (day.isSelected) Modifier.border(2.5.dp, AppPrimary, CircleShape).padding(2.dp) else Modifier)
                .background(day.color, CircleShape)
                .then(if (day.isSelected || day.emoji == "🤩") Modifier.shadow(2.dp, CircleShape, spotColor = day.color) else Modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(text = day.emoji, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = day.number.toString(), style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp, fontWeight = if (day.isSelected) FontWeight.Black else FontWeight.Medium, color = if (day.isSelected) AppTextPrimary else AppTextSecondary))
    }
}

@Composable
fun RowScope.MoodBar(fraction: Float, color: Color, emoji: String) {
    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction).clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)).background(color))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = emoji, fontSize = 18.sp)
    }
}

// Función para mapear el Enum de la base de datos a los emojis visuales del calendario
fun getEmojiAndColor(mood: IconoEstadoAnimo): Pair<String, Color> {
    return when (mood) {
        IconoEstadoAnimo.GENIAL -> Pair("🤩", MoodGreatBg)
        IconoEstadoAnimo.BIEN -> Pair("😊", MoodGoodBg)
        IconoEstadoAnimo.NORMAL -> Pair("😐", MoodNormalBg)
        IconoEstadoAnimo.MAL -> Pair("😔", MoodSadBg)
        IconoEstadoAnimo.MUYMAL -> Pair("😡", MoodAngryBg)
    }
}

// PREVIEW
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EstadoDeAnimoPreview() {
    MaterialTheme {
        EstadoDeAnimoContent(
            historial = emptyMap() // Pasamos un mapa vacío, se rellenará con los datos dummy del calendario
        )
    }
}