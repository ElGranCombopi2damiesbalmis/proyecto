package com.pmdm.appV2.data

import com.pmdm.appV2.data.mocks.EstadoAnimoMock
import com.pmdm.appV2.models.EstadoAnimo

fun EstadoAnimo.toEstadoAnimoMock() = EstadoAnimoMock(
    registroAnimo = registroAnimo // Map<LocalDate, IconoEstadoAnimo> se puede pasar directo si es mutable
)

fun EstadoAnimoMock.toEstadoAnimo() = EstadoAnimo(
    registroAnimo = registroAnimo
)