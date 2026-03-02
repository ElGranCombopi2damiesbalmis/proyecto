package com.pmdm.planify.ui.features.EstadoDeAnimo

import com.pmdm.planify.models.IconoEstadoAnimo
import java.time.LocalDate

sealed class EstadoAnimoEvent {
    // Ahora pasamos la fecha exacta y el ánimo seleccionado
    data class OnSelectMood(val date: LocalDate, val mood: IconoEstadoAnimo) : EstadoAnimoEvent()
}