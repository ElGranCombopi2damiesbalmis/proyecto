import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private String calle;
    private String fotoPerfil;
    
    private List<Tarea> tareas;
    private Economia economia;
    private EstadoAnimo estadoAnimo;
    private Home home;

    public Usuario() {}

    public Usuario(String nombre, String correo, String telefono, String calle) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.calle = calle;
        this.tareas = new ArrayList<>();
        this.economia = new Economia();
        this.estadoAnimo = new EstadoAnimo();
        this.home = new Home(nombre);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }
    public List<Tarea> getTareas() { return tareas; }
    public void setTareas(List<Tarea> tareas) { this.tareas = tareas; }
    public Economia getEconomia() { return economia; }
    public void setEconomia(Economia economia) { this.economia = economia; }
    public EstadoAnimo getEstadoAnimo() { return estadoAnimo; }
    public void setEstadoAnimo(EstadoAnimo estadoAnimo) { this.estadoAnimo = estadoAnimo; }
    public Home getHome() { return home; }
    public void setHome(Home home) { this.home = home; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "Usuario{" + "nombre='" + nombre + '\'' + ", correo='" + correo + '\'' + '}'; }
}