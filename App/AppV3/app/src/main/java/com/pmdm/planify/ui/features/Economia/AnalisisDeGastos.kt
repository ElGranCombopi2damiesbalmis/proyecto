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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion
import com.pmdm.planify.ui.features.Componentes.PlanifyBottomBar
import com.pmdm.planify.ui.features.Componentes.PlanifyHeader
import com.pmdm.planify.ui.features.Economia.AnalisisDeGastosViewModel
import java.time.format.DateTimeFormatter

// Colores unificados
val PrimaryYellow = Color(0xFFFACC15)
val SurfaceVariantFinance = Color(0xFFF4F4F5)
val SuccessGreen = Color(0xFF16A34A)

@Composable
fun GastosScreen(
    navController: NavHostController,
    vm: AnalisisDeGastosViewModel = hiltViewModel(),
    onNavigateToNuevaTransaccion: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {}
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = { PlanifyBottomBar(navController) },
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
            // HEADER COMÚN (Mantiene la coherencia con Inicio, Gym y Tareas)
            item {
                PlanifyHeader(
                    nombreUsuario = "Andrea",
                    fraseBienvenida = "Tus finanzas",
                    onProfileClick = onNavigateToSettings
                )

                Text(
                    text = "Análisis de Gastos",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                )
            }

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

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// --- COMPONENTES INTERNOS ---

@Composable
fun TotalCard(total: Double) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariantFinance),
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
        color = if (isSelected) PrimaryYellow else SurfaceVariantFinance,
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
            modifier = Modifier.size(48.dp).background(SurfaceVariantFinance, CircleShape),
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
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(top = 16.dp)
                .background(SurfaceVariantFinance.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            BarFinance(0.3f, "S1", false)
            BarFinance(0.8f, "S2", true)
            BarFinance(0.5f, "S3", false)
            BarFinance(0.2f, "S4", false)
        }
    }
}

@Composable
fun BarFinance(fraction: Float, label: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.width(20.dp).fillMaxHeight(fraction).clip(RoundedCornerShape(4.dp)).background(if (isSelected) PrimaryYellow else PrimaryYellow.copy(alpha = 0.3f)))
        Text(label, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GastosScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        GastosScreen(navController = navController)
    }
}