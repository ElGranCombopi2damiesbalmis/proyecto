import java.time.LocalDateTime;
import java.util.Objects;

public class Transaccion {
    private String descripcion;
    private double cantidad;
    private LocalDateTime fecha;
    private TipoTransaccion tipo;

    public Transaccion() {}
    public Transaccion(String descripcion, double cantidad, LocalDateTime fecha, TipoTransaccion tipo) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public TipoTransaccion getTipo() { return tipo; }
    public void setTipo(TipoTransaccion tipo) { this.tipo = tipo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return Double.compare(that.cantidad, cantidad) == 0 &&
               Objects.equals(descripcion, that.descripcion) &&
               Objects.equals(fecha, that.fecha) &&
               tipo == that.tipo;
    }

    @Override
    public int hashCode() { return Objects.hash(descripcion, cantidad, fecha, tipo); }

    @Override
    public String toString() {
        return "Transaccion{" + "descripcion='" + descripcion + '\'' + ", cantidad=" + cantidad + ", tipo=" + tipo + '}';
    }
}