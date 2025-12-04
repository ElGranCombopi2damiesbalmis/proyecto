# PLANIFY

## Datos del Equipo

| Participantes | Roles | Perfil Github |
| --- | --- | --- |
| Victor | Profe | [Victietje](https://github.com/victietje) |
| Ayman | Camis de Furbo | [Aymanelhattachi](https://github.com/aymanelhattachi) |
| Cosme | Puto amo | [Mafellas](https://github.com/Mafellas) |

## ODS y su correlación con el proyecto

El **ODS** de *salud y bienestar* esta directamente relacionado con la parte de nuestro proyecto que se centra en el estado emocional del usuario, sus rutinas de entrenamiento, etc.

El siguiente **ODS** es el de *educacion de calidad* el cual se aplica en el apartado del planificador que desarrollaremos en en nuestro proyecto.

Por último tenemos el **ODS** de *trabajo decente y crecimiento económico* alineado con la seccion del seguimiento economico (dinero disponible, gasto, etc.)

## Descripción del Problema

Normalmente tenemos varias aplicaciones distintas para planificarnos, hacernos rutinas y analizar nuestros gastos, para eso con nuestro proyecto buscamos tenerlos en una única aplicación y así poder unificarlo todo en ella.

## Descripción de la Solucion Propuesta

Vamos a crear una aplicación que tenga un apartado centrado en la salud fisica y mental del usuario, uno dedicado a crear una planificacion mensual, semanal, etc. y por ultimo una sección aplicada a la economía del usuario.

## Actores y Roles

Nuestra intención es que el propio usuario registrado sea el administrador de la aplicación y asi se pueda organizar él mismo con la maxima privacidad posible.

## Riesgos y  mitigación

| Riesgo | Mitigación |
| --- | --- |
| Complejidad de arquitectura | Usar de manera continua Flows o rutinas simultaneas puede generar errores con frecuencia |
| Sincronización y rendimiento | Mantener las tareas "vivas", usando un calendario con notificaciones puede complicarse mucho |
| Vulnerabilidad de datos | Linkear con la cuenta de Google la aplicación puede derivar en filtración de datos si se hace mal |
| Sincronización y fluidez | Al tener varias tareas concurrentes la fluidez puede verse afectada y, por ende, la experiencia de usuario |

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

## Planificación aproximada 

| Sprint | Semanas | Fecha fin | Entregable clave |
| --- | --- | --- | --- |
| 1 | 27 oct–3 nov 2025 | 3 nov | Arquitectura base y configuración inicial de las clases |
| 2 | 4–20 nov 2025 | 20 nov | Creacion de controles, modelos Dao y pasar las clases a Java, y elaborar bocetos de la parte grafica de la aplicación en Figma |
| 3 | 27 nov–4 dic 2025 | 4 dic| Pulir el diseño final de la aplicación y mejorar funcionalidades de la misma **(1º Evaluación)**|
| 4 | 5–11 dic 2025 | 11 dic | Integración de FCM y pruebas iniciales |
| 5 | 12–21 dic 2025 | 21 dic | Revisión de interfaz y documentación técnica |
| 6 | 8-20 ene 2026 | 20 ene | Mantenimiento menor y actualización de dependencias |
| 7 | 21 ene-2 feb 2026 | 2 feb | Métricas de usuario y mejora de autenticación |
| 8 | 3–12 feb 2026 | 12 feb | Pruebas de usabilidad y optimización de pantallas |
| 9 | 13-20 feb 2026 | 20 feb | Panel de estadísticas y monitoreo de rendimiento |
| 10 | 21 feb–1 mar 2026 | 1 mar | Pruebas de estabilidad e integración general **(2ª Evaluación)**|
| 11 | 2–15 mar 2026 | 15 mar| Preparación y lanzamiento de versión Beta |
| 12 | 16–29 mar 2026 | 29 mar | Corrección de errores y ajustes post-Beta |
| 13 | 30 mar–12 abr 2026 | 12 abr | Evaluación de desempeño y planificación futura |
| 14 | 13–26 abr 2026 | 26 abr | Implementación de nuevas funciones |
| 15 | 27 abr–10 may 2026 | 10 may | Mejoras visuales y optimización general |
| 16 | 11–24 may 2026 | 24 may| Documentación final y validación completa |
| 17 | 25 may–7 jun 2026 | 7 jun | Entrega final del proyecto |
| 18 | 8–21 jun 2026 | 21 jun | Evaluación postentrega y mantenimiento |

## Organización de repositorio

```
NombreApp/
├── docs/
│ ├── parteCosme.md ← redacción de los puntos asignada a Cosme
│ ├── proyectoAyman.md ← redacción de los puntos asignada a Ayman
│ ├── proyectoVictor.md ← redacción de los puntos asignada a Victor
│ └── proyecto.md ← documento unificado
├── backend/
├── frontend-wpf/
└── frontend-android/
```