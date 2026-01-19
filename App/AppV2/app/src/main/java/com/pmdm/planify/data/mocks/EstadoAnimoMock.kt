package com.pmdm.planify.data.mocks

import com.pmdm.planify.models.IconoEstadoAnimo
import java.time.LocalDate

data class EstadoAnimoMock(
    var registroAnimo: MutableMap<LocalDate, IconoEstadoAnimo> = mutableMapOf()
)