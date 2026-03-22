
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

@HiltAndroidApp
class PlanifyApp : Application() {
    @Inject lateinit var usuarioRepository: UsuarioRepository
    @Inject lateinit var tareaRepository: TareaRepository
    @Inject lateinit var transaccionRepository: TransaccionRepository
    @Inject lateinit var estadoAnimoRepository: EstadoAnimoRepository

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if (usuarioRepository.count() == 0) {
                usuarioRepository.insert(
                    Usuario(
                        nombre = "Andrea",
                        email = "andrea@planify.com",
                        password = "123456".hashCode().toString(),
                        telefono = "600123123",
                        calle = "Calle Mayor, 12"
                    )
                )
            }
            if (tareaRepository.count() == 0) {
                tareaRepository.insert(Tarea(titulo = "Preparar examen", descripcion = "Repasar Room y navegación", fecha = LocalDateTime.now().plusDays(1), etiqueta = EtiquetaTarea.ESTUDIO))
                tareaRepository.insert(Tarea(titulo = "Comprar comida", descripcion = "Fruta, verduras y agua", fecha = LocalDateTime.now().plusDays(2), etiqueta = EtiquetaTarea.HOGAR))
                tareaRepository.insert(Tarea(titulo = "Entrenar torso", descripcion = "Rutina de 50 min", fecha = LocalDateTime.now().plusHours(6), etiqueta = EtiquetaTarea.SALUD, completada = true))
            }
            if (transaccionRepository.count() == 0) {
                transaccionRepository.insert(Transaccion(nombre = "Supermercado", fecha = LocalDateTime.now().minusDays(1), categoria = "Comida", cantidad = 34.50, tipo = TipoTransaccion.GASTO, icon = Icons.Default.Restaurant))
                transaccionRepository.insert(Transaccion(nombre = "Nómina", fecha = LocalDateTime.now().minusDays(3), categoria = "Nómina", cantidad = 1250.0, tipo = TipoTransaccion.INGRESO, icon = Icons.Default.Payments))
                transaccionRepository.insert(Transaccion(nombre = "Gasolina", fecha = LocalDateTime.now().minusDays(2), categoria = "Transporte", cantidad = 50.0, tipo = TipoTransaccion.GASTO, icon = Icons.Default.DirectionsCar))
            }
            if (estadoAnimoRepository.count() == 0) {
                estadoAnimoRepository.registrar(LocalDate.now(), IconoEstadoAnimo.BIEN)
                estadoAnimoRepository.registrar(LocalDate.now().minusDays(1), IconoEstadoAnimo.GENIAL)
                estadoAnimoRepository.registrar(LocalDate.now().minusDays(2), IconoEstadoAnimo.NORMAL)
            }
        }
    }
}
