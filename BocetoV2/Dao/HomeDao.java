import java.util.ArrayList;
import java.util.List;

public class HomeDao {
    protected List<Home> listaHomes;

    public HomeDao() {
        this.listaHomes = new ArrayList<>();
    }

    public void guardar(Home home) {
        listaHomes.add(home);
    }

    public Home obtenerHomePorUsuario(String fraseBienvenida) {
        // Simulación de búsqueda
        return listaHomes.stream()
                .filter(h -> h.getFraseBienvenida().contains(fraseBienvenida))
                .findFirst().orElse(null);
    }
    
    public Home obtenerPrimero() {
         return listaHomes.isEmpty() ? null : listaHomes.get(0);
    }
}