package com.pmdm.planify.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pmdm.planify.ui.navegation.NavHostPlanify
import com.pmdm.planify.ui.theme.PlanifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Imprescindible para que funcione Hilt (Inyección de dependencias)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanifyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Llamamos directamente al NavHost para iniciar el sistema de rutas
                    NavHostPlanify()
                        /*EstadoDeAnimoContent(
                            historial = emptyMap() // Pasamos un mapa vacío, se rellenará con los datos dummy del calendario
                        )*/
                }
            }
        }
    }
}