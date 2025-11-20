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

    public Economia obtenerPrincipal() {
        if (listaEconomias.isEmpty()) return null;
        return listaEconomias.get(0);
    }

    public void actualizarSaldo(Economia economia, double nuevoSaldo) {
        economia.setSaldo(nuevoSaldo);
    }
}