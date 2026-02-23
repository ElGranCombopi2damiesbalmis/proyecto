package com.pmdm.planify.ui.features

sealed class PlanifyEvent {
    // Navegación y Login
    object OnLoginClick : PlanifyEvent()
    object OnBack : PlanifyEvent()

    // Economía
    data class OnSeleccionarTransaccion(val id: Int) : PlanifyEvent()
    object OnNuevaTransaccion : PlanifyEvent()
    object OnGuardarTransaccion : PlanifyEvent()

    // Tareas
    data class OnTareaClick(val id: Int) : PlanifyEvent()
    data class OnCambiarCheckTarea(val id: Int, val completada: Boolean) : PlanifyEvent()

    // Estado de Ánimo
    data class OnCambiarAnimo(val nuevoEstado: String) : PlanifyEvent()

    // Menú y Configuración
    object OnAbrirMenu : PlanifyEvent()
    object OnCerrarMenu : PlanifyEvent()
    data class OnUpdateSetting(val key: String, val value: Boolean) : PlanifyEvent()
}