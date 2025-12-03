import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EconomiaDao {
    protected List<Transaccion> listaTransacciones;

    public EconomiaDao() {
        this.listaTransacciones = new ArrayList<>();
    }

    public void guardarTransaccion(Transaccion transaccion) {
        listaTransacciones.add(transaccion);
    }

    public List<Transaccion> obtenerTodasLasTransacciones() {
        return new ArrayList<>(listaTransacciones);
    }

    public List<Transaccion> obtenerTransaccionesPorTipo(TipoTransaccion tipo) {
        return listaTransacciones.stream()
                .filter(t -> t.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public List<Transaccion> obtenerTransaccionesPorFecha(LocalDate fecha) {
        return listaTransacciones.stream()
                .filter(t -> t.getFecha().toLocalDate().equals(fecha))
                .collect(Collectors.toList());
    }

    // Métodos para editar o eliminar transacciones
    public void editarTransaccion(Transaccion transaccionVieja, Transaccion transaccionNueva) {
        int index = listaTransacciones.indexOf(transaccionVieja);
        if (index != -1) {
            listaTransacciones.set(index, transaccionNueva);
        }
    }

    public void eliminarTransaccion(Transaccion transaccion) {
        listaTransacciones.remove(transaccion);
    }
}