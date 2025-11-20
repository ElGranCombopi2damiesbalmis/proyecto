public class TareaController {
    private TareaDao tareaDao;

    public TareaController(TareaDao tareaDao) {
        this.tareaDao = tareaDao;
    }

    public void marcarComoCompletada(String idTarea) {
        Tarea tarea = tareaDao.obtenerPorId(idTarea);
        if (tarea != null) {
            tarea.setCompletada(true);
            tareaDao.actualizar(tarea);
            System.out.println("Tarea '" + tarea.getTitulo() + "' marcada como completada");
        }
    }

    public void editarTarea(String id, String titulo, String descripcion, EtiquetaTarea etiqueta) {
        Tarea tarea = tareaDao.obtenerPorId(id);
        if (tarea != null) {
            if (titulo != null) tarea.setTitulo(titulo);
            if (descripcion != null) tarea.setDescripcion(descripcion);
            if (etiqueta != null) tarea.setEtiqueta(etiqueta);
            tareaDao.actualizar(tarea);
            System.out.println("Tarea '" + tarea.getTitulo() + "' editada correctamente");
        }
    }

    public void añadirTarea(Tarea tarea) {
        tareaDao.guardar(tarea);
        System.out.println("Tarea '" + tarea.getTitulo() + "' añadida al sistema");
    }
}