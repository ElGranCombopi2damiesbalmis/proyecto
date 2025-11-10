import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class EstadoAnimoController {
    private final EstadoAnimoDAO estadoAnimoDAO;

    public EstadoAnimoController(EstadoAnimoDAO estadoAnimoDAO) {
        this.estadoAnimoDAO = estadoAnimoDAO;
    }

    public void registrarEstado(Date fecha, IconoEstadoAnimo icono) {
        estadoAnimoDAO.registrar(fecha, icono);
    }

    public Optional<IconoEstadoAnimo> consultarEstadoPorFecha(Date fecha) {
        return estadoAnimoDAO.obtenerPorFecha(fecha);
    }

    public Map<Date, IconoEstadoAnimo> obtenerHistorial() {
        return estadoAnimoDAO.obtenerHistorial();
    }
}