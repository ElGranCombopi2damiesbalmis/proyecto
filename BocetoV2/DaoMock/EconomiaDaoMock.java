import java.time.LocalDateTime;

public class EconomiaDaoMock extends EconomiaDao {
    public EconomiaDaoMock() {
        super();
        // Datos de prueba - transacciones iniciales
        this.guardarTransaccion(new Transaccion("Salario", 2000, LocalDateTime.now(), TipoTransaccion.INGRESO));
        this.guardarTransaccion(new Transaccion("Supermercado", 150, LocalDateTime.now(), TipoTransaccion.GASTO));
        this.guardarTransaccion(new Transaccion("Netflix", 15, LocalDateTime.now(), TipoTransaccion.GASTO));
        // Saldo calculado: 2000 - 150 - 15 = 1835
    }
}