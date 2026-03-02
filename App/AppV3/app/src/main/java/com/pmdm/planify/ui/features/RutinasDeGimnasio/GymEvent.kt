package com.pmdm.planify.ui.features.RutinasDeGimnasio

import com.pmdm.planify.models.Rutina

sealed class GymEvent {
    // Evento que se dispara al pulsar "Ver Video"
    data class OnPlayVideo(val rutina: Rutina) : GymEvent()
}