package com.pmdm.appV2.data.mocks

import com.pmdm.appV2.models.IconoEstadoAnimo
import java.time.LocalDate

data class EstadoAnimoMock(
    var registroAnimo: MutableMap<LocalDate, IconoEstadoAnimo> = mutableMapOf()
)