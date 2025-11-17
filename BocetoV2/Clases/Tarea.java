import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Tarea {
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha;
    private EtiquetaTarea etiqueta;
    private boolean completada;

    public Tarea() {}
    public Tarea(String titulo, String descripcion, LocalDateTime fecha, EtiquetaTarea etiqueta) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.etiqueta = etiqueta;
        this.completada = false;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public EtiquetaTarea getEtiqueta() { return etiqueta; }
    public void setEtiqueta(EtiquetaTarea etiqueta) { this.etiqueta = etiqueta; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return Objects.equals(id, tarea.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Tarea{" + "titulo='" + titulo + '\'' + ", completada=" + completada + '}';
    }
}