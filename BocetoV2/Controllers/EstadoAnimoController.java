import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class EstadoAnimoController {

    public void registrarEstadoAnimo(EstadoAnimo estadoAnimo, LocalDate fecha, IconoEstadoAnimo icono) {
        estadoAnimo.getRegistroAnimo().put(fecha, icono);
        System.out.println("Estado de ánimo registrado para " + fecha + ": " + icono);
    }

    public IconoEstadoAnimo obtenerEstadoAnimo(EstadoAnimo estadoAnimo, LocalDate fecha) {
        return estadoAnimo.getRegistroAnimo().getOrDefault(fecha, IconoEstadoAnimo.NORMAL);
    }

    public void mostrarHistorial(EstadoAnimo estadoAnimo) {
        System.out.println("=== HISTORIAL DE ESTADO DE ÁNIMO ===");
        Map<LocalDate, IconoEstadoAnimo> ordenado = new TreeMap<>(estadoAnimo.getRegistroAnimo());
        for (Map.Entry<LocalDate, IconoEstadoAnimo> entry : ordenado.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}