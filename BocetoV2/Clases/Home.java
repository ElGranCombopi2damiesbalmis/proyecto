import java.util.Objects;

public class Home {
    private String fraseBienvenida;
    private int notificacionesPendientes;

    public Home() {}
    public Home(String nombreUsuario) {
        this.fraseBienvenida = "¡Bienvenido, " + nombreUsuario + "!";
        this.notificacionesPendientes = 0;
    }

    public String getFraseBienvenida() { return fraseBienvenida; }
    public void setFraseBienvenida(String fraseBienvenida) { this.fraseBienvenida = fraseBienvenida; }
    public int getNotificacionesPendientes() { return notificacionesPendientes; }
    public void setNotificacionesPendientes(int notificacionesPendientes) { this.notificacionesPendientes = notificacionesPendientes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Home home = (Home) o;
        return notificacionesPendientes == home.notificacionesPendientes && Objects.equals(fraseBienvenida, home.fraseBienvenida);
    }

    @Override
    public int hashCode() { return Objects.hash(fraseBienvenida, notificacionesPendientes); }

    @Override
    public String toString() { return "Home{" + "mensaje='" + fraseBienvenida + '\'' + '}'; }
}