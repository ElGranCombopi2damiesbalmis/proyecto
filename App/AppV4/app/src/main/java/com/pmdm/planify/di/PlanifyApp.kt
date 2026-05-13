package com.pmdm.planify.di

import android.app.Application
import com.pmdm.planify.data.EstadoAnimoRepository
import com.pmdm.planify.data.TareaRepository
import com.pmdm.planify.data.TransaccionRepository
import com.pmdm.planify.data.UsuarioRepository
import com.pmdm.planify.models.EtiquetaTarea
import com.pmdm.planify.models.IconoEstadoAnimo
import com.pmdm.planify.models.Tarea
import com.pmdm.planify.models.TipoTransaccion
import com.pmdm.planify.models.Transaccion
import com.pmdm.planify.models.Usuario
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Restaurant
import java.security.MessageDigest

@HiltAndroidApp
class PlanifyApp : Application() {
    @Inject lateinit var usuarioRepository: UsuarioRepository
    @Inject lateinit var tareaRepository: TareaRepository
    @Inject lateinit var transaccionRepository: TransaccionRepository
    @Inject lateinit var estadoAnimoRepository: EstadoAnimoRepository

    // Función para cifrar la contraseña en SHA-256
    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    override fun onCreate() {
        super.onCreate()
        runBlocking {

            // 1. SEMILLA DE USUARIOS
            val passwordUniversal = hashPassword("1234") // A partir de ahora la contraseña será "1234" para todos

            // Verificamos si existe Ayman. Si no, lo creamos (sin necesidad de borrar datos)
            if (usuarioRepository.get("ayman@email.com") == null) {
                usuarioRepository.insert(
                    Usuario(nombre = "Ayman", email = "ayman@email.com", password = passwordUniversal, telefono = "600000001", calle = "Calle de Ayman 1")
                )
            }

            // Verificamos a Victor
            if (usuarioRepository.get("victor@ejemplo.com") == null) {
                usuarioRepository.insert(
                    Usuario(nombre = "Victor", email = "victor@ejemplo.com", password = passwordUniversal, telefono = "600000002", calle = "Avenida de Victor 2")
                )
            }

            // Verificamos a Andrea
            if (usuarioRepository.get("andrea@planify.com") == null) {
                usuarioRepository.insert(
                    Usuario(nombre = "Andrea", email = "andrea@planify.com", password = passwordUniversal, telefono = "600123456", calle = "Calle Principal 1")
                )
            }

            // 2. SEMILLA DE TAREAS
            if (tareaRepository.count() == 0) {
                tareaRepository.insert(Tarea(titulo = "Comprar comida", descripcion = "Fruta, verduras y agua", fecha = LocalDateTime.now().plusDays(2), etiqueta = EtiquetaTarea.HOGAR))
                tareaRepository.insert(Tarea(titulo = "Entrenar torso", descripcion = "Rutina de 50 min", fecha = LocalDateTime.now().plusHours(6), etiqueta = EtiquetaTarea.SALUD, completada = true))
            }

            // 3. SEMILLA DE TRANSACCIONES
            if (transaccionRepository.count() == 0) {
                transaccionRepository.insert(Transaccion(nombre = "Supermercado", fecha = LocalDateTime.now().minusDays(1), categoria = "Comida", cantidad = 34.50, tipo = TipoTransaccion.GASTO, icon = Icons.Default.Restaurant))
                transaccionRepository.insert(Transaccion(nombre = "Nómina", fecha = LocalDateTime.now().minusDays(3), categoria = "Nómina", cantidad = 1250.0, tipo = TipoTransaccion.INGRESO, icon = Icons.Default.Payments))
                transaccionRepository.insert(Transaccion(nombre = "Gasolina", fecha = LocalDateTime.now().minusDays(2), categoria = "Transporte", cantidad = 50.0, tipo = TipoTransaccion.GASTO, icon = Icons.Default.DirectionsCar))
            }

            // 4. SEMILLA DE ESTADO DE ÁNIMO
            if (estadoAnimoRepository.count() == 0) {
                estadoAnimoRepository.registrar(LocalDate.now(), IconoEstadoAnimo.BIEN)
                estadoAnimoRepository.registrar(LocalDate.now().minusDays(1), IconoEstadoAnimo.GENIAL)
                estadoAnimoRepository.registrar(LocalDate.now().minusDays(3), IconoEstadoAnimo.MAL)
                estadoAnimoRepository.registrar(LocalDate.now().minusDays(5), IconoEstadoAnimo.MUYMAL)
            }
        }
    }
}