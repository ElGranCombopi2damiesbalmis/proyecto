package com.pmdm.appV1.data.mocks

import com.pmdm.appV1.models.IconoEstadoAnimo
import java.time.LocalDate

data class EstadoAnimoMock(
    var registroAnimo: MutableMap<LocalDate, IconoEstadoAnimo> = mutableMapOf()
)