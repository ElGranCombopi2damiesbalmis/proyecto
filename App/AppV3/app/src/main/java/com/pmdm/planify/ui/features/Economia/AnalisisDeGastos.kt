package com.pmdm.planify.ui.features.AnalisisDeGastos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import java.time.format.DateTimeFormatter

// Colores unificados con tu diseño
val PrimaryYellow = Color(0xFFFACC15)
val SurfaceVariant = Color(0xFFF4F4F5)
val SuccessGreen = Color(0xFF16A34A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastosScreen(
    vm: AnalisisDeGastosViewModel = hiltViewModel(),
    onNavigateToNuevaTransaccion: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {}
) {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToNuevaTransaccion,
                containerColor = PrimaryYellow,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir", tint = Color.Black)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item { HeaderSection(onProfileClick = onNavigateToSettings) }
            item { TotalCard(total = vm.gastoTotal) }
            item { TrendSection() }
            item {
                CategoryFilters(
                    selectedCategory = vm.categoriaSeleccionada,
                    onCategoryClick = { vm.onCategoriaSelected(it) }
                )
            }
            item {
                Text(
                    "Movimientos Recientes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            items(vm.transaccionesFiltradas) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun HeaderSection(onProfileClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp).clickable { onProfileClick() },
            shape = CircleShape,
            color = SurfaceVariant
        ) {
            Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.padding(8.dp))
        }
    }
    Text(
        text = "Análisis de Gastos",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TotalCard(total: Double) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Gasto Total (Este mes)", color = Color.Gray, fontSize = 14.sp)
                Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = Color.Gray)
            }
            Text("$${String.format("%.2f", total)}", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(vertical = 8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
                Text("+12% vs mes anterior", color = SuccessGreen, fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}

@Composable
fun CategoryFilters(selectedCategory: String, onCategoryClick: (String) -> Unit) {
    val categorias = listOf("Todo", "Comida", "Transporte", "Hogar", "Salud")
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categorias) { categoria ->
            CategoryButton(
                label = categoria,
                isSelected = categoria == selectedCategory,
                onClick = { onCategoryClick(categoria) }
            )
        }
    }
}

@Composable
fun CategoryButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) PrimaryYellow else SurfaceVariant,
        border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier.height(40.dp)
    ) {
        Box(modifier = Modifier.padding(horizontal = 20.dp), contentAlignment = Alignment.Center) {
            Text(label, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium, color = if (isSelected) Color.Black else Color(0xFF64748B))
        }
    }
}

@Composable
fun TransactionItem(t: Transaccion) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM")
    val fechaTexto = t.fecha?.format(dateFormatter) ?: "---"

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).background(SurfaceVariant, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(t.icon, contentDescription = null, tint = if(t.tipo == TipoTransaccion.GASTO) Color.Gray else SuccessGreen)
        }
        Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
            Text(t.nombre, fontWeight = FontWeight.Bold)
            Text("$fechaTexto • ${t.categoria}", color = Color.Gray, fontSize = 12.sp)
        }
        Text(
            text = (if (t.tipo == TipoTransaccion.GASTO) "-" else "+") + "$${String.format("%.2f", t.cantidad)}",
            fontWeight = FontWeight.Bold,
            color = if (t.tipo == TipoTransaccion.GASTO) Color.Black else SuccessGreen
        )
    }
}

@Composable
fun TrendSection() {
    Column {
        Text("Tendencia", fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth().height(120.dp).padding(top = 16.dp).background(SurfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(16.dp)).padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            Bar(0.3f, "S1", false)
            Bar(0.8f, "S2", true)
            Bar(0.5f, "S3", false)
            Bar(0.2f, "S4", false)
        }
    }
}

@Composable
fun Bar(fraction: Float, label: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.width(20.dp).fillMaxHeight(fraction).clip(RoundedCornerShape(4.dp)).background(if (isSelected) PrimaryYellow else PrimaryYellow.copy(alpha = 0.3f)))
        Text(label, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = SurfaceVariant, tonalElevation = 0.dp) {
        val items = listOf(
            Triple("Inicio", Icons.Filled.Home, false),
            Triple("Tareas", Icons.Filled.CalendarMonth, false),
            Triple("Gym", Icons.Filled.FitnessCenter, false),
            Triple("Gastos", Icons.Filled.Payments, true),
            Triple("Ánimo", Icons.Filled.SentimentSatisfied, false)
        )
        items.forEach { (label, icon, isSelected) ->
            NavigationBarItem(
                selected = isSelected,
                onClick = { },
                icon = { Icon(icon, null) },
                label = { Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1C1C0D),
                    indicatorColor = Color(0xFFF2F5A9),
                    unselectedIconColor = Color(0xFF64748B)
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GastosScreenPreview() {
    MaterialTheme {
        Box(modifier = Modifier.background(Color.White)) {
            GastosScreen()
        }
    }
}