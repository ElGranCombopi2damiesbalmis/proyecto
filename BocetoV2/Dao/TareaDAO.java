import java.util.ArrayList;
import java.util.List;

public class TareaDao {
    protected List<Tarea> listaTareas;

    public TareaDao() {
        this.listaTareas = new ArrayList<>();
    }

    public void guardar(Tarea tarea) {
        listaTareas.add(tarea);
    }

    public Tarea obtenerPorId(String id) {
        return listaTareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(listaTareas);
    }

    public void actualizar(Tarea tarea) {
         for (int i = 0; i < listaTareas.size(); i++) {
            if (listaTareas.get(i).getId().equals(tarea.getId())) {
                listaTareas.set(i, tarea);
                return;
            }
        }
    }

    public void eliminar(String id) {
        listaTareas.removeIf(t -> t.getId().equals(id));
    }
}