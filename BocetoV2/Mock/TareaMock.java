using System.Collections.Generic;
using System.Linq;
using System;

public class TareaRepositoryMock : ITareaRepository
{
    // Simulación de Base de Datos
    private readonly List<Tarea> _tareas = new List<Tarea>();

    public void Guardar(Tarea tarea)
    {
        var existente = _tareas.FirstOrDefault(t => t.Id == tarea.Id);
        if (existente != null)
        {
            // Simular Actualización
            existente.Titulo = tarea.Titulo;
            existente.Descripcion = tarea.Descripcion;
            existente.Fecha = tarea.Fecha;
            existente.Etiqueta = tarea.Etiqueta;
            existente.Completada = tarea.Completada;
            Console.WriteLine($"[Mock Tarea]: Tarea '{tarea.Titulo}' actualizada.");
        }
        else
        {
            // Simular Inserción
            _tareas.Add(tarea);
            Console.WriteLine($"[Mock Tarea]: Tarea '{tarea.Titulo}' guardada.");
        }
    }

    public Tarea ObtenerPorId(string id)
    {
        return _tareas.FirstOrDefault(t => t.Id == id);
    }

    public List<Tarea> ObtenerTodas()
    {
        return new List<Tarea>(_tareas);
    }

    public List<Tarea> ObtenerPorEtiqueta(EtiquetaTarea etiqueta)
    {
        return _tareas.Where(t => t.Etiqueta == etiqueta).ToList();
    }

    public void Eliminar(string id)
    {
        _tareas.RemoveAll(t => t.Id == id);
        Console.WriteLine($"[Mock Tarea]: Tarea con ID '{id}' eliminada.");
    }
}