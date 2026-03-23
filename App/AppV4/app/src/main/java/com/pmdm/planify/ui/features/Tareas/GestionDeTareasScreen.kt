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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pmdm.planify.models.EtiquetaTarea
import com.pmdm.planify.ui.navigation.SettingsRoute

private val OlivePrimary              = Color(0xFF6B5E0F)
private val OliveOnPrimary            = Color(0xFFFFFFFF)
private val OlivePrimaryContainer     = Color(0xFFF5E86E)
private val OliveOnPrimaryContainer   = Color(0xFF201C00)
private val OliveSecondaryContainer   = Color(0xFFECE3BC)
private val OliveOnSecondaryContainer = Color(0xFF201C04)
private val TaskBackground            = Color(0xFFFFFFFF)
private val TaskSurfaceVariant        = Color(0xFFE7E2D6)

// ─── SCREEN PRINCIPAL ────────────────────────────────────────────────────────
@Composable
fun TaskManagerScreen(
    navController: NavHostController,
    viewModel: TareaViewModel
) {
    val state           by viewModel.uiState.collectAsState()
    val tareasFiltradas  = viewModel.getTareasFiltradas()

    // Diálogo
    if (state.mostrarDialogo) {
        NuevaTareaDialog(
            titulo       = state.tituloNueva,
            descripcion  = state.descripcionNueva,
            etiqueta     = state.etiquetaNueva,
            errorTitulo  = state.errorTitulo,
            onTituloChange      = viewModel::onTituloChange,
            onDescripcionChange = viewModel::onDescripcionChange,
            onEtiquetaChange    = viewModel::onEtiquetaChange,
            onGuardar           = viewModel::guardarTarea,
            onDismiss           = viewModel::cerrarDialogo
        )
    }

    Scaffold(
        containerColor = TaskBackground,
        bottomBar      = { PlanifyBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick        = viewModel::abrirDialogo,
                containerColor = OlivePrimaryContainer,
                contentColor   = OliveOnPrimaryContainer,
                shape          = RoundedCornerShape(16.dp)
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
                PlanifyHeader(
                    nombreUsuario   = state.nombreUsuario,
                    fraseBienvenida = "Tus tareas",
                    onProfileClick  = { navController.navigate(SettingsRoute) }
                )
            }
            item {
                CalendarSection(
                    mesVisible = state.mesVisible,
                    fechaSeleccionada = state.fechaSeleccionada,
                    dias = viewModel.getCalendarCells(),
                    onDiaClick = viewModel::seleccionarFecha,
                    onMesAnterior = viewModel::mesAnterior,
                    onMesSiguiente = viewModel::mesSiguiente
                )
            }
            item {
                FilterSection(
                    seleccionado  = state.filtroSeleccionado,
                    onFiltroClick = viewModel::cambiarFiltro
                )
            }
            item { TasksHeader(pendientes = tareasFiltradas.count { !it.completada }, fechaSeleccionada = state.fechaSeleccionada) }
            if (tareasFiltradas.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = TaskSurfaceVariant.copy(alpha = 0.45f)
                    ) {
                        Text(
                            text = "No hay tareas para la fecha seleccionada",
                            modifier = Modifier.padding(16.dp),
                            color = Color.Gray
                        )
                    }
                }
            } else {
                items(tareasFiltradas) { tarea ->
                    TaskCard(
                        tarea           = tarea,
                        onCheckedChange = { viewModel.onTareaCheckedChange(tarea.id, it) }
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// ─── DIÁLOGO NUEVA TAREA ─────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaTareaDialog(
    titulo: String,
    descripcion: String,
    etiqueta: EtiquetaTarea,
    errorTitulo: Boolean,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onEtiquetaChange: (EtiquetaTarea) -> Unit,
    onGuardar: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor   = Color.White,
        shape            = RoundedCornerShape(24.dp),
        title = {
            Text(
                "Nueva Tarea",
                style      = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // ── Título ──
                OutlinedTextField(
                    value         = titulo,
                    onValueChange = onTituloChange,
                    label         = { Text("Título *") },
                    placeholder   = { Text("Ej: Comprar comida") },
                    isError       = errorTitulo,
                    supportingText = if (errorTitulo) ({ Text("El título es obligatorio", color = MaterialTheme.colorScheme.error) }) else null,
                    singleLine    = true,
                    modifier      = Modifier.fillMaxWidth(),
                    shape         = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    colors        = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = OlivePrimary,
                        focusedLabelColor  = OlivePrimary,
                        cursorColor        = OlivePrimary
                    )
                )

                // ── Descripción ──
                OutlinedTextField(
                    value         = descripcion,
                    onValueChange = onDescripcionChange,
                    label         = { Text("Descripción (opcional)") },
                    placeholder   = { Text("Añade más detalles...") },
                    minLines      = 2,
                    maxLines      = 3,
                    modifier      = Modifier.fillMaxWidth(),
                    shape         = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    colors        = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = OlivePrimary,
                        focusedLabelColor  = OlivePrimary,
                        cursorColor        = OlivePrimary
                    )
                )

                // ── Etiqueta ──
                Text(
                    "Categoría",
                    style      = MaterialTheme.typography.labelMedium,
                    color      = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(EtiquetaTarea.values()) { e ->
                        val selected = e == etiqueta
                        FilterChip(
                            selected = selected,
                            onClick  = { onEtiquetaChange(e) },
                            label    = { Text(e.name, fontSize = 11.sp) },
                            colors   = FilterChipDefaults.filterChipColors(
                                selectedContainerColor    = OlivePrimaryContainer,
                                selectedLabelColor        = OliveOnPrimaryContainer,
                                selectedLeadingIconColor  = OliveOnPrimaryContainer
                            )
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onGuardar,
                colors  = ButtonDefaults.buttonColors(
                    containerColor = OlivePrimary,
                    contentColor   = OliveOnPrimary
                ),
                shape   = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Check, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("Guardar", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = Color.Gray)
            }
        }
    )
}

// ─── TASK CARD ────────────────────────────────────────────────────────────────
@Composable
fun TaskCard(tarea: TareaMock, onCheckedChange: (Boolean) -> Unit) {
    val horaFormateada = tarea.fecha.format(DateTimeFormatter.ofPattern("hh:mm a"))

    val (tagContainerColor, tagTextColor) = when (tarea.etiqueta.name) {
        "HOGAR"  -> Pair(Color(0xFFE8DEF8), Color(0xFF1D192B))
        "ESTUDIO"-> Pair(OlivePrimaryContainer, OliveOnPrimaryContainer)
        else     -> Pair(TaskSurfaceVariant, Color.Black)
    }

    Card(
        shape    = RoundedCornerShape(12.dp),
        colors   = CardDefaults.cardColors(
            containerColor = if (tarea.completada)
                TaskSurfaceVariant.copy(alpha = 0.5f) else Color(0xFFF3EEE2)
        ),
        modifier = Modifier.fillMaxWidth().clickable { onCheckedChange(!tarea.completada) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text           = tarea.titulo,
                    fontSize       = 16.sp,
                    fontWeight     = FontWeight.Medium,
                    color          = Color.Black.copy(alpha = if (tarea.completada) 0.5f else 1f),
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
                            Text(tarea.etiqueta.name,
                                Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                fontSize = 10.sp, color = tagTextColor)
                        }
                    } else {
                        Text("Completado a las $horaFormateada", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
            IconButton(onClick = { onCheckedChange(!tarea.completada) }) {
                Icon(
                    imageVector        = if (tarea.completada) Icons.Filled.CheckBox
                    else Icons.Filled.CheckBoxOutlineBlank,
                    contentDescription = "Marcar tarea",
                    tint               = OlivePrimary,
                    modifier           = Modifier.size(24.dp)
                )
            }
        }
    }
}

// ─── SUB-COMPONENTES ─────────────────────────────────────────────────────────
@Composable
fun CalendarSection(
    mesVisible: java.time.YearMonth,
    fechaSeleccionada: java.time.LocalDate,
    dias: List<CalendarCell>,
    onDiaClick: (java.time.LocalDate) -> Unit,
    onMesAnterior: () -> Unit,
    onMesSiguiente: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onMesAnterior) { Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Mes anterior") }
            Text(
                text = mesVisible.month.name.lowercase().replaceFirstChar { it.uppercase() } + " " + mesVisible.year,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            IconButton(onClick = onMesSiguiente) { Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Mes siguiente") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("D","L","M","X","J","V","S").forEach { day ->
                Text(day, modifier = Modifier.width(40.dp), color = Color.Gray,
                    fontSize = 12.sp, textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        dias.chunked(7).forEach { semana ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                semana.forEach { day -> DayItem(day = day, onClick = onDiaClick) }
                repeat(7 - semana.size) { Spacer(modifier = Modifier.size(40.dp)) }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
        Text(
            text = "Seleccionado: ${fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun DayItem(day: CalendarCell, onClick: (java.time.LocalDate) -> Unit) {
    val clickableModifier = if (day.date != null) Modifier.clickable { onClick(day.date) } else Modifier
    Column(
        modifier = Modifier.size(40.dp).then(clickableModifier).clip(CircleShape)
            .background(if (day.isSelected) OlivePrimary else Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(day.dayNumber,
            color      = if (day.isSelected) OliveOnPrimary else Color.Black,
            fontWeight = if (day.isSelected) FontWeight.SemiBold else FontWeight.Normal)
        if (day.hasEvent && !day.isSelected) {
            Spacer(Modifier.height(2.dp))
            Box(Modifier.size(4.dp).clip(CircleShape).background(OlivePrimary))
        }
    }
}

@Composable
fun FilterSection(seleccionado: String, onFiltroClick: (String) -> Unit) {
    val filtros = listOf(
        "Todos"     to Icons.Default.Check,
        "Prioridad" to Icons.Outlined.Flag,
        "Gimnasio"  to Icons.Outlined.FitnessCenter,
        "Finanzas"  to Icons.Outlined.Payments
    )
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(filtros) { (label, icon) ->
            FilterChipItem(label, icon, seleccionado == label) { onFiltroClick(label) }
        }
    }
}

@Composable
fun FilterChipItem(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        color    = if (isSelected) OliveSecondaryContainer else Color.Transparent,
        shape    = RoundedCornerShape(8.dp),
        border   = if (isSelected) null else BorderStroke(1.dp, Color.Gray.copy(0.5f)),
        modifier = Modifier.height(32.dp).clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, Modifier.size(16.dp),
                if (isSelected) OliveOnSecondaryContainer else Color.Gray)
            Spacer(Modifier.width(8.dp))
            Text(label, fontSize = 12.sp, color = if (isSelected) OliveOnSecondaryContainer else Color.Gray)
        }
    }
}

@Composable
fun TasksHeader(pendientes: Int, fechaSeleccionada: java.time.LocalDate) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text("Tareas del ${fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM"))}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Surface(color = OlivePrimaryContainer, shape = RoundedCornerShape(50)) {
            Text("$pendientes pendientes",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OliveOnPrimaryContainer)
        }
    }
}