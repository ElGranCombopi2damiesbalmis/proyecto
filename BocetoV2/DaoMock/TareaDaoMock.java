import java.time.LocalDateTime;

public class TareaDaoMock extends TareaDao {
    
    public TareaDaoMock() {
        super();
        
        // Datos inicializados de prueba
        Tarea t1 = new Tarea("Comprar comida", "Ir al supermercado", LocalDateTime.now().plusDays(1), EtiquetaTarea.HOGAR);
        Tarea t2 = new Tarea("Estudiar Java", "Repasar POO", LocalDateTime.now(), EtiquetaTarea.ESTUDIO);
        
        this.guardar(t1);
        this.guardar(t2);
    }
}