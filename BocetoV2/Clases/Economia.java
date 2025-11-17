import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Economia {
    private List<Transaccion> transacciones;
    private double saldo;

    public Economia() {
        this.transacciones = new ArrayList<>();
        this.saldo = 0;
    }

    public List<Transaccion> getTransacciones() { return transacciones; }
    public void setTransacciones(List<Transaccion> transacciones) { this.transacciones = transacciones; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Economia economia = (Economia) o;
        return Double.compare(economia.saldo, saldo) == 0 && Objects.equals(transacciones, economia.transacciones);
    }

    @Override
    public int hashCode() { return Objects.hash(transacciones, saldo); }

    @Override
    public String toString() { return "Economia{" + "saldo=" + saldo + '}'; }
}