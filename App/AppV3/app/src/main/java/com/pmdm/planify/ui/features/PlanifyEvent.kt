package com.pmdm.planify.ui.features

sealed class PlanifyEvent {
    // Navegación y Login (Añadimos email y pass)
    data class OnLoginClick(val email: String, val pass: String) : PlanifyEvent()
    object OnBack : PlanifyEvent()

    // Economía
    data class OnSeleccionarTransaccion(val id: Int) : PlanifyEvent()
    object OnNuevaTransaccion : PlanifyEvent()
    object OnGuardarTransaccion : PlanifyEvent()

    // Tareas
    data class OnTareaClick(val id: Int) : PlanifyEvent()
    data class OnCambiarCheckTarea(val id: Int, val completada: Boolean) : PlanifyEvent()

    // Estado de Ánimo (Ajustado al tipo correcto)
    data class OnCambiarAnimo(val nuevoIcono: com.pmdm.planify.models.IconoEstadoAnimo) : PlanifyEvent()

    // Menú y Configuración
    object OnAbrirMenu : PlanifyEvent()
    object OnCerrarMenu : PlanifyEvent()
    data class OnUpdateSetting(val key: String, val value: Boolean) : PlanifyEvent()
}