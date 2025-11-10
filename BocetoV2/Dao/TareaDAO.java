import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TareaDAO {
    private static final List<Tarea> tareas = new ArrayList<>();
    private static TareaDAO instance;

    public static TareaDAO getInstance() {
        if (instance == null) {
            instance = new TareaDAO();
            // Cargar tareas del usuario de prueba de UsuarioDAO (solo para simular)
            Usuario usuarioJuan = UsuarioDAO.getInstance().obtenerTodos().stream()
                                    .filter(u -> u.getNombre().equals("Juan Pérez"))
                                    .findFirst().orElse(null);
            if (usuarioJuan != null) {
                tareas.addAll(usuarioJuan.getTareas());
            }
        }
        return instance;
    }

    // Métodos CRUD
    public Optional<Tarea> obtenerPorId(String id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
    
    public Tarea guardar(Tarea tarea) {
        tareas.removeIf(t -> t.getId().equals(tarea.getId()));
        tareas.add(tarea);
        return tarea;
    }
    
    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }
    
    public void eliminar(String id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}