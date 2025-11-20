// Economia.java
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Economia {
    private EconomiaDao economiaDao;
    private double saldo;

    // Se usa inyección de dependencia para el DAO
    public Economia(EconomiaDao economiaDao) {
        this.economiaDao = economiaDao;
        // Calcular el saldo inicial basado en las transacciones existentes si las hubiera
        this.saldo = calcularSaldoInicial();
    }

    private double calcularSaldoInicial() {
        List<Transaccion> transacciones = economiaDao.obtenerTodasLasTransacciones();
        double ingresos = transacciones.stream()
                .filter(t -> t.getTipo() == TipoTransaccion.INGRESO)
                .mapToDouble(Transaccion::getCantidad)
                .sum();
        double gastos = transacciones.stream()
                .filter(t -> t.getTipo() == TipoTransaccion.GASTO)
                .mapToDouble(Transaccion::getCantidad)
                .sum();
        return ingresos - gastos;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        economiaDao.guardarTransaccion(transaccion);
        
        if (transaccion.getTipo() == TipoTransaccion.INGRESO)
            saldo += transaccion.getCantidad();
        else
            saldo -= transaccion.getCantidad();
    }

    public void verGastos() {
        System.out.println("=== GASTOS ===");
        List<Transaccion> gastos = economiaDao.obtenerTransaccionesPorTipo(TipoTransaccion.GASTO);
        gastos.forEach(System.out::println);
        double totalGastos = gastos.stream().mapToDouble(Transaccion::getCantidad).sum();
        System.out.println("Total Gastos: $" + totalGastos);
    }

    public void verIngresos() {
        System.out.println("=== INGRESOS ===");
        List<Transaccion> ingresos = economiaDao.obtenerTransaccionesPorTipo(TipoTransaccion.INGRESO);
        ingresos.forEach(System.out::println);
        double totalIngresos = ingresos.stream().mapToDouble(Transaccion::getCantidad).sum();
        System.out.println("Total Ingresos: $" + totalIngresos);
    }

    public void verAmbos() {
        System.out.println("=== TODAS LAS TRANSACCIONES ===");
        List<Transaccion> transacciones = economiaDao.obtenerTodasLasTransacciones();
        transacciones.stream()
                     .sorted((t1, t2) -> t1.getFecha().compareTo(t2.getFecha()))
                     .forEach(System.out::println);
        
        System.out.println("\nSaldo Actual: $" + saldo);
    }

    public void filtrarPorFecha(LocalDate fecha) {
        System.out.println("=== TRANSACCIONES DEL " + fecha + " ===");
        List<Transaccion> transaccionesFecha = economiaDao.obtenerTransaccionesPorFecha(fecha);
        transaccionesFecha.forEach(System.out::println);
    }

    public double obtenerSaldoActual() {
        return saldo;
    }
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