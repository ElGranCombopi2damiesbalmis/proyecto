package com.pmdm.appV1.ui.features.VentanaPrincipal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- 1. Definición de Colores (Extraídos del HTML) ---
val PrimaryLime = Color(0xFFE2E722)
val OnPrimary = Color(0xFF1C1C0D)
val PrimaryContainer = Color(0xFFF2F590) // Amarillo suave para el fondo de ánimo
val SurfaceBackground = Color(0xFFFFFBFE)
val SurfaceContainer = Color(0xFFF3F4F6) // Gris muy claro para las cards
val TextPrimary = Color(0xFF1C1B1F)
val TextSecondary = Color(0xFF757575)

// Colores específicos para emociones
val MoodAngry = Color(0xFFEF4444) // Red-500
val MoodSad = Color(0xFFFB923C)   // Orange-400
val MoodFine = Color(0xFFEAB308)  // Yellow-500
val MoodGreat = Color(0xFFE2E722) // Primary

// --- 2. Componente Principal ---
@Composable
fun DashboardScreen() {
    Scaffold(
        containerColor = SurfaceBackground,
        bottomBar = { DashboardBottomBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item { HeaderSection() }

            // Sección: ¿Cómo te sientes?
            item { MoodSection() }

            // Sección: Mis Tareas
            item { TasksSection() }

            // Sección: Rutina de Hoy (Imagen grande)
            item { WorkoutSection() }

            // Sección: Finanzas
            item { FinanceSection() }

            // Espacio final para scroll
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// --- 3. Secciones ---

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "Hola, de nuevo",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
            Text(
                text = "Andrea",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box {
            // Avatar Placeholder
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = Color.Gray
            ) {
                // Aquí iría tu Image(painter = ...)
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                }
            }
            // Punto de estado verde
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(Color(0xFF22C55E), CircleShape) // Green-500
                    .border(2.dp, SurfaceBackground, CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun MoodSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = PrimaryContainer.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "¿Cómo te sientes hoy?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MoodItem(Icons.Outlined.SentimentVeryDissatisfied, "Enfadado", MoodAngry, false)
                MoodItem(Icons.Outlined.SentimentDissatisfied, "Triste", MoodSad, false)
                MoodItem(Icons.Outlined.SentimentSatisfied, "Bien", MoodFine, false)
                MoodItem(Icons.Filled.SentimentVerySatisfied, "Genial", OnPrimary, true)
            }
        }
    }
}

@Composable
fun MoodItem(icon: ImageVector, label: String, color: Color, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            color = if (isSelected) PrimaryLime else SurfaceBackground,
            shadowElevation = 2.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = TextSecondary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun TasksSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mis Tareas",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryLime,
                        contentColor = OnPrimary
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Ver Todo", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Calendar Strip
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CalendarDayItem("Dom", "12", false)
                CalendarDayItem("Lun", "13", false)
                CalendarDayItem("Mar", "14", true) // Selected
                CalendarDayItem("Mié", "15", false)
                CalendarDayItem("Jue", "16", false)
                CalendarDayItem("Vie", "17", false)
                CalendarDayItem("Sab", "18", false)
            }

            // Tasks List
            TaskItem(
                title = "Reunión con equipo",
                subtitle = "10:00 AM • Zoom",
                icon = Icons.Rounded.Videocam,
                isCompleted = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            TaskItem(
                title = "Comprar víveres",
                subtitle = "Lista completada",
                icon = Icons.Default.Check, // Check inside circle logic handled inside
                isCompleted = true
            )
        }
    }
}

@Composable
fun CalendarDayItem(day: String, date: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = day, fontSize = 11.sp, color = TextSecondary)
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(if (isSelected) PrimaryLime else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = date,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) OnPrimary else TextSecondary
            )
        }
    }
}

@Composable
fun TaskItem(title: String, subtitle: String, icon: ImageVector, isCompleted: Boolean) {
    Surface(
        color = if (isCompleted) SurfaceBackground.copy(alpha = 0.5f) else SurfaceBackground,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox/Status Icon
            if (isCompleted) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(PrimaryLime, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(16.dp))
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(2.dp, Color.LightGray, CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = if (isCompleted) TextDecoration.LineThrough else null,
                    color = if(isCompleted) TextSecondary else TextPrimary
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            if (!isCompleted) {
                Surface(
                    color = SurfaceContainer,
                    shape = CircleShape,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color.DarkGray) // Placeholder color if image fails
    ) {
        // Placeholder background - replace with actual Image
        // Image(painter = painterResource(id = R.drawable.gym_bg), ...)
        Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray))

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Tag
            Row {
                Surface(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(50),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = PrimaryLime, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Rutina de Hoy", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Bottom Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("DÍA 4", color = PrimaryLime, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Pierna y\nGlúteos",
                        color = Color.White,
                        fontSize = 32.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Normal // Font display style
                    )
                }

                // Play Button
                Surface(
                    color = PrimaryLime,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(56.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = "Start", tint = OnPrimary, modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FinanceSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = PrimaryContainer, // Yellow-ish
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Outlined.AccountBalanceWallet, contentDescription = null, tint = OnPrimary)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Finanzas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Este mes", color = TextSecondary, fontSize = 12.sp)
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Total gastado", color = TextSecondary, fontSize = 12.sp)
                    Text("$845.00", fontWeight = FontWeight.Normal, fontSize = 24.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bar Chart Visualization
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Bar values mimic the height % in HTML
                FinanceBar(0.4f, false)
                FinanceBar(0.65f, false)
                FinanceBar(0.3f, false, color = PrimaryLime.copy(alpha = 0.3f))
                FinanceBar(0.85f, false)
                FinanceBar(0.55f, true) // "Today"
                FinanceBar(0.2f, false, isFaded = true)
                FinanceBar(0.2f, false, isFaded = true)
            }
        }
    }
}

@Composable
fun FinanceBar(fillFraction: Float, isToday: Boolean, color: Color = Color.White, isFaded: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().width(36.dp), // Width distribuiton
        verticalArrangement = Arrangement.Bottom
    ) {
        if (isToday) {
            Surface(
                color = OnPrimary,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                Text(
                    "Hoy",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fillFraction)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(if (isToday) PrimaryLime else color.copy(alpha = if(isFaded) 0.5f else 1f))
        )
    }
}


@Composable
fun DashboardBottomBar() {
    NavigationBar(
        containerColor = SurfaceContainer,
        contentColor = TextSecondary,
        tonalElevation = 0.dp
    ) {
        val items = listOf(
            Triple("Inicio", Icons.Filled.Home, true),
            Triple("Tareas", Icons.Filled.CalendarMonth, false),
            Triple("Gym", Icons.Filled.FitnessCenter, false),
            Triple("Gastos", Icons.Filled.Payments, false),
            Triple("Ánimo", Icons.Filled.SentimentSatisfied, false)
        )

        items.forEach { (label, icon, isSelected) ->
            NavigationBarItem(
                selected = isSelected,
                onClick = { },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = OnPrimary,
                    selectedTextColor = TextPrimary,
                    indicatorColor = PrimaryContainer,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary
                )
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 1000)
@Composable
fun DashboardPreview() {
    MaterialTheme {
        DashboardScreen()
    }
}