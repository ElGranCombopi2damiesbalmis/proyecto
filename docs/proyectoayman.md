# Parte del proyecto de ayman

## Arquitectura general  
**App Desarrollo y Organización Personal**

| Componente         | Plataforma / Tecnología    | Descripción                                                                 |
|--------------------|---------------------------|-----------------------------------------------------------------------------|
| Aplicación Móvil   | Android (Kotlin/Java)      | Interfaz para gestión personal: actividades, gastos y bienestar emocional.  |
| Backend / API REST | Spring Boot / Node.js      | Gestión de usuarios, datos, historial y estadísticas.                        |
| Base de Datos      | MySQL / Firebase Firestore | Almacenamiento de eventos, gastos, estado emocional y rutinas.              |

---

## Casos de Uso Principales

### Para Usuario (App Móvil)

| Caso de Uso | Descripción | Prioridad |
|-----------------------------|--------------------------------------------------------------------------------------------------|-----------|
| Registro / Inicio de sesión  | Permitir que el usuario cree una cuenta y acceda a la app.                                        | Alta      |
| Gestión de actividades       | Crear, editar y eliminar actividades o eventos con fecha, hora y recordatorios.                   | Alta      |
| Visualización calendario     | Ver actividades/eventos organizados en un calendario mensual/semanal/día.                         | Alta      |
| Gestión de gastos            | Añadir y categorizar gastos mensuales (ocio, alquiler, mensualidades, etc).                       | Alta      |
| Visualización de gastos      | Consultar resumen mensual de gastos y categorías.                                                | Alta      |
| Registro de estado emocional | El usuario registra su estado de ánimo diario mediante emoticonos.                               | Alta      |
| Historial emocional          | Consultar evolución y media del estado emocional a lo largo del tiempo (semana, mes).             | Media     |
| Gestión de rutinas de salud  | Crear y almacenar rutinas de gimnasio o salud personal.                                          | Media     |
| Notificaciones y recordatorios | Recibir alertas para actividades y seguimiento del estado emocional.                            | Media     |

### Para Usuario (App Escritorio)

| Caso de Uso | Descripción | Prioridad |
|-----------------------------|--------------------------------------------------------------------------------------------------|-----------|
| Inicio de sesión            | Permitir al usuario acceder a su cuenta de forma segura.                                          | Alta      |
| Gestión de actividades       | Crear, editar y eliminar actividades o eventos, con más detalles y facilidad desde escritorio.    | Alta      |
| Visualización calendario     | Consultar actividades y eventos en un calendario amplio con opciones avanzadas de filtrado.      | Alta      |
| Gestión de gastos            | Añadir, modificar y categorizar gastos mensuales, con gráficos y análisis detallados.            | Alta      |
| Visualización de gastos      | Consultar informes y resúmenes financieros personalizados por categorías y fechas.               | Alta      |
| Registro de estado emocional | Registrar y consultar el estado de ánimo diario mediante emoticonos, con historial y gráficas.   | Media     |
| Gestión de rutinas de salud  | Crear, editar y organizar rutinas de gimnasio o salud personal con opciones avanzadas.           | Media     |
| Notificaciones y recordatorios | Recibir y gestionar alertas para eventos, gastos y seguimiento emocional.                       | Media     |
| Exportar datos              | Exportar informes y datos personales (actividades, gastos, estado emocional) en formatos PDF o CSV.| Baja      |
