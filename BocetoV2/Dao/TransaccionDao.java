import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransaccionDao {
    protected List<Transaccion> listaTransacciones;

    public TransaccionDao() {
        this.listaTransacciones = new ArrayList<>();
    }

    public void guardar(Transaccion transaccion) {
        listaTransacciones.add(transaccion);
    }

    public List<Transaccion> obtenerTodas() {
        return new ArrayList<>(listaTransacciones);
    }

    public List<Transaccion> obtenerPorTipo(TipoTransaccion tipo) {
        return listaTransacciones.stream()
                .filter(t -> t.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    // Al no tener ID, eliminamos por coincidencia de objeto
    public void eliminar(Transaccion transaccion) {
        listaTransacciones.remove(transaccion);
    }
}