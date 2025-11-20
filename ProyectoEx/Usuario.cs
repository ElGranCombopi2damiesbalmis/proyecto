public class Usuario
{
    public Guid Id { get; private set; }
    public string Nombre { get; set; }
    public string Correo { get; set; }
    public string Telefono { get; set; }
    public string Calle { get; set; }
    public string FotoPerfil { get; set; }
    
    public List<Tarea> Tareas { get; set; }
    public Economia Economia { get; set; }
    public EstadoAnimo EstadoAnimo { get; set; }
    public Home Home { get; set; }

    public Usuario(string nombre, string correo, string telefono, string calle, string fotoPerfil)
    {
        Id = Guid.NewGuid();
        Nombre = nombre;
        Correo = correo;
        Telefono = telefono;
        Calle = calle;
        FotoPerfil = fotoPerfil;
        Tareas = new List<Tarea>();
        Economia = new Economia();
        EstadoAnimo = new EstadoAnimo();
        Home = new Home(this, nombre);
    }

    public void VerAjustes()
    {
        Console.WriteLine($"Mostrando ajustes de {Nombre}");
        Console.WriteLine($"Correo: {Correo}");
        Console.WriteLine($"Teléfono: {Telefono}");
        Console.WriteLine($"Dirección: {Calle}");
    }

    public void EditarPerfil(string nombre, string correo, string telefono, string calle, string fotoPerfil)
    {
        if (nombre != null) Nombre = nombre;
        if (correo != null) Correo = correo;
        if (telefono != null) Telefono = telefono;
        if (calle != null) Calle = calle;
        if (fotoPerfil != null) FotoPerfil = fotoPerfil;
        
        Console.WriteLine("Perfil actualizado correctamente");
    }
}