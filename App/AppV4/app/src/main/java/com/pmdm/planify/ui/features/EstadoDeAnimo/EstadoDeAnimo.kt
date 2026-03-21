package com.pmdm.planify.ui.features.EstadoDeAnimo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material.icons.outlined.SentimentNeutral
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

// ── Colores ───────────────────────────────────────────────────────────────────
private val AppPrimary          = Color(0xFFF9F506)
private val AppBackground       = Color(0xFFFFFFFF)
private val AppTextPrimary      = Color(0xFF0F172A)
private val AppTextSecondary    = Color(0xFF94A3B8)
private val AppGreenText        = Color(0xFF16A34A)
private val MoodGreatBg         = Color(0xFFFEF08A)
private val MoodGoodBg          = Color(0xFFFEF9C3)
private val MoodNormalBg        = Color(0xFFF1F5F9)
private val MoodSadBg           = Color(0xFFFFEDD5)
private val MoodAngryBg         = Color(0xFFFEE2E2)
private val EmptyDayBg          = Color(0xFFF8FAFC)

// ── Helpers ───────────────────────────────────────────────────────────────────
fun getEmojiAndColor(mood: IconoEstadoAnimo): Pair<String, Color> = when (mood) {
    IconoEstadoAnimo.GENIAL  -> "🤩" to MoodGreatBg
    IconoEstadoAnimo.BIEN    -> "😊" to MoodGoodBg
    IconoEstadoAnimo.NORMAL  -> "😐" to MoodNormalBg
    IconoEstadoAnimo.MAL     -> "😔" to MoodSadBg
    IconoEstadoAnimo.MUYMAL  -> "😡" to MoodAngryBg
}

fun getMoodIcon(mood: IconoEstadoAnimo): ImageVector = when (mood) {
    IconoEstadoAnimo.GENIAL  -> Icons.Default.SentimentVerySatisfied
    IconoEstadoAnimo.BIEN    -> Icons.Default.SentimentSatisfied
    IconoEstadoAnimo.NORMAL  -> Icons.Outlined.SentimentNeutral
    IconoEstadoAnimo.MAL     -> Icons.Default.SentimentDissatisfied
    IconoEstadoAnimo.MUYMAL  -> Icons.Default.SentimentVeryDissatisfied
}

fun getMoodColor(mood: IconoEstadoAnimo): Color = when (mood) {
    IconoEstadoAnimo.GENIAL  -> Color(0xFFFDE047)
    IconoEstadoAnimo.BIEN    -> Color(0xFFF9F506)
    IconoEstadoAnimo.NORMAL  -> Color(0xFFE2E8F0)
    IconoEstadoAnimo.MAL     -> Color(0xFFFECACA)
    IconoEstadoAnimo.MUYMAL  -> Color(0xFFEF4444)
}

fun getMoodLabel(mood: IconoEstadoAnimo): String = when (mood) {
    IconoEstadoAnimo.GENIAL  -> "Genial"
    IconoEstadoAnimo.BIEN    -> "Bien"
    IconoEstadoAnimo.NORMAL  -> "Normal"
    IconoEstadoAnimo.MAL     -> "Mal"
    IconoEstadoAnimo.MUYMAL  -> "Muy mal"
}

// ── SCREEN ────────────────────────────────────────────────────────────────────
@Composable
fun EstadoDeAnimoScreen(navController: NavHostController, vm: EstadoAnimoVM) {
    // Día seleccionado para mostrar el picker de ánimo
    var diaSeleccionado by remember { mutableStateOf<LocalDate?>(null) }

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
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                PlanifyHeader(
                    nombreUsuario   = vm.nombreUsuario,
                    fraseBienvenida = "Tu equilibrio",
                    onProfileClick  = {}
                )
            }

            Spacer(Modifier.height(16.dp))

            // ── Selector de mes ───────────────────────────────────────────────
            MonthSelectorSection(
                mes          = vm.mesActual,
                totalRegistros = vm.totalRegistrosMes(),
                onAnterior   = { vm.mesAnterior() },
                onSiguiente  = { vm.mesSiguiente() }
            )

            Spacer(Modifier.height(24.dp))

            // ── Calendario ────────────────────────────────────────────────────
            CalendarGridSection(
                mes          = vm.mesActual,
                historial    = vm.estadoAnimo.registroAnimo,
                onDayClick   = { date -> diaSeleccionado = date }
            )

            Spacer(Modifier.height(32.dp))

            Text(
                "Resumen Mensual",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = AppTextPrimary),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.height(16.dp))

            // ── Resumen ───────────────────────────────────────────────────────
            SummaryCardSection(
                promedio     = vm.promedioMes(),
                distribucion = vm.distribucionMes()
            )
        }
    }

    // ── Diálogo selector de ánimo al pulsar un día ────────────────────────────
    diaSeleccionado?.let { fecha ->
        MoodPickerDialog(
            fecha        = fecha,
            moodActual   = vm.getMoodForDate(fecha),
            onSelectMood = { mood ->
                vm.onEvent(EstadoAnimoEvent.OnSelectMood(fecha, mood))
                diaSeleccionado = null
            },
            onDismiss    = { diaSeleccionado = null }
        )
    }
}

// ── SELECTOR DE MES ───────────────────────────────────────────────────────────
@Composable
fun MonthSelectorSection(mes: YearMonth, totalRegistros: Int, onAnterior: () -> Unit, onSiguiente: () -> Unit) {
    val mesNombre = mes.month.getDisplayName(TextStyle.FULL, Locale("es")).replaceFirstChar { it.uppercase() }
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        IconButton(onClick = onAnterior, modifier = Modifier.size(40.dp).background(Color(0xFFF8FAFC), CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, null, tint = AppTextPrimary)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$mesNombre ${mes.year}", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text("$totalRegistros REGISTROS", style = MaterialTheme.typography.labelSmall.copy(color = AppTextSecondary, letterSpacing = 1.sp))
        }
        IconButton(onClick = onSiguiente, modifier = Modifier.size(40.dp).background(Color(0xFFF8FAFC), CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = AppTextPrimary)
        }
    }
}

// ── CALENDARIO ────────────────────────────────────────────────────────────────
@Composable
fun CalendarGridSection(
    mes: YearMonth,
    historial: Map<LocalDate, IconoEstadoAnimo>,
    onDayClick: (LocalDate) -> Unit
) {
    val today          = LocalDate.now()
    val primerDia      = mes.atDay(1)
    // Offset: cuántas celdas vacías antes del día 1 (lunes=0 ... domingo=6)
    val offsetInicio   = (primerDia.dayOfWeek.value % 7) // Dom=0,Lun=1...Sab=6 → ajustamos a DOM=0
    val diasEnMes      = mes.lengthOfMonth()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        // Cabecera días de la semana
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            listOf("DOM", "LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB").forEach { day ->
                Text(day, modifier = Modifier.weight(1f), textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFCBD5E1)))
            }
        }

        // Construir lista de celdas: offsets vacíos + días del mes
        val celdas = buildList {
            repeat(offsetInicio) { add(null) }           // celdas vacías
            for (d in 1..diasEnMes) add(mes.atDay(d))   // días reales
        }

        celdas.chunked(7).forEach { semana ->
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                semana.forEach { fecha ->
                    if (fecha == null) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        val mood      = historial[fecha]
                        val isToday   = fecha == today
                        val (emoji, bgColor) = if (mood != null) getEmojiAndColor(mood) else ("" to EmptyDayBg)

                        Column(
                            modifier = Modifier.weight(1f).clickable { onDayClick(fecha) },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(bgColor, CircleShape)
                                    .then(if (isToday) Modifier.border(2.dp, AppTextPrimary, CircleShape) else Modifier),
                                contentAlignment = Alignment.Center
                            ) {
                                if (emoji.isNotEmpty()) Text(emoji, fontSize = 18.sp)
                                else Text(fecha.dayOfMonth.toString(), fontSize = 13.sp, color = AppTextSecondary, fontWeight = FontWeight.Medium)
                            }
                            Text(fecha.dayOfMonth.toString(), style = MaterialTheme.typography.labelSmall, color = AppTextSecondary)
                        }
                    }
                }
                // Rellenar última fila si tiene menos de 7
                repeat(7 - semana.size) { Spacer(modifier = Modifier.weight(1f)) }
            }
        }
    }
}

// ── RESUMEN ───────────────────────────────────────────────────────────────────
@Composable
fun SummaryCardSection(
    promedio: Pair<String, IconoEstadoAnimo?>,
    distribucion: Map<IconoEstadoAnimo, Int>
) {
    val (textoPromedio, iconoPromedio) = promedio
    val totalDias = distribucion.values.sum().takeIf { it > 0 } ?: 1

    Card(
        modifier  = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        shape     = RoundedCornerShape(24.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
        border    = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(56.dp).background(AppPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    val emoji = if (iconoPromedio != null) getEmojiAndColor(iconoPromedio).first else "📊"
                    Text(emoji, fontSize = 28.sp)
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("PROMEDIO MENSUAL", style = MaterialTheme.typography.labelSmall, color = AppTextSecondary)
                    Text(textoPromedio, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }

            if (distribucion.values.sum() > 0) {
                Spacer(Modifier.height(16.dp))
                val diasPositivos = (distribucion[IconoEstadoAnimo.GENIAL] ?: 0) + (distribucion[IconoEstadoAnimo.BIEN] ?: 0)
                Text(
                    text = buildAnnotatedString {
                        append("Has tenido ")
                        withStyle(SpanStyle(color = AppGreenText, fontWeight = FontWeight.Bold)) { append("$diasPositivos días positivos") }
                        append(" este mes.")
                    },
                    style = MaterialTheme.typography.bodyMedium, color = Color(0xFF475569)
                )

                Spacer(Modifier.height(24.dp))
                Text("DISTRIBUCIÓN", style = MaterialTheme.typography.labelSmall, color = AppTextSecondary)
                Spacer(Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth().height(80.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Bottom) {
                    IconoEstadoAnimo.entries.forEach { icono ->
                        val count   = distribucion[icono] ?: 0
                        val fraccion = count.toFloat() / totalDias.toFloat()
                        val (emoji, _) = getEmojiAndColor(icono)
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(fraccion.coerceAtLeast(0.05f)).clip(RoundedCornerShape(4.dp)).background(getMoodColor(icono)))
                            Text(emoji, fontSize = 16.sp)
                        }
                    }
                }
            } else {
                Spacer(Modifier.height(12.dp))
                Text("Registra tu ánimo en el calendario para ver el resumen.", color = AppTextSecondary, fontSize = 13.sp)
            }
        }
    }
}

// ── DIALOG SELECTOR DE ÁNIMO ──────────────────────────────────────────────────
@Composable
fun MoodPickerDialog(
    fecha: LocalDate,
    moodActual: IconoEstadoAnimo?,
    onSelectMood: (IconoEstadoAnimo) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("¿Cómo te sentiste?", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = AppTextPrimary)
                Text(
                    "${fecha.dayOfMonth} de ${fecha.month.getDisplayName(TextStyle.FULL,
                        Locale("es")
                    ).replaceFirstChar { it.uppercase() }}",
                    fontSize = 13.sp, color = AppTextSecondary, modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(Modifier.height(24.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    IconoEstadoAnimo.entries.forEach { icono ->
                        val (emoji, bgColor) = getEmojiAndColor(icono)
                        val isSelected       = moodActual == icono
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { onSelectMood(icono) }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .background(if (isSelected) bgColor else Color(0xFFF8FAFC), CircleShape)
                                    .then(if (isSelected) Modifier.border(2.dp, AppTextPrimary, CircleShape) else Modifier),
                                contentAlignment = Alignment.Center
                            ) { Text(emoji, fontSize = 22.sp) }
                            Spacer(Modifier.height(4.dp))
                            Text(getMoodLabel(icono), fontSize = 10.sp, color = AppTextSecondary, textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
                TextButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Text("Cancelar", color = AppTextSecondary)
                }
            }
        }
    }
}