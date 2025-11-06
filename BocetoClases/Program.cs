public class Program
{
    public static void Main()
    {
        var usuario = new Usuario("Juan Pérez", "juan@email.com", "123456789", "Calle Principal 123");
        
        var tarea1 = new Tarea("Comprar comida", "Ir al supermercado", DateTime.Now.AddDays(1), EtiquetaTarea.HOGAR);
        var tarea2 = new Tarea("Estudiar C#", "Repasar POO", DateTime.Now, EtiquetaTarea.ESTUDIO);
        usuario.Tareas.Add(tarea1);
        usuario.Tareas.Add(tarea2);
        
        usuario.Economia.AgregarTransaccion(new Transaccion("Salario", 2000, DateTime.Now, TipoTransaccion.INGRESO));
        usuario.Economia.AgregarTransaccion(new Transaccion("Supermercado", 150, DateTime.Now, TipoTransaccion.GASTO));
        usuario.Economia.AgregarTransaccion(new Transaccion("Netflix", 15, DateTime.Now, TipoTransaccion.GASTO));
        
        usuario.EstadoAnimo.RegistrarEstadoAnimo(DateTime.Now, IconoEstadoAnimo.BIEN);
        
        usuario.Home.MostrarDashboard();
        
        Console.WriteLine("\n");
        usuario.Economia.VerAmbos();
    }
}