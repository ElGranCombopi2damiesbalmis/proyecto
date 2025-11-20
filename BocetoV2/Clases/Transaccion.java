using System;

public class Transaccion
{
    public string Descripcion { get; set; }
    public double Cantidad { get; set; }
    public DateTime Fecha { get; set; }
    public TipoTransaccion Tipo { get; set; }

    public Transaccion(string descripcion, double cantidad, DateTime fecha, TipoTransaccion tipo)
    {
        Descripcion = descripcion;
        Cantidad = cantidad;
        Fecha = fecha;
        Tipo = tipo;
    }

    public override string ToString()
    {
        return $"{Fecha.ToShortDateString()} - {Tipo}: {Descripcion} - ${Cantidad:F2}";
    }
}