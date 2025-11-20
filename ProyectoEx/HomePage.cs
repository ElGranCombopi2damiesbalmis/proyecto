public class Home
{
    public string FraseBienvenida { get; set; }
    public int NotificacionesPendientes { get; set; }
    
    private Usuario usuario;

    public Home(Usuario usuario, string nombreUsuario)
    {
        this.usuario = usuario;
        FraseBienvenida = $"¡Bienvenido, {nombreUsuario}!";
        NotificacionesPendientes = 0;
    }

    public void MostrarPreviewEstadoAnimo()
    {
        Console.WriteLine("--- Preview Estado de Ánimo ---");
        Console.WriteLine("Estado de hoy registrado");
    }

    public void MostrarPreviewEconomia()
    {
        Console.WriteLine("--- Preview Economía ---");
        Console.WriteLine("Balance actual y últimas transacciones");
    }

    public void MostrarPreviewTareas()
    {
        Console.WriteLine("--- Preview Tareas ---");
        Console.WriteLine("Tareas pendientes y próximas");
    }

    public void VerNotificaciones()
    {
        Console.WriteLine($"Tienes {NotificacionesPendientes} notificaciones pendientes");
    }

    public void SeleccionarEstadoAnimo(IconoEstadoAnimo icono)
    {
        Console.WriteLine($"Estado de ánimo seleccionado: {icono}");
    }

    public void MostrarDashboard()
    {
        Console.WriteLine("=================================");
        Console.WriteLine(FraseBienvenida);
        Console.WriteLine("=================================");
        MostrarPreviewTareas();
        MostrarPreviewEconomia();
        MostrarPreviewEstadoAnimo();
        VerNotificaciones();
    }
}