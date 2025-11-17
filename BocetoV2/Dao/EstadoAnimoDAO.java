import java.util.ArrayList;
import java.util.List;

public class EstadoAnimoDao {
    protected List<EstadoAnimo> listaEstados;

    public EstadoAnimoDao() {
        this.listaEstados = new ArrayList<>();
    }

    public void guardar(EstadoAnimo estadoAnimo) {
        listaEstados.add(estadoAnimo);
    }

    public EstadoAnimo obtenerPrincipal() {
        return listaEstados.isEmpty() ? null : listaEstados.get(0);
    }
    
    public void actualizar(EstadoAnimo estadoAnimo) {
        // Lógica de persistencia si fuera DB
    }
}