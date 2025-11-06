public class Tarea
{
    public string Id { get; set; }
    public string Titulo { get; set; }
    public string Descripcion { get; set; }
    public DateTime Fecha { get; set; }
    public EtiquetaTarea Etiqueta { get; set; }
    public bool Completada { get; set; }

    public Tarea(string titulo, string descripcion, DateTime fecha, EtiquetaTarea etiqueta)
    {
        Id = Guid.NewGuid().ToString();
        Titulo = titulo;
        Descripcion = descripcion;
        Fecha = fecha;
        Etiqueta = etiqueta;
        Completada = false;
    }

    public void MarcarComoCompletada()
    {
        Completada = true;
        Console.WriteLine($"Tarea '{Titulo}' marcada como completada");
    }

    public void EditarTarea(string titulo = null, string descripcion = null, DateTime? fecha = null, EtiquetaTarea? etiqueta = null)
    {
        if (titulo != null) Titulo = titulo;
        if (descripcion != null) Descripcion = descripcion;
        if (fecha.HasValue) Fecha = fecha.Value;
        if (etiqueta.HasValue) Etiqueta = etiqueta.Value;
        
        Console.WriteLine($"Tarea '{Titulo}' editada correctamente");
    }

    public void AñadirTarea()
    {
        Console.WriteLine($"Tarea '{Titulo}' añadida al sistema");
    }

    public void EliminarTarea()
    {
        Console.WriteLine($"Tarea '{Titulo}' eliminada del sistema");
    }
}