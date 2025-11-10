import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TareaController {
    private final TareaDAO tareaDAO;

    public TareaController(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    public Tarea crearNuevaTarea(String titulo, String descripcion, Date fecha, EtiquetaTarea etiqueta) {
        Tarea nuevaTarea = new Tarea(titulo, descripcion, fecha, etiqueta);
        return tareaDAO.guardar(nuevaTarea);
    }

    public Optional<Tarea> obtenerTareaPorId(String id) {
        return tareaDAO.obtenerPorId(id);
    }

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaDAO.obtenerTodas();
    }

    public boolean marcarTareaComoCompletada(String id) {
        Optional<Tarea> tareaOpt = tareaDAO.obtenerPorId(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.marcarComoCompletada();
            tareaDAO.guardar(tarea); // Persistir el cambio de estado
            return true;
        }
        return false;
    }

    public boolean editarTarea(String id, String titulo, String descripcion, Date fecha, EtiquetaTarea etiqueta) {
        Optional<Tarea> tareaOpt = tareaDAO.obtenerPorId(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.editarTarea(titulo, descripcion, fecha, etiqueta);
            tareaDAO.guardar(tarea); // Persistir los cambios
            return true;
        }
        return false;
    }

    public void eliminarTarea(String id) {
        tareaDAO.eliminar(id);
    }
}