package com.pmdm.planify.ui.features.Tareas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarViewMonth
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ViewWeek
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBarItem
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
import java.time.format.DateTimeFormatter
import com.pmdm.planify.data.daomocks.TareaDaoMock
import com.pmdm.planify.data.mocks.TareaMock
import kotlin.collections.count
import kotlin.collections.forEach
import kotlin.text.isNotEmpty
import kotlin.to

// --- 1. Definición de Colores (Extraídos de tu HTML) ---
val OlivePrimary = Color(0xFF6B5E0F)
val OliveOnPrimary = Color(0xFFFFFFFF)
val OlivePrimaryContainer = Color(0xFFF5E86E)
val OliveOnPrimaryContainer = Color(0xFF201C00)
val OliveSecondaryContainer = Color(0xFFECE3BC)
val OliveOnSecondaryContainer = Color(0xFF201C04)
val BackgroundColor = Color(0xFFFFFFFF) // O un tono crema muy suave
val SurfaceVariant = Color(0xFFE7E2D6)
val ErrorColor = Color(0xFFBA1A1A)
val ErrorContainer = Color(0xFFFFDAD6)
val OnErrorContainer = Color(0xFF410002)

// --- 2. Modelos de Datos ---
data class Task(
    val title: String,
    val time: String,
    val tag: String? = null,
    val tagColor: Color? = null,
    val tagTextColor: Color? = null,
    val isCompleted: Boolean = false
)

data class CalendarDay(
    val dayNumber: String,
    val isSelected: Boolean = false,
    val hasEvent: Boolean = false,
    val eventColor: Color = OlivePrimary
)

// --- 3. Componente Principal ---
// --- 3. Componente Principal Actualizado ---
@Composable
fun TaskManagerScreen(
    // Inyectamos el DAO por parámetro (con un valor por defecto para facilitar las Previews)
    tareaDao: TareaDaoMock = TareaDaoMock()
) {
    // Obtenemos la lista de tareas del DAO
    // Nota: En una app real, esto vendría de un ViewModel usando un Flow o State
    val tareas = tareaDao.tareas

    Scaffold(
        containerColor = BackgroundColor,
        topBar = { TaskTopAppBar() },
        bottomBar = { TaskBottomNavigation() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción agregar */ },
                containerColor = OlivePrimaryContainer,
                contentColor = OliveOnPrimaryContainer,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
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
            item { CalendarSection() }
            item { FilterSection() }
            item { TasksHeader(pendientes = tareas.count { !it.completada }) }

            items(tareas) { tarea ->
                TaskCard(tarea)
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

// --- Componente TaskCard Actualizado ---
@Composable
fun TaskCard(tarea: TareaMock) {
    // Formateamos el LocalDateTime a un texto legible (ej. "10:30 AM")
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val horaFormateada = tarea.fecha.format(formatter)

    // Asignamos colores a las etiquetas dinámicamente según su nombre
    // (Ajusta los nombres del Enum según los tengas definidos en EtiquetaTarea)
    val (tagContainerColor, tagTextColor) = when (tarea.etiqueta.name) {
        "HOGAR" -> Pair(Color(0xFFE8DEF8), Color(0xFF1D192B)) // Tonos morados
        "ESTUDIO" -> Pair(OlivePrimaryContainer, OliveOnPrimaryContainer) // Tonos oliva
        else -> Pair(SurfaceVariant, Color.Black) // Por defecto
    }

    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.completada) SurfaceVariant.copy(alpha = 0.5f) else Color(
                0xFFF3EEE2
            )
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tarea.titulo, // Viene del Mock
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(alpha = if (tarea.completada) 0.5f else 1f),
                    textDecoration = if (tarea.completada) TextDecoration.LineThrough else null
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Muestra la descripción si existe
                if (tarea.descripcion.isNotEmpty() && !tarea.completada) {
                    Text(
                        text = tarea.descripcion,
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (tarea.completada) {
                        Text(
                            text = "Completado a las $horaFormateada",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    } else {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = horaFormateada,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // Etiqueta dinámica
                        Surface(
                            color = tagContainerColor,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = tarea.etiqueta.name,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = tagTextColor
                            )
                        }
                    }
                }
            }

            // Checkbox simulado
            if (tarea.completada) {
                Icon(
                    Icons.Filled.CheckBox,
                    contentDescription = "Completado",
                    tint = OlivePrimary,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .border(
                            2.dp,
                            OlivePrimary,
                            androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    }
}

// --- 4. Sub-Componentes ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Octubre 2023",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.Black)
            }
            // Avatar de usuario simulado
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BackgroundColor
        )
    )
}

@Composable
fun CalendarSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Toggle Mes/Semana
        Row(
            modifier = Modifier
                .width(300.dp)
                .height(40.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), CircleShape)
                .clip(CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(OliveSecondaryContainer)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.CalendarViewMonth,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = OliveOnSecondaryContainer
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Mes", color = OliveOnSecondaryContainer, fontWeight = FontWeight.Medium)
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.ViewWeek,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Gray
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Semana", color = Color.Gray, fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Días de la semana (Encabezados)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("D", "L", "M", "X", "J", "V", "S").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.width(40.dp),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Grilla de días (Hardcoded visualmente para coincidir con la imagen)
        // Semana 1 (Parcial)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Spacer(modifier = Modifier.width(40.dp)) // D
            Spacer(modifier = Modifier.width(40.dp)) // L
            Spacer(modifier = Modifier.width(40.dp)) // M
            DayItem(CalendarDay("1"))
            DayItem(CalendarDay("2"))
            DayItem(CalendarDay("3"))
            DayItem(CalendarDay("4", hasEvent = true, eventColor = OlivePrimary)) // Jueves 4
        }

        // Semana 2
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            DayItem(CalendarDay("5", isSelected = true)) // Viernes 5 (Seleccionado)
            DayItem(CalendarDay("6"))
            DayItem(CalendarDay("7"))
            DayItem(CalendarDay("8"))
            DayItem(CalendarDay("9", hasEvent = true, eventColor = ErrorColor)) // Martes 9
            DayItem(CalendarDay("10"))
            DayItem(CalendarDay("11"))
        }

        // Icono desplegable
        Icon(
            Icons.Default.KeyboardArrowDown,
            contentDescription = "Expandir",
            tint = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
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
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(day.eventColor)
            )
        }
    }
}

@Composable
fun FilterSection() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            FilterChipItem("Todos", Icons.Default.Check, true)
        }

        items(
            listOf(
                "Prioridad" to Icons.Outlined.Flag,
                "Gimnasio" to Icons.Outlined.FitnessCenter,
                "Finanzas" to Icons.Outlined.Payments
            )
        ) { (label, icon) ->
            FilterChipItem(label, icon, false)
        }
    }
}

@Composable
fun FilterChipItem(label: String, icon: ImageVector, isSelected: Boolean) {
    Surface(
        color = if (isSelected) OliveSecondaryContainer else Color.Transparent,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        border = if (isSelected) null else BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
        modifier = Modifier.height(32.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = if (isSelected) OliveOnSecondaryContainer else Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                color = if (isSelected) OliveOnSecondaryContainer else Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun TasksHeader(pendientes: Int) { // Añade este parámetro
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Tareas de hoy", fontSize = 20.sp, color = Color.Black)
        Surface(
            color = OlivePrimaryContainer,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
        ) {
            Text(
                "$pendientes pendientes", // Se actualiza dinámicamente
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = OliveOnPrimaryContainer
            )
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.isCompleted) SurfaceVariant.copy(alpha = 0.5f) else Color(
                0xFFF3EEE2
            ) // Surface container high
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(alpha = if (task.isCompleted) 0.5f else 1f),
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (task.isCompleted) {
                        Text(
                            text = task.time,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    } else {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = task.time,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        // Tag (si existe)
                        if (task.tag != null) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                color = task.tagColor ?: Color.LightGray,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = task.tag,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = task.tagTextColor ?: Color.Black
                                )
                            }
                        }
                    }
                }
            }

            // Checkbox
            if (task.isCompleted) {
                Icon(
                    Icons.Filled.CheckBox,
                    contentDescription = "Completado",
                    tint = OlivePrimary,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .border(
                            2.dp,
                            OlivePrimary,
                            androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    }
}

@Composable
fun TaskBottomNavigation() {
    NavigationBar(
        // Usamos los colores exactos de Ventana Principal
        containerColor = Color(0xFFF4F4F5), // SurfaceContainer
        contentColor = Color(0xFF64748B),    // TextSecondary
        tonalElevation = 0.dp
    ) {
        // Definimos los 5 ítems exactos de la Ventana Principal
        val items = listOf(
            Triple("Tareas", Icons.Filled.CalendarMonth, true),
            Triple("Gym", Icons.Filled.FitnessCenter, false),
            Triple("Inicio", Icons.Filled.Home, false),
            Triple("Gastos", Icons.Filled.Payments, false),
            Triple("Ánimo", Icons.Filled.SentimentSatisfied, false)
        )

        items.forEach { (label, icon, isSelected) ->
            NavigationBarItem(
                selected = isSelected,
                onClick = { /* Aquí irá la navegación más adelante */ },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1C1C0D), // OnPrimary (Negro)
                    selectedTextColor = Color(0xFF1C1C0D), // TextPrimary
                    indicatorColor = Color(0xFFF2F5A9),    // PrimaryContainer (Amarillo suave)
                    unselectedIconColor = Color(0xFF64748B),
                    unselectedTextColor = Color(0xFF64748B)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskManagerPreview() {
    MaterialTheme {
        TaskManagerScreen()
    }
}