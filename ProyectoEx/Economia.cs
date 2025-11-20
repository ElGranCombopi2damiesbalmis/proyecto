public class Economia
{
    public List<Transaccion> Transacciones { get; set; }
    public double Saldo { get; private set; }

    public Economia()
    {
        Transacciones = new List<Transaccion>();
        Saldo = 0;
    }

    public void AgregarTransaccion(Transaccion transaccion)
    {
        Transacciones.Add(transaccion);
        
        if (transaccion.Tipo == TipoTransaccion.INGRESO)
            Saldo += transaccion.Cantidad;
        else
            Saldo -= transaccion.Cantidad;
    }

    public void VerGastos()
    {
        Console.WriteLine("=== GASTOS ===");
        var gastos = Transacciones.Where(t => t.Tipo == TipoTransaccion.GASTO);
        foreach (var gasto in gastos)
        {
            Console.WriteLine(gasto);
        }
        Console.WriteLine($"Total Gastos: ${gastos.Sum(g => g.Cantidad)}");
    }

    public void VerIngresos()
    {
        Console.WriteLine("=== INGRESOS ===");
        var ingresos = Transacciones.Where(t => t.Tipo == TipoTransaccion.INGRESO);
        foreach (var ingreso in ingresos)
        {
            Console.WriteLine(ingreso);
        }
        Console.WriteLine($"Total Ingresos: ${ingresos.Sum(i => i.Cantidad)}");
    }

    public void VerAmbos()
    {
        Console.WriteLine("=== TODAS LAS TRANSACCIONES ===");
        foreach (var transaccion in Transacciones.OrderBy(t => t.Fecha))
        {
            Console.WriteLine(transaccion);
        }
        Console.WriteLine($"\nSaldo Actual: ${Saldo}");
    }

    public void FiltrarPorFecha(DateTime fecha)
    {
        Console.WriteLine($"=== TRANSACCIONES DEL {fecha.ToShortDateString()} ===");
        var transaccionesFecha = Transacciones.Where(t => t.Fecha.Date == fecha.Date);
        foreach (var transaccion in transaccionesFecha)
        {
            Console.WriteLine(transaccion);
        }
    }

    public double ObtenerSaldoActual()
    {
        return Saldo;
    }
}