import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EstadoAnimo {
    // Almacena el estado de ánimo por fecha (sin hora)
    private Map<Date, IconoEstadoAnimo> registroAnimo;

    public EstadoAnimo() {
        this.registroAnimo = new HashMap<>();
    }

    /**
     * Utilidad interna para limpiar la hora de un objeto Date.
     * Esto no es una "lógica de negocio", sino una necesidad de la estructura del Map.
     */
    public static Date limpiarHora(Date fecha) {
        long time = fecha.getTime();
        long days = TimeUnit.MILLISECONDS.toHours(time) / 24;
        return new Date(TimeUnit.DAYS.toMillis(days));
    }

    // Getters y Setters
    public Map<Date, IconoEstadoAnimo> getRegistroAnimo() {
        return registroAnimo;
    }

    public void setRegistroAnimo(Map<Date, IconoEstadoAnimo> registroAnimo) {
        this.registroAnimo = registroAnimo;
    }

    // Se eliminan los métodos registrarEstadoAnimo y obtenerEstadoAnimo
    // ya que su lógica será manejada por el Controller a través del DAO.
}