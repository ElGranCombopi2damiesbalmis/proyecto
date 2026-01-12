package com.pmdm.appV1.models


import java.time.LocalDate

data class EstadoAnimo(
    var registroAnimo: MutableMap<LocalDate, IconoEstadoAnimo> = mutableMapOf()
)