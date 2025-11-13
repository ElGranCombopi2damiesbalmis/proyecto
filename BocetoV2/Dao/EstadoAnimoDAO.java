import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EstadoAnimoDAO {
    private static final Map<Date, IconoEstadoAnimo> registroAnimoGlobal = new HashMap<>();
    private static EstadoAnimoDAO instance;

    public static EstadoAnimoDAO getInstance() {
        if (instance == null) {
            instance = new EstadoAnimoDAO();
            inicializarDatos(); 
        }
        return instance;
    }

    private static void inicializarDatos() {
        // Historial de Estado de Ánimo para los últimos 5 días
        registroAnimoGlobal.put(DateUtils.limpiarHora(DateUtils.getDateOffset(0)), IconoEstadoAnimo.GENIAL);
        registroAnimoGlobal.put(DateUtils.limpiarHora(DateUtils.getDateOffset(-1)), IconoEstadoAnimo.BIEN);
        registroAnimoGlobal.put(DateUtils.limpiarHora(DateUtils.getDateOffset(-2)), IconoEstadoAnimo.NORMAL);
        registroAnimoGlobal.put(DateUtils.limpiarHora(DateUtils.getDateOffset(-3)), IconoEstadoAnimo.MAL);
        registroAnimoGlobal.put(DateUtils.limpiarHora(DateUtils.getDateOffset(-4)), IconoEstadoAnimo.MUYMAL);
    }
    
    // Métodos CRUD (se mantienen igual que en la refactorización anterior)
    public void guardarRegistro(Date fecha, IconoEstadoAnimo icono) {
        registroAnimoGlobal.put(DateUtils.limpiarHora(fecha), icono);
    }

    public Optional<IconoEstadoAnimo> obtenerPorFecha(Date fecha) {
        return Optional.ofNullable(registroAnimoGlobal.get(DateUtils.limpiarHora(fecha)));
    }

    public Map<Date, IconoEstadoAnimo> obtenerHistorial() {
        return new HashMap<>(registroAnimoGlobal);
    }
}