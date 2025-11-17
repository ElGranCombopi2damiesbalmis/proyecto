import java.util.ArrayList;
import java.util.List;

public class EconomiaDao {
    protected List<Economia> listaEconomias;

    public EconomiaDao() {
        this.listaEconomias = new ArrayList<>();
    }

    public void guardar(Economia economia) {
        listaEconomias.add(economia);
    }

    // Asumimos gestión de una economía principal para el ejemplo
    public Economia obtenerPrincipal() {
        if (listaEconomias.isEmpty()) return null;
        return listaEconomias.get(0);
    }

    public void actualizarSaldo(Economia economia, double nuevoSaldo) {
        economia.setSaldo(nuevoSaldo);
        // En base de datos real aquí se haría un update
    }
}