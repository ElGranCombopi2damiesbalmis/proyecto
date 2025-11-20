import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EstadoAnimo {
    private Map<LocalDate, IconoEstadoAnimo> registroAnimo;

    public EstadoAnimo() {
        this.registroAnimo = new HashMap<>();
    }

    public Map<LocalDate, IconoEstadoAnimo> getRegistroAnimo() { return registroAnimo; }
    public void setRegistroAnimo(Map<LocalDate, IconoEstadoAnimo> registroAnimo) { this.registroAnimo = registroAnimo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoAnimo that = (EstadoAnimo) o;
        return Objects.equals(registroAnimo, that.registroAnimo);
    }

    @Override
    public int hashCode() { return Objects.hash(registroAnimo); }

    @Override
    public String toString() { return "EstadoAnimo{" + "registros=" + registroAnimo.size() + '}'; }
}