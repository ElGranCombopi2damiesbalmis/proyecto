import com.pmdm.appV1.data.daomocks.EconomiaDaoMock

class Economia(private val economiaDao: EconomiaDaoMock) {
    var saldo: Double = 0.0
        private set

    init {
        saldo = calcularSaldoInicial()
    }

    private fun calcularSaldoInicial(): Double {
        val transacciones = economiaDao.obtenerTodasLasTransacciones()
        val ingresos = transacciones.filter { it.tipo == TipoTransaccion.INGRESO }.sumOf { it.cantidad }
        val gastos = transacciones.filter { it.tipo == TipoTransaccion.GASTO }.sumOf { it.cantidad }
        return ingresos - gastos
    }

    fun agregarTransaccion(transaccion: Transaccion) {
        // En Android, esto se haría usualmente a través de un ViewModel
        economiaDao.guardarTransaccion(transaccion)

        if (transaccion.tipo == TipoTransaccion.INGRESO) {
            saldo += transaccion.cantidad
        } else {
            saldo -= transaccion.cantidad
        }
    }

    // Métodos de filtrado que retornan listas para la UI
    fun obtenerGastos(): List<Transaccion> = economiaDao.obtenerTransaccionesPorTipo(TipoTransaccion.GASTO)
    fun obtenerIngresos(): List<Transaccion> = economiaDao.obtenerTransaccionesPorTipo(TipoTransaccion.INGRESO)
}