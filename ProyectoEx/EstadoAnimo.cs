public class EstadoAnimo
{
    public Dictionary<DateTime, IconoEstadoAnimo> RegistroAnimo { get; set; }

    public EstadoAnimo()
    {
        RegistroAnimo = new Dictionary<DateTime, IconoEstadoAnimo>();
    }

    public void RegistrarEstadoAnimo(DateTime fecha, IconoEstadoAnimo icono)
    {
        var fechaSinHora = fecha.Date;
        RegistroAnimo[fechaSinHora] = icono;
        Console.WriteLine($"Estado de ánimo registrado para {fechaSinHora.ToShortDateString()}: {icono}");
    }

    public IconoEstadoAnimo ObtenerEstadoAnimo(DateTime fecha)
    {
        var fechaSinHora = fecha.Date;
        if (RegistroAnimo.ContainsKey(fechaSinHora))
        {
            return RegistroAnimo[fechaSinHora];
        }
        return IconoEstadoAnimo.NORMAL;
    }

    public void MostrarHistorial()
    {
        Console.WriteLine("=== HISTORIAL DE ESTADO DE ÁNIMO ===");
        foreach (var registro in RegistroAnimo.OrderBy(r => r.Key))
        {
            Console.WriteLine($"{registro.Key.ToShortDateString()}: {registro.Value}");
        }
    }
}