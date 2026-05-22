# Documentación de AppV4 (Planify)

## Índice
- **Introducción**: objetivo y justificación
- **Requisitos funcionales**: lista de funcionalidades
- **Análisis y diseño**: arquitectura, casos de uso, clases, datos
- **Codificación**: fragmentos y decisiones técnicas
- **Manual de usuario**: flujos y capturas
- **Requisitos e instalación**: pasos para ejecutar
- **Conclusiones**: mejoras y próximos pasos

## Resumen
AppV4 es la versión entregable de la aplicación móvil "Planify" para Android. Es una aplicación modular construida con Jetpack Compose, arquitectura MVVM, inyección de dependencias con Hilt y persistencia local con Room. Incluye funcionalidades principales: login, home, gestión de tareas, gestión económica (transacciones) y registro de estado de ánimo.
# Planify — Documentación del proyecto

## 1. Título
Planify — Aplicación para gestión personal: tareas, rutinas y finanzas.

## 2. Introducción

Objetivo
: Crear una aplicación móvil que unifique planificación de tareas, seguimiento de rutinas de salud y control económico personal.

Justificación
: Usuarios suelen usar múltiples aplicaciones (calendarios, gestores de tareas, apps de finanzas). Planify busca centralizar estas funcionalidades para mejorar la experiencia y la correlación entre hábitos y finanzas.

Análisis de lo existente
: El repositorio contiene varias iteraciones (BocetoClases, BocetoV2, ProyectoEx) y la evolución final en `App/AppV4` con migración a Kotlin, arquitectura MVVM, Room y Hilt. Los diarios del equipo documentan las decisiones y la evolución técnica.

## 3. Requisitos funcionales de la aplicación
- Registro y autenticación de usuarios (alta, login, logout).
- Gestión de tareas: crear/editar/borrar tareas con fecha, categoría y estado (pendiente, en curso, done).
- Calendario con vista mensual/semanal/día y vinculación con tareas.
- Gestión de transacciones: añadir gastos e ingresos con categoría, importe y fecha.
- Visualización de estadísticas de gasto por periodo y categoría.
- Registro del estado de ánimo diario y consulta de histórico.
- Sincronización local mediante Room y carga inicial de datos para primera ejecución.

## 4. Análisis y Diseño

Arquitectura
: La aplicación sigue MVVM con las capas: UI (Compose), ViewModels, Repositories y Data (Room/DAOs). Hilt se usa para inyección de dependencias.

Diagrama de la arquitectura
![Diagrama de arquitectura](./screenshots/architecture.png)

Diagrama de casos de uso
![Diagrama de casos de uso](./screenshots/usecases.png)

Diagrama de clases (simplificado)
![Diagrama de clases](./screenshots/classes.png)

Diseño de datos
- Entidades principales: `Usuario`, `Tarea`, `Transaccion`, `EstadoAnimo`.
- Relaciones: un `Usuario` posee muchas `Tarea` y `Transaccion`.
- Se usa Room con converters para tipos de fecha (`LocalDate`) y enums.

## 5. Codificación

Entorno de programación
- JDK 17
- Android Studio con soporte para Jetpack Compose
- Gradle (wrapper incluido en `App/AppV4`)

Lenguajes y herramientas
- Kotlin (Android + Jetpack Compose)
- Room (persistencia local)
- Hilt (inyección de dependencias)
- Kotlinx.serialization (serialización)
- KSP para generación de código (Room/Hilt)

Aspectos relevantes de la implementación
- MVVM con separación clara: `ViewModel` expone `StateFlow`/`LiveData` a la UI.
- Repositories encapsulan lógica de acceso a datos y mapeo entre entidades y modelos de dominio.
- Precarga de datos en `PlanifyApp` para tener contenido inicial útil en la primera ejecución.
- Manejo de errores en lectura de base de datos: consultas defensivas (ej. `COALESCE` para evitar nulls en agregados).

## 6. Manual de usuario

Instalación en dispositivo/emulador
1. Abrir `App/AppV4` en Android Studio.
2. Conectar un emulador o dispositivo con API >= 28.
3. Ejecutar Run -> `app` (Debug) o usar el comando:

```powershell
.\gradlew :app:installDebug
```

Uso básico
- Registro: crear cuenta desde la pantalla de Login -> Registro.
- Home: resumen de tareas del día y saldo rápido.
- Tareas: añadir nueva tarea desde `+`, editar vía swipe o menú contextual.
- Finanzas: añadir transacción desde la pantalla Economía, categorizarla.
- Estado de ánimo: registrar valor diario desde la pantalla de Estado de Ánimo.

## 7. Requisitos e instalación

Requisitos mínimos
- Android 9 (API 28)
- 2 GB RAM mínimo para emulador, recomendado 4 GB

Pasos de instalación (resumen)
```powershell
# En la raíz AppV4
.\gradlew assembleDebug
.\gradlew installDebug
```

## 8. Conclusiones

Conclusiones sobre el trabajo realizado
- Se logró una reestructuración consistente hacia MVVM, integración de Room y Hilt, y migración a Kotlin con pantallas Compose funcionales.

Posibles ampliaciones y mejoras
- Añadir sincronización remota (backend / API) y autenticación federada (Google Sign-In).
- Implementar pruebas unitarias e instrumentadas.
- Mejorar UX con analítica y recomendaciones personalizadas.

## 9. Bibliografía

Libros, artículos y apuntes
- Material de curso (apuntes y guías sobre Android, MVVM, Room)

Direcciones Web
- Documentación Android: https://developer.android.com
- Repositorio del proyecto: (este repositorio local)
- Diario de Cosme (ruta local): [docs/diarios/cosme_rodriguez.md](docs/diarios/cosme_rodriguez.md)
- Documentar los endpoints (si se integra backend) y formatos de serialización.
- Incluir un pequeño diagrama de arquitectura (PlantUML o Mermaid) dentro de `docs/` para visualización rápida.

---
Generado y actualizado con diagramas PlantUML el 22-05-2026.
