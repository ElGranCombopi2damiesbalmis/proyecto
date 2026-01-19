import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.AttachMoney
import com.pmdm.appV1.data.mocks.TipoTransaccion
import com.pmdm.appV1.data.mocks.Transaccion

fun TransaccionDaoMock(): List<Transaccion> {
    return listOf(
        Transaccion(
            nombre = "McDonalds",
            fecha = "Hoy, 14:00",
            categoria = "Comida",
            cantidad = 15.00,
            tipo = TipoTransaccion.GASTO,
            icon = Icons.Default.Restaurant
        ),
        Transaccion(
            nombre = "Gold's Gym",
            fecha = "Ayer",
            categoria = "Salud",
            cantidad = 45.00,
            tipo = TipoTransaccion.GASTO,
            icon = Icons.Default.FitnessCenter
        ),
        Transaccion(
            nombre = "Uber Trip",
            fecha = "2 Oct",
            categoria = "Transporte",
            cantidad = 12.50,
            tipo = TipoTransaccion.GASTO,
            icon = Icons.Default.LocalTaxi
        ),
        Transaccion(
            nombre = "Reembolso",
            fecha = "1 Oct",
            categoria = "Varios",
            cantidad = 32.00,
            tipo = TipoTransaccion.INGRESO,
            icon = Icons.Default.AttachMoney
        )
    )
}