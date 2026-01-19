package com.pmdm.appV1.models

import java.time.LocalDate

data class Economia(
    var saldo: Double = 0.0,
    var historialTransacciones: MutableList<Transaccion> = mutableListOf()
) {

    // Lógica de Negocio: Recalcular saldo basado en la lista interna
    fun recalcularSaldo() {
        val ingresos = historialTransacciones.filter { it.tipo == TipoTransaccion.INGRESO }.sumOf { it.cantidad }
        val gastos = historialTransacciones.filter { it.tipo == TipoTransaccion.GASTO }.sumOf { it.cantidad }
        this.saldo = ingresos - gastos
    }

    fun agregarTransaccion(transaccion: Transaccion) {
        historialTransacciones.add(transaccion)

        // Actualizamos el saldo inmediatamente
        if (transaccion.tipo == TipoTransaccion.INGRESO) {
            saldo += transaccion.cantidad
        } else {
            saldo -= transaccion.cantidad
        }
    }

    // --- Métodos para la UI (Filtros) ---

    fun obtenerGastos(): List<Transaccion> {
        return historialTransacciones.filter { it.tipo == TipoTransaccion.GASTO }
    }

    fun obtenerIngresos(): List<Transaccion> {
        return historialTransacciones.filter { it.tipo == TipoTransaccion.INGRESO }
    }

    fun filtrarPorCategoria(categoria: String): List<Transaccion> {
        if (categoria == "Todo") return historialTransacciones
        return historialTransacciones.filter { it.categoria == categoria }
    }
}