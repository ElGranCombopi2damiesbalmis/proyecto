using System;
using System.Collections.Generic;
using System.Linq;

public class EstadoAnimoRepositoryMock : IEstadoAnimoRepository
{
    // Simulación de Base de Datos: El diccionario en memoria
    private readonly Dictionary<DateTime, IconoEstadoAnimo> _registroAnimo = new Dictionary<DateTime, IconoEstadoAnimo>();

    public void RegistrarEstado(DateTime fecha, IconoEstadoAnimo icono)
    {
        var fechaSinHora = fecha.Date;
        _registroAnimo[fechaSinHora] = icono;
        Console.WriteLine($"[Mock EstadoAnimo]: Estado de ánimo '{icono}' registrado para {fechaSinHora.ToShortDateString()}.");
    }

    public IconoEstadoAnimo ObtenerEstado(DateTime fecha)
    {
        var fechaSinHora = fecha.Date;
        if (_registroAnimo.ContainsKey(fechaSinHora))
        {
            return _registroAnimo[fechaSinHora];
        }
        return IconoEstadoAnimo.NORMAL; // Valor por defecto si no existe
    }

    public Dictionary<DateTime, IconoEstadoAnimo> ObtenerHistorial()
    {
        // Retornar una copia para evitar manipulación externa directa
        return new Dictionary<DateTime, IconoEstadoAnimo>(_registroAnimo);
    }
}