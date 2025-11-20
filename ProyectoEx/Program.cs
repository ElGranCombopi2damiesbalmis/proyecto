using System;

class Program
{
static void Main(string[] args)
{
Usuario usuario = new Usuario(
"Carlos",
"[carlos@test.com](mailto:carlos@test.com)",
"555-1234",
"Av. Central",
"perfil.jpg"
);

    int opcion = -1;

    do
    {
        Console.Clear();
        Console.WriteLine("=== MENÚ PRINCIPAL ===");
        Console.WriteLine("1. Ver economía");
        Console.WriteLine("2. Añadir transacción");
        Console.WriteLine("3. Registrar estado de ánimo");
        Console.WriteLine("4. Ver tareas");
        Console.WriteLine("5. Añadir tarea");
        Console.WriteLine("6. Completar tarea");
        Console.WriteLine("0. Salir");
        Console.Write("Opción: ");

        int.TryParse(Console.ReadLine(), out opcion);

        Console.Clear();

        switch (opcion)
        {
            case 1:
                Console.WriteLine("=== ECONOMÍA ===");

                foreach (var t in usuario.Economia.Transacciones)
                    Console.WriteLine(t);

                Console.WriteLine($"\nSaldo actual: {usuario.Economia.ObtenerSaldoActual()}");
                Console.ReadKey();
                break;

            case 2:
                Console.WriteLine("=== AÑADIR TRANSACCIÓN ===");

                Console.Write("Descripción: ");
                string desc = Console.ReadLine();

                Console.Write("Cantidad (+ ingreso / - gasto): ");
                double monto;
                double.TryParse(Console.ReadLine(), out monto);

                Console.WriteLine("Tipo:");
                Console.WriteLine("1. Ingreso");
                Console.WriteLine("2. Gasto");

                int tipoNum;
                int.TryParse(Console.ReadLine(), out tipoNum);

                TipoTransaccion tipo =
                    tipoNum == 1 ? TipoTransaccion.INGRESO : TipoTransaccion.GASTO;

                usuario.Economia.AgregarTransaccion(
                    new Transaccion(
                        desc,
                        monto,
                        DateTime.Now,
                        tipo
                    )
                );

                Console.WriteLine("Transacción registrada.");
                Console.ReadKey();
                break;

            case 3:
                Console.WriteLine("=== ESTADO DE ÁNIMO ===");

                Console.WriteLine("1. GENIAL");
                Console.WriteLine("2. BIEN");
                Console.WriteLine("3. NORMAL");
                Console.WriteLine("4. MAL");
                Console.WriteLine("5. MUY MAL");

                int moodNum;
                int.TryParse(Console.ReadLine(), out moodNum);

                IconoEstadoAnimo estado = (IconoEstadoAnimo)(moodNum - 1);

                usuario.EstadoAnimo.RegistrarEstadoAnimo(
                    DateTime.Now,
                    estado
                );

                Console.WriteLine("Estado registrado.");
                Console.ReadKey();
                break;

            case 4:
                Console.WriteLine("=== TAREAS ===");

                if (usuario.Tareas.Count == 0)
                    Console.WriteLine("No hay tareas.");
                else
                    for (int i = 0; i < usuario.Tareas.Count; i++)
                    {
                        var t = usuario.Tareas[i];
                        Console.WriteLine(
                            $"{i + 1}. {t.Titulo} - {(t.Completada ? "Completada" : "Pendiente")}"
                        );
                    }

                Console.ReadKey();
                break;

            case 5:
                Console.WriteLine("=== AÑADIR TAREA ==="); 

                Console.Write("Título: ");
                string titulo = Console.ReadLine();

                Console.Write("Descripción: ");
                string descripcion = Console.ReadLine();

                Console.WriteLine("Etiqueta:");
                Console.WriteLine("1. TRABAJO");
                Console.WriteLine("2. PERSONAL");
                Console.WriteLine("3. ESTUDIO");
                Console.WriteLine("4. HOGAR");
                Console.WriteLine("5. SALUD");
                Console.WriteLine("6. OTROS");

                int et;
                int.TryParse(Console.ReadLine(), out et);

                usuario.Tareas.Add(
                    new Tarea(
                        titulo,
                        descripcion,
                        DateTime.Now,
                        (EtiquetaTarea)(et - 1)
                    )
                );

                Console.WriteLine("Tarea creada.");
                Console.ReadKey();
                break;

            case 6:
                Console.WriteLine("=== COMPLETAR TAREA ===");

                for (int i = 0; i < usuario.Tareas.Count; i++)
                    Console.WriteLine($"{i + 1}. {usuario.Tareas[i].Titulo}");

                Console.Write("Seleccione número: ");
                int idx;
                int.TryParse(Console.ReadLine(), out idx);

                if (idx > 0 && idx <= usuario.Tareas.Count)
                {
                    usuario.Tareas[idx - 1].Completada = true;
                    Console.WriteLine("Tarea completada.");
                }
                else
                {
                    Console.WriteLine("Número inválido.");
                }

                Console.ReadKey();
                break;
        }

    } while (opcion != 0);
}

}
