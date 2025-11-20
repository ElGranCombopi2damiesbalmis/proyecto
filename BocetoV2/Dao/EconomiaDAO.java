import java.time.LocalDate;
import java.util.List;

public interface EconomiaDao {
    void guardarTransaccion(Transaccion transaccion);
    List<Transaccion> obtenerTodasLasTransacciones();
    List<Transaccion> obtenerTransaccionesPorTipo(TipoTransaccion tipo);
    List<Transaccion> obtenerTransaccionesPorFecha(LocalDate fecha);
    // Podrían agregarse métodos para editar o eliminar
}