package com.pmdm.planify.data

import com.pmdm.planify.data.daomocks.HomeDaoMock
import com.pmdm.planify.models.Home
import javax.inject.Inject

class HomeRepository @Inject constructor(){
    private val dao = HomeDaoMock()

    fun get(): Home = dao.home.toHome()

    fun update(home: Home) {
        val mock = home.toHomeMock()
        dao.home.fraseBienvenida = mock.fraseBienvenida
        dao.home.notificacionesPendientes = mock.notificacionesPendientes
    }
}