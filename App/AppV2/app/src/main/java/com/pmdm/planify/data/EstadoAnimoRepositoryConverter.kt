package com.pmdm.planify.data

import com.pmdm.planify.data.mocks.EstadoAnimoMock
import com.pmdm.planify.models.EstadoAnimo

fun EstadoAnimo.toEstadoAnimoMock() = EstadoAnimoMock(
    registroAnimo = registroAnimo // Map<LocalDate, IconoEstadoAnimo> se puede pasar directo si es mutable
)

fun EstadoAnimoMock.toEstadoAnimo() = EstadoAnimo(
    registroAnimo = registroAnimo
)