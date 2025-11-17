public class EconomiaDaoMock extends EconomiaDao {
    public EconomiaDaoMock() {
        super();
        Economia economia = new Economia();
        // Nota: Las transacciones se cargan usualmente a través de la relación,
        // pero aquí inicializamos el objeto base.
        economia.setSaldo(1835); // Saldo calculado (2000 - 150 - 15)
        this.guardar(economia);
    }
}