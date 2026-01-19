package com.pmdm.planify.data

<<<<<<< HEAD:App/AppV2/app/src/main/java/com/pmdm/appV1/data/TransaccionRepositoryConverter.kt
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import com.pmdm.appV1.data.mocks.TransaccionMock
import com.pmdm.appV1.models.Transaccion
=======
import com.pmdm.planify.data.mocks.TransaccionMock
import com.pmdm.planify.models.Transaccion
>>>>>>> ayman:App/AppV2/app/src/main/java/com/pmdm/planify/data/TransaccionRepositoryConverter.kt

fun Transaccion.toTransaccionMock() = TransaccionMock(
    id = id,
    descripcion = nombre,
    cantidad = cantidad,
    fecha = fecha,
    tipo = tipo
)

fun TransaccionMock.toTransaccion() = Transaccion(
    id = id,
    nombre = descripcion,
    cantidad = cantidad,
    fecha = fecha,
    tipo = tipo,
    categoria = "Varios", // Valor por defecto ya que el Mock no tiene este campo
    icon = Icons.Default.Help // Icono por defecto
)