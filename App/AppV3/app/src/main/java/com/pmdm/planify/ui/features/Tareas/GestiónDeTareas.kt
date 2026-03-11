package com.pmdm.planify.ui.features.Tareas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.data.mocks.TareaMock
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

// --- Colores del Tema de Tareas ---
val OlivePrimary = Color(0xFF6B5E0F)
val OliveOnPrimary = Color(0xFFFFFFFF)
val OlivePrimaryContainer = Color(0xFFF5E86E)
val OliveOnPrimaryContainer = Color(0xFF201C00)
val OliveSecondaryContainer = Color(0xFFECE3BC)
val OliveOnSecondaryContainer = Color(0xFF201C04)
val BackgroundColor = Color(0xFFFFFFFF)
val SurfaceVariantTasks = Color(0xFFE7E2D6)

// --- Modelos ---
data class CalendarDay(
    val dayNumber: String,
    val isSelected: Boolean = false,
    val hasEvent: Boolean = false,
    val eventColor: Color = OlivePrimary
)

@Composable
fun TaskManagerScreen(
    navController: NavHostController,
    viewModel: TareaViewModel = viewModel()
) {
    // Aquí recolectamos el estado que incluye las tareas y el nombre de usuario
    val state by viewModel.uiState.collectAsState()
    val tareasFiltradas = viewModel.getTareasFiltradas()

    Scaffold(
        containerColor = BackgroundColor,
        // Tu BottomBar ya está correctamente implementado aquí:
        bottomBar = { PlanifyBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción agregar */ },
                containerColor = OlivePrimaryContainer,
                contentColor = OliveOnPrimaryContainer,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Tarea")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                // Pasamos el estado dinámico al Header en lugar del string estático
                PlanifyHeader(
                    nombreUsuario = state.nombreUsuario,
                    fraseBienvenida = "Tus tareas",
                    onProfileClick = {
                        // Aquí podrías implementar la navegación al perfil
                        // navController.navigate(PerfilRoute)
                    }
                )
            }

            item { CalendarSection() }

            item {
                FilterSection(
                    seleccionado = state.filtroSeleccionado,
                    onFiltroClick = { viewModel.cambiarFiltro(it) }
                )
            }

            item {
                TasksHeader(pendientes = tareasFiltradas.count { !it.completada })
            }

            items(tareasFiltradas) { tarea ->
                TaskCard(
                    tarea = tarea,
                    onCheckedChange = { viewModel.onTareaCheckedChange(tarea.id, it) }
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// --- Componente TaskCard ---
@Composable
fun TaskCard(
    tarea: TareaMock,
    onCheckedChange: (Boolean) -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val horaFormateada = tarea.fecha.format(formatter)

    val (tagContainerColor, tagTextColor) = when (tarea.etiqueta.name) {
        "HOGAR" -> Pair(Color(0xFFE8DEF8), Color(0xFF1D192B))
        "ESTUDIO" -> Pair(OlivePrimaryContainer, OliveOnPrimaryContainer)
        else -> Pair(SurfaceVariantTasks, Color.Black)
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.completada) SurfaceVariantTasks.copy(alpha = 0.5f) else Color(0xFFF3EEE2)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!tarea.completada) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tarea.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(alpha = if (tarea.completada) 0.5f else 1f),
                    textDecoration = if (tarea.completada) TextDecoration.LineThrough else null
                )

                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!tarea.completada) {
                        Icon(Icons.Default.Schedule, null, Modifier.size(14.dp), Color.Gray)
                        Spacer(Modifier.width(4.dp))
                        Text(horaFormateada, fontSize = 12.sp, color = Color.Gray)
                        Spacer(Modifier.width(8.dp))
                        Surface(color = tagContainerColor, shape = RoundedCornerShape(4.dp)) {
                            Text(tarea.etiqueta.name, Modifier.padding(horizontal = 4.dp, vertical = 2.dp), fontSize = 10.sp, color = tagTextColor)
                        }
                    } else {
                        Text("Completado a las $horaFormateada", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }

            IconButton(onClick = { onCheckedChange(!tarea.completada) }) {
                Icon(
                    imageVector = if (tarea.completada) Icons.Filled.CheckBox else Icons.Filled.CheckBoxOutlineBlank,
                    contentDescription = "Marcar tarea",
                    tint = OlivePrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// --- Sub-Componentes ---

@Composable
fun CalendarSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .width(300.dp)
                .height(40.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), CircleShape)
                .clip(CircleShape)
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(OliveSecondaryContainer).clickable { }, contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.CalendarViewMonth, null, Modifier.size(18.dp), OliveOnSecondaryContainer)
                    Spacer(Modifier.width(8.dp))
                    Text("Mes", color = OliveOnSecondaryContainer, fontWeight = FontWeight.Medium)
                }
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable { }, contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.ViewWeek, null, Modifier.size(18.dp), Color.Gray)
                    Spacer(Modifier.width(8.dp))
                    Text("Semana", color = Color.Gray, fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("D", "L", "M", "X", "J", "V", "S").forEach { day ->
                Text(text = day, modifier = Modifier.width(40.dp), color = Color.Gray, fontSize = 12.sp, textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Ejemplo visual de fila del calendario
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            DayItem(CalendarDay("5", isSelected = true))
            DayItem(CalendarDay("6"))
            DayItem(CalendarDay("7"))
            DayItem(CalendarDay("8"))
            DayItem(CalendarDay("9", hasEvent = true, eventColor = Color.Red))
            DayItem(CalendarDay("10"))
            DayItem(CalendarDay("11"))
        }

        Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.Gray, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun DayItem(day: CalendarDay) {
    Column(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(if (day.isSelected) OlivePrimary else Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = day.dayNumber,
            color = if (day.isSelected) OliveOnPrimary else Color.Black,
            fontWeight = if (day.isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
        if (day.hasEvent && !day.isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(day.eventColor))
        }
    }
}

@Composable
fun FilterSection(seleccionado: String, onFiltroClick: (String) -> Unit) {
    val filtros = listOf(
        "Todos" to Icons.Default.Check,
        "Prioridad" to Icons.Outlined.Flag,
        "Gimnasio" to Icons.Outlined.FitnessCenter,
        "Finanzas" to Icons.Outlined.Payments
    )

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(filtros) { (label, icon) ->
            FilterChipItem(
                label = label,
                icon = icon,
                isSelected = seleccionado == label,
                onClick = { onFiltroClick(label) }
            )
        }
    }
}

@Composable
fun FilterChipItem(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        color = if (isSelected) OliveSecondaryContainer else Color.Transparent,
        shape = RoundedCornerShape(8.dp),
        border = if (isSelected) null else BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
        modifier = Modifier.height(32.dp).clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, modifier = Modifier.size(16.dp), tint = if (isSelected) OliveOnSecondaryContainer else Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, fontSize = 12.sp, color = if (isSelected) OliveOnSecondaryContainer else Color.Gray)
        }
    }
}

@Composable
fun TasksHeader(pendientes: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Tareas de hoy", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Surface(color = OlivePrimaryContainer, shape = RoundedCornerShape(50)) {
            Text(
                "$pendientes pendientes",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = OliveOnPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskManagerPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        TaskManagerScreen(navController = navController)
    }
}