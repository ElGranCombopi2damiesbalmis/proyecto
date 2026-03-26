# Entrega de reestructuración - Planify

## A. Análisis inicial

- El proyecto original ya tenía intención de seguir una separación por capas (`models`, `data`, `ui`, `room`, `views`), pero estaba mezclado con incoherencias de paquetes, nombres, pantallas, navegación y persistencia.
- El proyecto de referencia `Recetario_Solucion` organiza la app por capas claras: `data` (repositorios, converters, mocks y room), `model`, `ui/features`, `ui/navigation`, `ui/theme`, `ui/views` y una `Application` de arranque con Hilt.
- La web de clase refuerza la misma idea: MVVM, repositorios, capa UI con ViewModel, navegación Compose y Room como persistencia local.
- Se ha aplicado esa misma filosofía al proyecto Planify sin rediseñar la interfaz ni cambiar su intención funcional.

## B. Reestructuración realizada

### Archivos renombrados
- `di/Aplication.kt` -> `di/PlanifyApp.kt`
- `ui/features/Tareas/Gesti#U00f3nDeTareas.kt` -> `ui/features/Tareas/GestionDeTareasScreen.kt`
- `ui/features/VentanaPrincipal/Ventana Principal.kt` -> `ui/features/VentanaPrincipal/DashboardScreen.kt`

### Reorganización de paquetes
- `ui/navegation` -> `ui/navigation`
- Ajuste masivo de imports para unificar navegación con el mismo patrón del proyecto de referencia.
- Ajuste de paquete en `LoginScreen.kt` para dejarlo dentro de `ui/features/Login`.
- Ajuste de paquete en `AnalisisDeGastos.kt` para dejarlo dentro de `ui/features/Economia`.

### Limpieza estructural
- Eliminados del proyecto entregable: `.gradle`, `.idea`, `.kotlin`, `app/build` y `local.properties`.
- Se ha dejado el proyecto preparado como código fuente limpio para abrir directamente en Android Studio.

## C. Errores corregidos

- Corregido el conflicto del plugin de serialización de Kotlin:
  - raíz del proyecto usando `alias(libs.plugins.kotlinx.serialization) apply false`
  - módulo `app` usando `alias(libs.plugins.kotlinx.serialization)`
- Corregidos imports rotos entre pantallas, navegación y features.
- Corregida la incoherencia entre nombre de carpeta y paquete de economía.
- Corregida la pérdida de datos al actualizar usuario:
  - `Usuario` ahora conserva `id`
  - el converter de `Usuario <-> UsuarioEntity` ya preserva `id` y `password`
- Corregido el alta duplicada de usuarios en `LoginRepository` comprobando email existente antes de insertar.
- Corregida la consulta de saldo para que no falle con base de datos vacía usando `COALESCE(..., 0)`.
- Corregida la firma de borrado en `EstadoAnimoDao` para que use `LocalDate` y sea coherente con la entidad.
- Ampliado el mapper de iconos de transacciones para cubrir las categorías usadas por la UI.
- Corregidos errores de sintaxis en campos `supportingText` de formularios.
- Corregidas llamadas de elevación de botones para ajustarlas a la API de Material3.

## D. Lógica completada

- Se ha añadido precarga inicial de datos en `PlanifyApp` al estilo del proyecto de referencia:
  - usuario por defecto
  - tareas iniciales
  - transacciones iniciales
  - estados de ánimo iniciales
- Esto evita una app vacía al arrancar y deja operativos login, home, tareas, economía y estado de ánimo desde el primer inicio.
- Se ha mantenido Room como persistencia real para tareas, transacciones, usuarios y estado de ánimo.

## E. Cambios excepcionales fuera del código

### `build.gradle.kts` (raíz)
- Añadido uso del alias correcto del plugin de serialización.
- Era imprescindible porque el proyecto mezclaba Kotlin `2.0.20` con el plugin de serialización fijado a `1.9.0`, lo que provocaba incompatibilidad directa.

### `app/build.gradle.kts`
- Sustituido `id("kotlinx-serialization")` por `alias(libs.plugins.kotlinx.serialization)`.
- Era imprescindible para usar exactamente la misma versión del plugin declarada en el catálogo y evitar conflictos de resolución.

No se ha tocado ningún otro archivo de configuración más allá de lo imprescindible.

## F. Resultado final

- Proyecto reestructurado y limpiado.
- Persistencia real con Room para las partes clave.
- Navegación y packages unificados.
- Preparado para abrir directamente en Android Studio.
- Entrega final empaquetada en ZIP.

## G. Regeneración de entrega

- Versión regenerada: v2
- Cambio menor aplicado: actualización mínima del documento de entrega para regenerar el ZIP.
