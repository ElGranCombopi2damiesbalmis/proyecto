import java.time.LocalDateTime;

public class TransaccionDaoMock extends TransaccionDao {
    public TransaccionDaoMock() {
        super();
        // Datos de prueba del Program.cs original
        this.guardar(new Transaccion("Salario", 2000, LocalDateTime.now(), TipoTransaccion.INGRESO));
        this.guardar(new Transaccion("Supermercado", 150, LocalDateTime.now(), TipoTransaccion.GASTO));
        this.guardar(new Transaccion("Netflix", 15, LocalDateTime.now(), TipoTransaccion.GASTO));
    }
}