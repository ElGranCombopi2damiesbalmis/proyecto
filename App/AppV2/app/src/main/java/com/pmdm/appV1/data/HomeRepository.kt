package com.pmdm.appV1.data

import com.pmdm.appV1.data.daomocks.HomeDaoMock
import com.pmdm.appV1.models.Home

class HomeRepository {
    private val dao = HomeDaoMock()

    fun get(): Home = dao.home.toHome()

    fun update(home: Home) {
        val mock = home.toHomeMock()
        dao.home.fraseBienvenida = mock.fraseBienvenida
        dao.home.notificacionesPendientes = mock.notificacionesPendientes
    }
}