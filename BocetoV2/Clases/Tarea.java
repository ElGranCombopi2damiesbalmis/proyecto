import java.util.Date;
import java.util.UUID;
// ...

public class Tarea {
    private String id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private EtiquetaTarea etiqueta;
    private boolean completada;

    public Tarea(String titulo, String descripcion, Date fecha, EtiquetaTarea etiqueta) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.etiqueta = etiqueta;
        this.completada = false;
    }
    
    // Getters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public Date getFecha() { return fecha; }
    public EtiquetaTarea getEtiqueta() { return etiqueta; }
    public boolean isCompletada() { return completada; }

    // Setters (que serán usados por el Controller para modificar el estado)
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setEtiqueta(EtiquetaTarea etiqueta) { this.etiqueta = etiqueta; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    // El método MarcarComoCompletada() se convierte en un simple setter en el Controller.
}