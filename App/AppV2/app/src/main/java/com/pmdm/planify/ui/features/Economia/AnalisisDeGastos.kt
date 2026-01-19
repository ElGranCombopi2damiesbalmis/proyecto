import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion

// Colores personalizados basados en tu HTML
val PrimaryYellow = Color(0xFFFACC15)
val SurfaceVariant = Color(0xFFF4F4F5)
val SuccessGreen = Color(0xFF16A34A)

@Composable
fun CategoryFilters() {
    val categorias = listOf("Todo", "Comida", "Transporte", "Hogar", "Salud")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(end = 16.dp)
    ) {
        items(categorias) { categoria ->
            CategoryButton(
                label = categoria,
                isSelected = categoria == "Todo" // Simulamos que "Todo" está seleccionado
            )
        }
    }
}

@Composable
fun CategoryButton(label: String, isSelected: Boolean) {
    Surface(
        onClick = { /* TODO: Filtrar lista */ },
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) PrimaryYellow else SurfaceVariant,
        border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier.height(40.dp)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.Black else Color(0xFF64748B)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastosScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
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
            item { HeaderSection() }
            item { TotalCard() }
            item { TrendSection() }
            item { CategoryFilters() }
            item {
                Text(
                    "Movimientos Recientes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            // Aquí integrarías los datos de tu clase Economia
            /*items(getMockTransactions()) { transaction ->
                TransactionItem(transaction)
            }*/
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
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
fun TotalCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Gasto Total (Oct)", color = Color.Gray, fontSize = 14.sp)
                Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = Color.Gray)
            }
            Text("$1,240.50", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(vertical = 8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
                Text("+12% vs mes anterior", color = SuccessGreen, fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}

@Composable
fun TrendSection() {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Tendencia", fontWeight = FontWeight.Bold)
            Text("Octubre 2023", color = Color.Gray, fontSize = 12.sp)
        }
        // Simulación de gráfico de barras simple
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(top = 16.dp)
                .background(SurfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            Bar(0.3f, "Sem 1", false)
            Bar(0.85f, "Sem 2", true)
            Bar(0.45f, "Sem 3", false)
            Bar(0.2f, "Sem 4", false)
        }
    }
}

@Composable
fun Bar(fraction: Float, label: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .fillMaxHeight(fraction)
                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                .background(if (isSelected) PrimaryYellow else PrimaryYellow.copy(alpha = 0.3f))
        )
        Text(label, fontSize = 10.sp, color = if (isSelected) Color.Black else Color.Gray, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun TransactionItem(t: Transaccion) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).background(Color(0xFFF4F4F5), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(t.icon, contentDescription = null, tint = if(t.tipo == TipoTransaccion.GASTO) Color.Gray else SuccessGreen)
        }
        Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
            Text(t.nombre, fontWeight = FontWeight.Bold)
            Text("${t.fecha} • ${t.categoria}", color = Color.Gray, fontSize = 12.sp)
        }
        Text(
            text = (if (t.tipo == TipoTransaccion.GASTO) "-" else "+") + "$${String.format("%.2f", t.cantidad)}",
            fontWeight = FontWeight.Bold,
            color = if (t.tipo == TipoTransaccion.GASTO) Color.Black else SuccessGreen
        )
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = SurfaceVariant.copy(alpha = 0.9f)) {
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.CheckCircle, null) }, label = { Text("Tareas") })
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.FitnessCenter, null) }, label = { Text("Gym") })
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.AccountBalanceWallet, null) },
            label = { Text("Gastos") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = PrimaryYellow.copy(alpha = 0.2f), selectedIconColor = Color.Black)
        )
        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Mood, null) }, label = { Text("Ánimo") })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GastosScreenPreview() {
    // Aplicamos un tema básico para la previsualización
    MaterialTheme {
        // Un contenedor con fondo blanco para que coincida con tu captura
        Box(modifier = Modifier.background(Color.White)) {
            GastosScreen()
        }
    }
}