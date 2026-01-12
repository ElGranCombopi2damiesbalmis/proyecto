package com.pmdm.appV1.models

import java.time.LocalDate

data class EstadoAnimo(
    // En Kotlin usamos MutableMap para indicar que se puede modificar
    var registroAnimo: MutableMap<LocalDate, IconoEstadoAnimo> = mutableMapOf()
) {
    override fun toString(): String {
        return "EstadoAnimo(registros=${registroAnimo.size})"
    }
}
