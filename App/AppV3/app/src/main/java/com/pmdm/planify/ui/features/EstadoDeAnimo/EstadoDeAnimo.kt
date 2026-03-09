package com.pmdm.planify.ui.features.Mood // Ajusta tu package

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader
import com.pmdm.planify.ui.features.EstadoDeAnimo.EstadoAnimoVM
import java.time.LocalDate

// --- COLORES DEL TEMA ---
private val AppPrimary = Color(0xFFF9F506)
private val AppBackground = Color(0xFFFFFFFF)
private val AppTextPrimary = Color(0xFF0F172A)
private val AppTextSecondary = Color(0xFF94A3B8)
private val AppGreenText = Color(0xFF16A34A)

// Colores de Estados de Ánimo para el Calendario
private val MoodGreatBg = Color(0xFFFEF08A)
private val MoodGoodBg = Color(0xFFFEF9C3)
private val MoodNormalBg = Color(0xFFF1F5F9)
private val MoodSadBg = Color(0xFFFFEDD5)
private val MoodAngryBg = Color(0xFFFEE2E2)

// --- 1. COMPONENTE CON ESTADO ---
@Composable
fun EstadoDeAnimoScreen(
    navController: NavHostController,
    vm: EstadoAnimoVM // Inyectado vía hiltViewModel() en el NavHost
) {
    EstadoDeAnimoContent(
        navController = navController,
        historial = vm.estadoAnimo.registroAnimo
    )
}

// --- 2. COMPONENTE SIN ESTADO (UI Pura) ---
@Composable
fun EstadoDeAnimoContent(
    navController: NavHostController,
    historial: Map<LocalDate, IconoEstadoAnimo>
) {
    Scaffold(
        containerColor = AppBackground,
        bottomBar = { PlanifyBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            // HEADER CENTRALIZADO
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                PlanifyHeader(
                    nombreUsuario = "Andrea",
                    fraseBienvenida = "Tu equilibrio",
                    onProfileClick = { /* navController.navigate(SettingsRoute) */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            MonthSelectorSection()

            Spacer(modifier = Modifier.height(24.dp))

            CalendarGridSection(historial = historial)

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Resumen Mensual",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = AppTextPrimary
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SummaryCardSection()
        }
    }
}

// --- SECCIONES INTERNAS ---

@Composable
fun MonthSelectorSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}, modifier = Modifier.size(40.dp).background(Color(0xFFF8FAFC), CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, null, tint = AppTextPrimary)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Octubre 2023", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text(text = "31 REGISTROS", style = MaterialTheme.typography.labelSmall.copy(color = AppTextSecondary, letterSpacing = 1.sp))
        }
        IconButton(onClick = {}, modifier = Modifier.size(40.dp).background(Color(0xFFF8FAFC), CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = AppTextPrimary)
        }
    }
}

@Composable
fun CalendarGridSection(historial: Map<LocalDate, IconoEstadoAnimo>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            listOf("DOM", "LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB").forEach { day ->
                Text(text = day, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFCBD5E1)))
            }
        }

        // Datos dummy para rellenar el calendario visualmente
        val dummyData = (1..31).map { MoodDay(it, "😊", MoodGoodBg) }
        val today = LocalDate.now()

        val mergedData = dummyData.map { dummyDay ->
            val dateForDay = try { today.withDayOfMonth(dummyDay.number) } catch (e: Exception) { null }
            val realMood = dateForDay?.let { historial[it] }

            if (realMood != null) {
                val (emoji, color) = getEmojiAndColor(realMood)
                MoodDay(dummyDay.number, emoji, color, isSelected = (dummyDay.number == today.dayOfMonth))
            } else {
                dummyDay.copy(isSelected = (dummyDay.number == today.dayOfMonth))
            }
        }

        mergedData.chunked(7).forEach { week ->
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                week.forEach { MoodDayItem(it, Modifier.weight(1f)) }
                // Rellenar espacios vacíos si la semana tiene menos de 7 días
                if (week.size < 7) { repeat(7 - week.size) { Spacer(modifier = Modifier.weight(1f)) } }
            }
        }
    }
}

@Composable
fun SummaryCardSection() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(56.dp).background(AppPrimary, CircleShape), contentAlignment = Alignment.Center) {
                    Text("🙂", fontSize = 28.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("PROMEDIO MENSUAL", style = MaterialTheme.typography.labelSmall, color = AppTextSecondary)
                    Text("Mayormente Bien", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    append("Has tenido un ")
                    withStyle(style = SpanStyle(color = AppGreenText, fontWeight = FontWeight.Bold)) { append("12% más de días positivos") }
                    append(" que el mes pasado.")
                },
                style = MaterialTheme.typography.bodyMedium, color = Color(0xFF475569)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("DISTRIBUCIÓN", style = MaterialTheme.typography.labelSmall, color = AppTextSecondary)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth().height(80.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Bottom) {
                MoodBar(0.85f, Color(0xFFFDE047), "🤩")
                MoodBar(0.65f, AppPrimary, "😊")
                MoodBar(0.35f, Color(0xFFE2E8F0), "😐")
                MoodBar(0.15f, Color(0xFFFECACA), "😔")
            }
        }
    }
}

// --- AUXILIARES ---

data class MoodDay(val number: Int, val emoji: String, val color: Color, val isSelected: Boolean = false)

@Composable
fun MoodDayItem(day: MoodDay, modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(day.color, CircleShape)
                .then(if (day.isSelected) Modifier.border(2.dp, AppTextPrimary, CircleShape) else Modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(text = day.emoji, fontSize = 18.sp)
        }
        Text(text = day.number.toString(), style = MaterialTheme.typography.labelSmall, color = AppTextSecondary)
    }
}

@Composable
fun RowScope.MoodBar(fraction: Float, color: Color, emoji: String) {
    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction).clip(RoundedCornerShape(4.dp)).background(color))
        Text(text = emoji, fontSize = 16.sp)
    }
}

fun getEmojiAndColor(mood: IconoEstadoAnimo): Pair<String, Color> {
    return when (mood) {
        IconoEstadoAnimo.GENIAL -> Pair("🤩", MoodGreatBg)
        IconoEstadoAnimo.BIEN -> Pair("😊", MoodGoodBg)
        IconoEstadoAnimo.NORMAL -> Pair("😐", MoodNormalBg)
        IconoEstadoAnimo.MAL -> Pair("😔", MoodSadBg)
        IconoEstadoAnimo.MUYMAL -> Pair("😡", MoodAngryBg)
    }
}

@Preview(showBackground = true)
@Composable
fun MoodPreview() {
    EstadoDeAnimoContent(rememberNavController(), emptyMap())
}