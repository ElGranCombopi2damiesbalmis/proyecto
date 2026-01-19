package com.pmdm.planify.data.daomocks

<<<<<<< HEAD:App/AppV2/app/src/main/java/com/pmdm/appV1/data/daomocks/EconomiaDaoMock.kt
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.pmdm.appV1.data.mocks.EconomiaMock
import com.pmdm.appV1.models.TipoTransaccion
import com.pmdm.appV1.models.Transaccion
import java.time.LocalDateTime
=======
import com.pmdm.planify.data.mocks.EconomiaMock
>>>>>>> ayman:App/AppV2/app/src/main/java/com/pmdm/planify/data/daomocks/EconomiaDaoMock.kt

class EconomiaDaoMock {
    // Inicializamos con datos de prueba para verlos en la UI
    val economia = EconomiaMock(
        saldo = 1240.50, // Saldo inicial coincidente con tu imagen
        historialTransacciones = mutableListOf(
            Transaccion(
                nombre = "McDonalds",
                fecha = LocalDateTime.now().withHour(14).withMinute(0),
                categoria = "Comida",
                cantidad = 15.00,
                tipo = TipoTransaccion.GASTO,
                icon = Icons.Default.Restaurant
            ),
            Transaccion(
                nombre = "Gold's Gym",
                fecha = LocalDateTime.now().minusDays(1),
                categoria = "Salud",
                cantidad = 45.00,
                tipo = TipoTransaccion.GASTO,
                icon = Icons.Default.FitnessCenter
            ),
            Transaccion(
                nombre = "Uber Trip",
                fecha = LocalDateTime.now().minusDays(2),
                categoria = "Transporte",
                cantidad = 12.50,
                tipo = TipoTransaccion.GASTO,
                icon = Icons.Default.LocalTaxi
            ),
            Transaccion(
                nombre = "Reembolso",
                fecha = LocalDateTime.now().minusDays(3),
                categoria = "Varios",
                cantidad = 32.00,
                tipo = TipoTransaccion.INGRESO,
                icon = Icons.Default.AttachMoney
            )
        )
    )
}