import java.time.LocalDate;
import java.util.Map;

public class EstadoAnimoController {
    private EstadoAnimoDao estadoAnimoDao;

    public EstadoAnimoController(EstadoAnimoDao estadoAnimoDao) {
        this.estadoAnimoDao = estadoAnimoDao;
    }

    public void registrarNuevoAnimo(EstadoAnimo estadoAnimo, IconoEstadoAnimo icono) {
        LocalDate hoy = LocalDate.now();
        estadoAnimo.getRegistroAnimo().put(hoy, icono);
        estadoAnimoDao.actualizar(estadoAnimo);
        System.out.println("Estado de ánimo guardado: " + icono);
    }

    public void mostrarCalendario(EstadoAnimo estadoAnimo) {
        System.out.println("=== CALENDARIO DE ÁNIMO ===");
        for (Map.Entry<LocalDate, IconoEstadoAnimo> entry : estadoAnimo.getRegistroAnimo().entrySet()) {
            System.out.println("Fecha: " + entry.getKey() + " | Estado: " + entry.getValue());
        }
    }
}