package com.pmdm.planify.models


import java.time.LocalDate

data class EstadoAnimo(
    var registroAnimo: MutableMap<LocalDate, IconoEstadoAnimo> = mutableMapOf()
)