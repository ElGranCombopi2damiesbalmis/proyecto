import java.util.List;

public class TransaccionController {
    private TransaccionDao transaccionDao;

    public TransaccionController(TransaccionDao transaccionDao) {
        this.transaccionDao = transaccionDao;
    }

    public void crearTransaccion(String descripcion, double cantidad, TipoTransaccion tipo) {
        Transaccion t = new Transaccion(descripcion, cantidad, java.time.LocalDateTime.now(), tipo);
        transaccionDao.guardar(t);
        System.out.println("Transacción creada: " + descripcion);
    }

    public void listarTodas() {
        List<Transaccion> lista = transaccionDao.obtenerTodas();
        System.out.println("=== LISTA GLOBAL DE TRANSACCIONES ===");
        for (Transaccion t : lista) {
            System.out.println(t);
        }
    }
}