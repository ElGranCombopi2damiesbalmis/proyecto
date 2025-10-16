# Título del Proyecto

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

## Riesgos y  mitigación

| Riesgo | Mitigación |
| --- | --- |
| Complejidad de arquitectura | Usar de manera continua Flows o rutinas simultaneas puede generar errores con frecuencia |
| Sincronización y rendimiento | Mantener las tareas "vivas", usando un calendario con notificaciones puede complicarse mucho |
| Vulnerabilidad de datos | Linkear con la cuenta de Google la aplicación puede derivar en filtración de datos si se hace mal |
| Sincronización y fluidez | Al tener varias tareas concurrentes la fluidez puede verse afectada y, por ende, la experiencia de usuario |

## Planificación aproximada 

| Sprint | Semanas | Fecha fin | Entregable clave |
| --- | --- | --- | --- |
| 1 | 13–26 oct 2025 | 26 oct | Arquitectura base y configuración inicial de Firebase |
| 2 | 27 oct–9 nov 2025 | 9 nov | Módulo de tareas y gestión de estado de ánimo |
| 3 | 10–23 nov 2025 | 23 nov| Módulo de finanzas y apartado de salud **(1º Evaluación)**|
| 4 | 24 nov–7 dic 2025 | 7 dic | Integración de FCM y pruebas iniciales |
| 5 | 8–21 dic 2025 | 21 dic | Revisión de interfaz y documentación técnica |
| 6 | 22 dic 2025–4 ene 2026 | 4 ene | Mantenimiento menor y actualización de dependencias |
| 7 | 5–18 ene 2026 | 18 ene | Métricas de usuario y mejora de autenticación |
| 8 | 19 ene–1 feb 2026 | 1 feb | Pruebas de usabilidad y optimización de pantallas |
| 9 | 2–15 feb 2026 | 15 feb | Panel de estadísticas y monitoreo de rendimiento |
| 10 | 16 feb–1 mar 2026 | 1 mar | Pruebas de estabilidad e integración general **(2ª Evaluación)**|
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
│ ├── PROYECTO.md ← visión, ODS, casos de uso
│ ├── DISENO.md ← modelo, decisiones arquitectónicas
│ └── DIARIO.md ← seguimiento semanal individual
├── backend/
├── frontend-wpf/
└── frontend-android/
```