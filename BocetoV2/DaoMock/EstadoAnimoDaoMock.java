import java.time.LocalDate;

public class EstadoAnimoDaoMock extends EstadoAnimoDao {
    public EstadoAnimoDaoMock() {
        super();
        EstadoAnimo estado = new EstadoAnimo();
        // Dato de prueba inicial
        estado.getRegistroAnimo().put(LocalDate.now(), IconoEstadoAnimo.BIEN);
        this.guardar(estado);
    }
}