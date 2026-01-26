package com.pmdm.planify.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pmdm.planify.ui.LoginScreen
import com.pmdm.planify.ui.theme.PlanifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanifyTheme {
                LoginScreen(onLoginSuccess = { })
            }
        }
    }
}