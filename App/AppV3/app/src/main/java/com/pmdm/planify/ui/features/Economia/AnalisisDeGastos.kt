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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
import com.pmdm.planify.ui.features.Economia.CATEGORIAS
import com.pmdm.planify.ui.features.Economia.CategoriaItem
import java.time.format.DateTimeFormatter

private val FinanceYellow       = Color(0xFFFACC15)
private val FinanceSurface      = Color(0xFFF4F4F5)
private val FinanceGreen        = Color(0xFF16A34A)
private val FinanceRed          = Color(0xFFEF4444)

// ─── SCREEN PRINCIPAL ────────────────────────────────────────────────────────
@Composable
fun GastosScreen(
    navController: NavHostController,
    vm: AnalisisDeGastosViewModel,
    onNavigateToNuevaTransaccion: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {}
) {
    // Diálogo de nueva transacción
    if (vm.mostrarDialogo) {
        NuevaTransaccionDialog(
            nombre          = vm.nombreNueva,
            cantidad        = vm.cantidadNueva,
            categoria       = vm.categoriaNueva,
            tipo            = vm.tipoNueva,
            errorNombre     = vm.errorNombre,
            errorCantidad   = vm.errorCantidad,
            onNombreChange          = vm::onNombreChange,
            onCantidadChange        = vm::onCantidadChange,
            onCategoriaChange       = vm::onCategoriaDialogoChange,
            onTipoChange            = vm::onTipoChange,
            onGuardar               = vm::guardarTransaccion,
            onDismiss               = vm::cerrarDialogo
        )
    }

    Scaffold(
        containerColor = Color.White,
        bottomBar      = { PlanifyBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick        = vm::abrirDialogo,        // ← abre el diálogo interno
                containerColor = FinanceYellow,
                shape          = RoundedCornerShape(16.dp)
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
            item {
                PlanifyHeader(
                    nombreUsuario   = "Andrea",
                    fraseBienvenida = "Tus finanzas",
                    onProfileClick  = onNavigateToSettings
                )
                Text("Análisis de Gastos",
                    style      = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier   = Modifier.padding(top = 8.dp, bottom = 16.dp))
            }
            item { TotalCard(gastos = vm.gastoTotal, ingresos = vm.ingresoTotal) }
            item { TrendSection() }
            item {
                CategoryFilters(
                    selectedCategory = vm.categoriaSeleccionada,
                    onCategoryClick  = vm::onCategoriaSelected
                )
            }
            item {
                Text("Movimientos Recientes",
                    style      = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier   = Modifier.padding(vertical = 16.dp))
            }
            items(vm.transaccionesFiltradas) { t -> TransactionItem(t) }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

// ─── DIÁLOGO NUEVA TRANSACCIÓN ────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaTransaccionDialog(
    nombre: String,
    cantidad: String,
    categoria: CategoriaItem,
    tipo: TipoTransaccion,
    errorNombre: Boolean,
    errorCantidad: Boolean,
    onNombreChange: (String) -> Unit,
    onCantidadChange: (String) -> Unit,
    onCategoriaChange: (CategoriaItem) -> Unit,
    onTipoChange: (TipoTransaccion) -> Unit,
    onGuardar: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor   = Color.White,
        shape            = RoundedCornerShape(24.dp),
        title = {
            Text("Nueva Transacción",
                style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // ── Tipo: Gasto / Ingreso ──
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(FinanceSurface, RoundedCornerShape(12.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TipoTransaccion.values().forEach { t ->
                        val selected = t == tipo
                        val color    = if (t == TipoTransaccion.GASTO) FinanceRed else FinanceGreen
                        Button(
                            onClick  = { onTipoChange(t) },
                            modifier = Modifier.weight(1f).height(40.dp),
                            shape    = RoundedCornerShape(10.dp),
                            colors   = ButtonDefaults.buttonColors(
                                containerColor = if (selected) color else Color.Transparent,
                                contentColor   = if (selected) Color.White else Color.Gray
                            ),
                            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp)
                        ) {
                            Text(
                                if (t == TipoTransaccion.GASTO) "Gasto" else "Ingreso",
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                fontSize   = 13.sp
                            )
                        }
                    }
                }

                // ── Nombre ──
                OutlinedTextField(
                    value         = nombre,
                    onValueChange = onNombreChange,
                    label         = { Text("Concepto *") },
                    placeholder   = { Text("Ej: Cena restaurante") },
                    isError       = errorNombre,
                    supportingText = if (errorNombre) {{ Text("El concepto es obligatorio", color = MaterialTheme.colorScheme.error) }} else null,
                    singleLine    = true,
                    modifier      = Modifier.fillMaxWidth(),
                    shape         = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    colors        = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = FinanceYellow,
                        focusedLabelColor  = Color(0xFF92400E),
                        cursorColor        = FinanceYellow
                    )
                )

                // ── Cantidad ──
                OutlinedTextField(
                    value         = cantidad,
                    onValueChange = onCantidadChange,
                    label         = { Text("Cantidad (€) *") },
                    placeholder   = { Text("0.00") },
                    isError       = errorCantidad,
                    supportingText = if (errorCantidad) {{ Text("Introduce una cantidad válida", color = MaterialTheme.colorScheme.error) }} else null,
                    singleLine    = true,
                    modifier      = Modifier.fillMaxWidth(),
                    shape         = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors        = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = FinanceYellow,
                        focusedLabelColor  = Color(0xFF92400E),
                        cursorColor        = FinanceYellow
                    ),
                    leadingIcon   = { Text("€", modifier = Modifier.padding(start = 12.dp),
                        fontWeight = FontWeight.Bold, color = Color.Gray) }
                )

                // ── Categoría ──
                Text("Categoría",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray, fontWeight = FontWeight.Medium)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(CATEGORIAS) { cat ->
                        val selected = cat.nombre == categoria.nombre
                        FilterChip(
                            selected  = selected,
                            onClick   = { onCategoriaChange(cat) },
                            label     = { Text(cat.nombre, fontSize = 11.sp) },
                            leadingIcon = {
                                Icon(cat.icono, null, modifier = Modifier.size(14.dp))
                            },
                            colors    = FilterChipDefaults.filterChipColors(
                                selectedContainerColor   = FinanceYellow,
                                selectedLabelColor       = Color.Black,
                                selectedLeadingIconColor = Color.Black
                            )
                        )
                    }
                }
            }
        },
        confirmButton = {
            val btnColor = if (tipo == TipoTransaccion.GASTO) FinanceRed else FinanceGreen
            Button(
                onClick = onGuardar,
                colors  = ButtonDefaults.buttonColors(
                    containerColor = btnColor,
                    contentColor   = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
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

// ─── COMPONENTES DE LA LISTA ──────────────────────────────────────────────────
@Composable
fun TotalCard(gastos: Double, ingresos: Double) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
        colors   = CardDefaults.cardColors(containerColor = FinanceSurface),
        shape    = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Gasto Total (Este mes)", color = Color.Gray, fontSize = 14.sp)
                Icon(Icons.Default.MoreHoriz, null, tint = Color.Gray)
            }
            Text("$${String.format("%.2f", gastos)}",
                fontSize = 36.sp, fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.TrendingDown, null, tint = FinanceRed, modifier = Modifier.size(16.dp))
                    Text(" Gastos: $${String.format("%.2f", gastos)}",
                        color = FinanceRed, fontSize = 13.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.TrendingUp, null, tint = FinanceGreen, modifier = Modifier.size(16.dp))
                    Text(" Ingresos: $${String.format("%.2f", ingresos)}",
                        color = FinanceGreen, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun CategoryFilters(selectedCategory: String, onCategoryClick: (String) -> Unit) {
    val cats = listOf("Todo", "Comida", "Transporte", "Hogar", "Salud", "Ocio", "Nómina", "Varios")
    LazyRow(
        modifier              = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cats) { cat ->
            CategoryButton(cat, cat == selectedCategory) { onCategoryClick(cat) }
        }
    }
}

@Composable
fun CategoryButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick  = onClick,
        shape    = RoundedCornerShape(12.dp),
        color    = if (isSelected) FinanceYellow else FinanceSurface,
        border   = if (isSelected) null else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier.height(40.dp)
    ) {
        Box(modifier = Modifier.padding(horizontal = 20.dp), contentAlignment = Alignment.Center) {
            Text(label,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color      = if (isSelected) Color.Black else Color(0xFF64748B))
        }
    }
}

@Composable
fun TransactionItem(t: Transaccion) {
    val fechaTexto = t.fecha?.format(DateTimeFormatter.ofPattern("dd MMM")) ?: "---"
    Row(
        modifier          = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier.size(48.dp).background(FinanceSurface, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(t.icon, null,
                tint = if (t.tipo == TipoTransaccion.GASTO) Color.Gray else FinanceGreen)
        }
        Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
            Text(t.nombre, fontWeight = FontWeight.Bold)
            Text("$fechaTexto • ${t.categoria}", color = Color.Gray, fontSize = 12.sp)
        }
        Text(
            text       = (if (t.tipo == TipoTransaccion.GASTO) "-" else "+") +
                    "$${String.format("%.2f", t.cantidad)}",
            fontWeight = FontWeight.Bold,
            color      = if (t.tipo == TipoTransaccion.GASTO) Color.Black else FinanceGreen
        )
    }
}

@Composable
fun TrendSection() {
    Column {
        Text("Tendencia", fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth().height(120.dp).padding(top = 16.dp)
                .background(FinanceSurface.copy(0.5f), RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment     = Alignment.Bottom
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
        Box(modifier = Modifier.width(20.dp).fillMaxHeight(fraction)
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSelected) FinanceYellow else FinanceYellow.copy(0.3f)))
        Text(label, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
    }
}