using System;
using System.Collections.Generic;
using System.Linq;

public class EconomiaRepositoryMock : IEconomiaRepository
{
    // Simulación de Base de Datos
    private readonly List<Transaccion> _transacciones = new List<Transaccion>(); 

    public void GuardarTransaccion(Transaccion transaccion)
    {
        _transacciones.Add(transaccion);
        Console.WriteLine($"[Mock Economia]: Transacción '{transaccion.Descripcion}' guardada.");
    }
    // ... Implementación de ObtenerTodas, ObtenerPorTipo, ObtenerPorFecha usando _transacciones
    public List<Transaccion> ObtenerTodas() => new List<Transaccion>(_transacciones);
    public List<Transaccion> ObtenerPorTipo(TipoTransaccion tipo) => _transacciones.Where(t => t.Tipo == tipo).ToList();
    public List<Transaccion> ObtenerPorFecha(DateTime fecha) => _transacciones.Where(t => t.Fecha.Date == fecha.Date).ToList();
}