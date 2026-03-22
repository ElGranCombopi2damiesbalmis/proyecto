# Informe de Participación — Sprint 6
**Organización:** ElGranCombopi2damiesbalmis
**Proyecto:** Project
**Periodo:** 2026-02-22 a 2026-03-12
**Generado:** 2026-03-22

---

## 1. Estadísticas Generales

* **Total de usuarios activos:** 3 (Ayman El Hattachi Annachabi, Cosme Juan Rodríguez Pérez, Victor Tietje Fonollosa)
* **Periodo analizado:** 2026-02-22 a 2026-03-12
* **Días con actividad:** 8 (23/02, 26/02, 27/02, 02/03, 03/03, 09/03, 11/03, 12/03)
* **Total de commits:** 44 (30 efectivos de código + 14 de diario)
* **Archivos modificados únicos:** 78

---

## 2. Registro de Commits

| Nombre | Fecha | Archivos modificados | Resumen de lo que ha realizado (Análisis del diff) |
|---|---|---|---|
| Victor Tietje | 2026-02-23 | Reescritura de archivo .idea (6) | Reescritura de ficheros de configuración del IDE (.idea/.gitignore, misc.xml, modules.xml, etc.) |
| Cosme Juan Rodriguez | 2026-02-23 | GestiónDeTareas.kt, Ventana Principal.kt | Conexión de las pantallas de Tareas y la Ventana Principal para la navegación |
| Victor Tietje | 2026-02-23 | build.gradle.kts (AppV3), PlanifyEvent.kt, PlanifyVM.kt, Ventana Principal.kt, rutas (x8), libs.versions.toml | Cambios en ViewModel y navegación; ajuste de dependencias en Gradle y ficheros de ruta |
| Victor Tietje | 2026-02-26 | docs/diarios/victor_tietje.md | Actualización del diario (3 commits consecutivos de diario solamente) |
| Victor Tietje | 2026-02-26 | docs/diarios/victor_tietje.md | Actualización del diario |
| Victor Tietje | 2026-02-26 | docs/diarios/victor_tietje.md | Actualización del diario |
| Ayman El Hattachi | 2026-02-27 | docs/diarios/ayman_elhattachi.md | Actualización del diario — sesión recuperada de días 23/02 y 26/02 |
| Ayman El Hattachi | 2026-02-27 | app/build.gradle.kts, build.gradle.kts | Solución de problemas de ejecución de la App (dependencias de librerías); pantalla de login funcional en la navegación |
| Ayman El Hattachi | 2026-02-27 | AndroidManifest.xml, 8 Repositories, di/Aplication.kt | Creación del módulo DI con el archivo Application; anotaciones @Inject/@HiltAndroidApp en Repositorios y manifest |
| Ayman El Hattachi | 2026-02-27 | 9 ficheros de rutas de navegación + MainActivity.kt | Solución de errores en la navegación de la aplicación (rutas: Economia, EstadoAnimo, Gym, Home, Login, Settings, Tarea, Transaccion) |
| Ayman El Hattachi | 2026-02-27 | UsuarioRepository.kt, UsuarioRepositoryConverter.kt, LoginScreen.kt, PlanifyEvent.kt, PlanifyVM.kt | Solución de errores en ViewModel, Event y LoginScreen de Planify |
| Victor Tietje | 2026-03-02 | docs/diarios/victor_tietje.md | Actualización del diario |
| Victor Tietje | 2026-03-02 | UsuarioRepository.kt, AnalisisDeGastos.kt, AnalisisDeGastosViewModel.kt, EstadoDeAnimo.kt, LoginScreen.kt, LoginViewModel.kt, RutinasDeGimnasio.kt, GestiónDeTareas.kt, Ventana Principal.kt, 9 rutas | Añadido ViewModel de Economía y Login e implementación progresiva de la navegación en múltiples screens |
| Ayman El Hattachi | 2026-03-02 | docs/diarios/ayman_elhattachi.md | Actualización del diario |
| Ayman El Hattachi | 2026-03-02 | EstadoAnimoVM.kt, EstadoDeAnimo.kt, EstadoDeAnimoEvent.kt, NavHostPlanify.kt | Módulo de Estado de Ánimo linkado con ViewModel y Event |
| Ayman El Hattachi | 2026-03-02 | 5 pantallas Ajustes, GymEvent.kt, GymVM.kt, RutinasDeGimnasio.kt, Ventana Principal.kt, NavHostPlanify.kt, SettingsRoute.kt | Módulos de Salud y Ajustes linkados con ViewModels y Events respectivos; pantallas AjustesPerfil, Notificaciones, Privacidad, EditarPerfil |
| Ayman El Hattachi | 2026-03-02 | AnalisisDeGastos.kt, EstadoDeAnimo.kt, RutinasDeGimnasio.kt, Ventana Principal.kt, 5 rutas de navegación | Solución de conflictos de merge; rutas de Economía, EstadoAnimo, Gym, Settings y Transaccion reconciliadas |
| Cosme Juan Rodriguez | 2026-03-02 | GestiónDeTareas.kt | Cambios en la ventana de Tareas |
| Victor Tietje | 2026-03-03 | NavHostPlanify.kt | Elección del código base para el NavHost |
| Cosme Juan Rodriguez | 2026-03-03 | TareaRoute.kt | Inicio de la creación de la ruta de Tareas |
| Cosme Juan Rodriguez | 2026-03-03 | GestiónDeTareas.kt, TareaViewModel.kt, HomeViewModel.kt, Ventana Principal.kt | Creación de ViewModels de Tareas y Home (VentanaPrincipal) |
| Cosme Juan Rodriguez | 2026-03-03 | GestiónDeTareas.kt, Ventana Principal.kt, NavHostPlanify.kt | Arreglo de conflictos y añadido de rutas al NavHost |
| Cosme Juan Rodriguez | 2026-03-03 | Ventana Principal.kt | Cambios en la ventana principal (ajuste de layout y lógica) |
| Victor Tietje | 2026-03-03 | docs/diarios/victor_tietje.md | Actualización del diario |
| Victor Tietje | 2026-03-03 | NavegationItems.kt, EstadoDeAnimo.kt, RutinasDeGimnasio.kt, GestiónDeTareas.kt, Ventana Principal.kt, HomeRoute.kt, NavHostPlanify.kt, SettingsRoute.kt | Ordenado del código y unificación visual de componentes en los Screens |
| Victor Tietje | 2026-03-03 | NavegationItems.kt | Creación del componente NavegationItems reutilizable |
| Victor Tietje | 2026-03-03 | AnalisisDeGastos.kt, NavHostPlanify.kt | Unificación de componentes de Economía en el NavHost |
| Ayman El Hattachi | 2026-03-09 | EstadoDeAnimo.kt, studiobot.xml, MainActivity.kt | Avance de la pantalla de Estado de Ánimo (estructura UI) |
| Victor Tietje | 2026-03-09 | docs/diarios/victor_tietje.md | Actualización del diario |
| Victor Tietje | 2026-03-09 | Home.kt, NavegationItems.kt, AnalisisDeGastos.kt, EstadoDeAnimo.kt, RutinasDeGimnasio.kt, GestiónDeTareas.kt, TareaViewModel.kt, HomeViewModel.kt, Ventana Principal.kt | Sincronización de componentes comunes entre todas las Screens para unificar la barra de navegación inferior |
| Ayman El Hattachi | 2026-03-09 | docs/diarios/ayman_elhattachi.md | Actualización del diario |
| Ayman El Hattachi | 2026-03-09 | HomeRepositoryConverter.kt, Home.kt, AjustesPerfilScreen.kt, NavegationItems.kt, 5 Screens de distintos módulos, LoginScreen.kt, LoginViewModel.kt, HomeViewModel.kt, NavHostPlanify.kt, 4 Routes, MainActivity.kt | Solución de errores de navegación y funcionalidad completa de navegación entre vistas de la app |
| Cosme Juan Rodriguez | 2026-03-09 | GestiónDeTareas.kt, TareaViewModel.kt, TareaRoute.kt | Arreglo de la ruta de Tareas y ajuste del ViewModel |
| Ayman El Hattachi | 2026-03-09 | docs/diarios/ayman_elhattachi.md | Actualización del diario |
| Ayman El Hattachi | 2026-03-09 | AnalisisDeGastos.kt, AnalisisDeGastosViewModel.kt, GestiónDeTareas.kt, TareaViewModel.kt | Creación de los diálogos de añadir en Tareas y Transacciones (dialogs de creación) |
| Victor Tietje | 2026-03-11 | docs/diarios/victor_tietje.md | Actualización del diario |
| Victor Tietje | 2026-03-11 | Todos los repositorios + todos los DAOs y Entities Room (19 ficheros) | Implementación completa de Room en el proyecto: DAOs, Entities, Converters, DB, EntityConverters para Tarea, Transaccion, Rutina, EstadoAnimo, Usuario |
| Cosme Juan Rodriguez | 2026-03-11 | GestiónDeTareas.kt, TareaViewModel.kt | Actualización de la ventana de Tareas y su ViewModel (ajuste de lógica y UI) |
| Ayman El Hattachi | 2026-03-12 | AjustesVM.kt, AnalisisDeGastosViewModel.kt, EstadoAnimoVM.kt, LoginViewModel.kt, PlanifyVM.kt, GymVM.kt, HomeViewModel.kt, Ventana Principal.kt | Corrección de errores de anotaciones de Room en todos los ViewModels (@HiltViewModel) |
| Ayman El Hattachi | 2026-03-12 | EconomiaRepository.kt, EstadoAnimoRepository.kt, LoginRepository.kt, RutinaRepository.kt, TareaRepository.kt, TransaccionRepository.kt, UsuarioRepository.kt, LoginDaoMock.kt | Corrección de errores de anotaciones de Room @ApplicationContext en todos los Repositorios |
| Ayman El Hattachi | 2026-03-12 | LoginRepository.kt, RutinaRepository.kt, LoginDaoMock.kt, AnalisisDeGastosViewModel.kt, EstadoAnimoVM.kt, EstadoDeAnimo.kt, LoginScreen.kt, LoginViewModel.kt, TareaViewModel.kt, HomeViewModel.kt, Ventana Principal.kt, NavHostPlanify.kt, MainActivity.kt | Funcionalidad de Estado de Ánimo, Tareas, Transacciones, Rutina de Gimnasio y Home completadas con persistencia (Room integrado) |
| Ayman El Hattachi | 2026-03-12 | docs/diarios/ayman_elhattachi.md | Actualización del diario |
| Ayman El Hattachi | 2026-03-12 | docs/diarios/ayman_elhattachi.md | Actualización del diario (segunda entrada del día) |

---

## 3. Análisis Individualizado de Contribuciones

| Usuario | Commits (Efectivos)* | Días activos | Contribución principal | Seguimiento (Diario) | Discrepancias |
|---|---|---|---|---|---|
| Ayman El Hattachi | 13 (de 19 totales; 6 son de diario) | 4 / 19 días | Integración Hilt+DI, vinculación de todos los módulos (Ajustes, Salud, EstadoAnimo), navegación completa, funcionalidad Room, diálogos de creación en Tareas y Transacciones | Diario al día: entradas el 12/02, 27/02, 02/03, 09/03, 12/03. Coincide bien con los commits de código de esas fechas. | Alguna entrada del diario omite horas y reflexión en el mes de Marzo ("Pendiente"); multiples commits de diario por día en lugar de agruparlos. |
| Cosme Juan Rodriguez | 9 (de 9 totales; 0 son de diario) | 5 / 19 días | Vinculación de los módulos Home y Tareas, creación de ViewModels (HomeViewModel, TareaViewModel) y rutas de navegación (TareaRoute), ajuste del NavHost | **Diario sin actualizar**: no hay ninguna entrada para el Sprint 6 ni para todo el año 2026 (el último registro es del 08/01/2026). El diario no coincide con la actividad del sprint, que sí ha sido técnicamente activa. | Gran discrepancia entre la actividad real en código (9 commits) y la ausencia total de entradas en el diario desde el 8 de enero. No documenta su trabajo. |
| Victor Tietje | 8 (de 16 totales; 8 son de diario) | 6 / 19 días | Creación de NavegationItems, ViewModels de Economía/Login, implementación completa de Room (DAOs, Entities, Converters, DB), sincronización de componentes | Diario al día: entradas el 23/02, 26/02, 02/03, 03/03, 09/03, 11/03. En general coincide con los commits. | El 26/02 realizó 3 commits consecutivos de diario el mismo día (spammy). Las sesiones de Marzo tienen numeración de sesión duplicada (S25, S26) ya usadas en meses anteriores. El diario tiene menor detalle que el de Ayman. |

*Commits efectivos = commits que modifican código/recursos del proyecto. Se excluyen commits que únicamente tocan el archivo de diario personal.*

---

## 4. Resumen Análisis 📈 (Individual)

---

**Nombre: Ayman El Hattachi Annachabi (`aymanelhattachi`)**

* **Contribución principal:** Ha sido el miembro más activo del sprint en términos de volumen y amplitud técnica. Resolvió los problemas de dependencias con Hilt, creó el módulo DI, vinculó los módulos de Ajustes, Salud y Estado de Ánimo con sus respectivos ViewModels y Events, implementó la navegación completa de la app (rutas+NavHost), creó diálogos de creación en Tareas y Transacciones, y cerró el sprint dando funcionalidad real (persistencia con Room) a Estado de Ánimo, Tareas, Transacciones, Rutinas y Home.

* **Seguimiento:** El diario está bastante al día y su contenido es coherente con los commits. Se aprecian reflexiones y descripción del trabajo en cada sesión. Como punto de mejora, en Marzo las entradas tienen el campo "Resumen del mes" incompleto ("Pendiente") y en ocasiones hace varios commits de diario por día en lugar de consolidarlos en uno.

* **Análisis de las Tasks y/o UserStories asignadas durante el Sprint:**
  | Tarea | Status | Asignado |
  |---|---|---|
  | #100 - Vinculación del módulo Estado de Ánimo | ✅ Done | aymanelhattachi |
  | #101 - Vinculación del módulo Salud | ✅ Done | aymanelhattachi |
  | #97 - Vinculación del módulo Ajustes | ✅ Done | aymanelhattachi |
  | #110 - Agregar funcionalidad al botón de crear Tarea | ✅ Done | aymanelhattachi |
  | #111 - Agregar funcionalidad al botón de crear Transacción | ✅ Done | aymanelhattachi |
  | #112 - Agregar sincronización de los datos entre las vistas | 🔄 In Progress | aymanelhattachi |
  | #117 - Actualizar los VMs con anotaciones de Hilt | 🔄 In Progress | victietje, aymanelhattachi |

  5 tareas finalizadas (Done) y 2 en progreso. Las tareas "In Progress" al cierre del sprint reflejan trabajo técnico avanzado (las anotaciones de Hilt y la sincronización de datos) que se completó sustancialmente según los commits del 12/03, aunque el tablero no se actualizó a "Done". Esto indica una pequeña desconexión entre la realidad del código y el estado del tablero al cierre del sprint.

* **Participación en el grupo de forma activa:** 🟢 — Alta implicación durante todo el sprint. Cuatro días de trabajo intenso, resolviendo bloqueos técnicos críticos (dependencias, Room, navegación) que afectaban al conjunto del equipo.

* **Registro en Diario de Trabajo durante el Sprint:** 🟢 — Diario actualizado con regularidad en las fechas de trabajo (27/02, 02/03, 09/03, 12/03). Entradas con detalle de problemas, soluciones y reflexiones. Se penaliza ligeramente por commits de diario múltiples en el mismo día y el resumen de marzo sin completar.

* **Participación en el Incremento:** 🟢 — Contribución significativa y central: 5 tareas en Done, implementación de módulos completos con persistencia y funcionalidad real, cierre técnico del sprint.

---

**Nombre: Cosme Juan Rodríguez Pérez (`CosmeJuan`)**

* **Contribución principal:** Ha vinculado los módulos de Home y Tareas a la lógica de la aplicación: creó los ViewModels (HomeViewModel, TareaViewModel), la pantalla GestiónDeTareas, la ruta de navegación TareaRoute y participó en el ajuste del NavHost. Aunque sus commits son en menor número que sus compañeros, la calidad técnica es correcta dentro de su ámbito.

* **Seguimiento:** El mayor punto débil de Cosme este sprint es la ausencia absoluta de entradas en el diario. Su último registro es del **8 de enero de 2026** (Sprint 4), sin ninguna actualización en las casi dos semanas del Sprint 5 ni en las del Sprint 6. No hay ningún rastro escrito de su actividad durante este período, a pesar de haber hecho 9 commits de código.

* **Análisis de las Tasks y/o UserStories asignadas durante el Sprint:**
  | Tarea | Status | Asignado |
  |---|---|---|
  | #96 - Vinculación del módulo Home | ✅ Done | CosmeJuan |
  | #99 - Vinculación del módulo Tareas | ✅ Done | CosmeJuan |

  2 tareas asignadas, ambas finalizadas (Done). Cosme no tiene tareas "In Progress" al cierre del sprint, lo cual es positivo en cuanto a cierre de tareas asignadas, aunque su cartera de tareas es más reducida que la de sus compañeros.

* **Participación en el grupo de forma activa:** 🟡 — Cosme estuvo presente en 5 de los 8 días con actividad del sprint y completó sus tareas asignadas. Sin embargo, la ausencia de registro en el diario y la cartera de tareas más limitada respecto al resto del equipo indican una participación activa pero por debajo del nivel esperado.

* **Registro en Diario de Trabajo durante el Sprint:** 🔴 — El diario lleva sin actualizarse desde el 8 de enero de 2026. No hay ninguna entrada para el Sprint 5 ni el Sprint 6. Es una carencia grave y reiterada que debe corregirse con urgencia.

* **Participación en el Incremento:** 🟡 — Dos tareas completadas (Home y Tareas linkadas) con contribución correcta dentro de su ámbito. La participación es real pero limitada en comparación con el trabajo de los otros dos miembros del equipo.

---

**Nombre: Victor Tietje Fonollosa (`victietje`)**

* **Contribución principal:** Ha contribuido de forma muy relevante desde el inicio del sprint: implementó el sistema de navegación (components NavegationItems), creó los ViewModels de Economía y Login, reorganizó componentes comunes, y destaca especialmente por haber implementado la **capa Room completa** del proyecto (11/03): todos los DAOs, Entities, Converters, DB y EntityConverters para los 5 módulos de datos de la aplicación. Es una contribución técnica de gran calado que sienta las bases de la persistencia.

* **Seguimiento:** El diario está al día durante todo el Sprint 6. Las entradas (23/02, 26/02, 02/03, 03/03, 09/03, 11/03) describen correctamente el trabajo realizado y coinciden con los commits. Se observa que el 26/02 hizo 3 commits de diario consecutivos sin cambios de código (posiblemente errores de edición/resubida), y la numeración de sesiones en Marzo está duplicada respecto a meses anteriores (S25, S26 repetidos). El detalle de las entradas es correcto aunque algo más superficial que el de Ayman.

* **Análisis de las Tasks y/o UserStories asignadas durante el Sprint:**
  | Tarea | Status | Asignado |
  |---|---|---|
  | #95 - Vinculación módulo de Login | ✅ Done | victietje |
  | #98 - Vinculación del módulo Economía | ✅ Done | victietje |
  | #115 - Implementación de Entitys | ✅ Done | victietje |
  | #116 - Añadir anotaciones de Hilt en Repositorios | ✅ Done | victietje |
  | #114 - Carga de Datos de Room | 🔄 In Progress | victietje |
  | #117 - Actualizar los VMs con anotaciones de Hilt | 🔄 In Progress | victietje, aymanelhattachi |

  4 tareas finalizadas (Done) y 2 en progreso. La tarea #114 ("Carga de Datos de Room") quedó "In Progress" al finalizar el sprint aunque Victor implementó todas las entidades y DAOs de Room (commit b0ddb9a). El tablero no refleja completamente el trabajo realizado.

* **Participación en el grupo de forma activa:** 🟢 — Activo en 6 de los 8 días de actividad del sprint, con aportes técnicos significativos y variados (navegación, ViewModels, Room completo). Es el miembro con más días activos del sprint.

* **Registro en Diario de Trabajo durante el Sprint:** 🟡 — Diario presente y con regularidad en las sesiones del sprint. Sin embargo, hay 3 commits redundantes de diario en un mismo día (26/02), numeración de sesiones inconsistente, y el nivel de detalle/reflexión es algo inferior al de sprints anteriores.

* **Participación en el Incremento:** 🟢 — Contribución significativa: 4 tareas Done, implementación completa de Room (trabajo complejo y de mayor envergadura), y unificación de componentes de navegación. Su aportación es clave para el avance funcional del proyecto.

---

## 5. Resumen Análisis 📈 (Por Grupo)

**Nombre del Grupo: ElGranCombopi2damiesbalmis — Project (Planify)**

* **Funcionalidad del incremento entregado:** 🟢 — Los tres miembros activos tienen tareas en "Done": Ayman con 5, Victor con 4, y Cosme con 2. El sprint ha entregado un incremento real y completo en términos de vinculación de módulos, navegación funcional e inicio de la persistencia con Room. La app ha pasado de pantallas aisladas a una aplicación con navegación real y datos persistentes.

* **Realización de los eventos de Scrum, aplicando su filosofía:** 🟡 — Todos los miembros han trabajado y existe actividad distribuida a lo largo del sprint. Se observa que hay tarjetas de retrospectiva del Sprint 5 en el tablero (issues #105, #106, #107 en columna "Retrospective"), lo cual demuestra que en sprints anteriores sí se realizó. Sin embargo, **no hay ninguna tarjeta de retrospectiva creada para el Sprint 6** al cierre del período analizado, lo que indica que el evento de retrospectiva del Sprint 6 aún no se ha formalizado en el tablero.

* **Compromiso del equipo con el flujo de trabajo de Scrum a través de GitHub Projects:** 🟡 — La mayoría de las tareas están correctamente asignadas y con estados actualizados. Sin embargo, se detectan dos debilidades: (1) las issues #113 ("Implementación de Room al Proyecto") y #109 ("Implementar funcionalidades") permanecen en "In Progress" sin asignado —son user stories padre que no se han descompuesto correctamente o no han sido asignadas en el tablero—; (2) varias tareas cuyo código está completado según los commits (Hilt en VMs, sincronización de datos) siguen en "In Progress" en el tablero, indicando que el equipo no actualiza el estado del tablero en tiempo real.

---

## 6. Resumen Ejecutivo 📈

1. **Tendencias y Patrones:**
   * **2026-02-22 a 2026-02-27 — Arranque técnico por Ayman:** La primera semana del sprint fue dominada por Ayman, que resolvió los bloqueos técnicos críticos (dependencias Hilt, módulo DI, rutas de navegación) que impedían avanzar al resto del equipo. Esto denota una tendencia en el grupo donde uno de los miembros toma la iniciativa en los problemas de arquitectura.
   * **2026-03-02 a 2026-03-03 — Trabajo intensivo y colaborativo:** El 2 y 3 de marzo el equipo al completo trabajó en paralelo (Ayman en módulos, Victor en navegación/componentes, Cosme en rutas y views). Es el período de mayor densidad colaborativa del sprint.
   * **2026-03-09 a 2026-03-12 — Cierre con Room y funcionalidad real:** La semana final del sprint concentra los commits más técnicamente complejos y relevantes: Room completo de Victor, funcionalidad real de módulos de Ayman y ajuste final de Cosme en Tareas. El sprint cierra con un incremento sustancial y funcional.

2. **Calidad del Trabajo:**
   * La arquitectura MVVM+Hilt+Room que el equipo ha adoptado es correcta y moderna para Android. Evidencia madurez técnica, sobre todo en las implementaciones de Ayman (Hilt, DI) y Victor (Room completo).
   * Los commits de Ayman en el cierre del sprint son amplios (muchos archivos) pero bien documentados con mensajes descriptivos.
   * Victor ha demostrado comprensión sólida del patrón Repository+Room con Entities y Converters para múltiples modelos de dominio.
   * Cosme mantiene un trabajo más acotado y quizá menos visible, pero su contribución en Home y Tareas es coherente y correcta.
   * El principal problema de calidad de proceso es el **mal uso del tablero Scrum** (tareas completadas que no se marcan como Done, user stories sin asignar) y la **ausencia de diario por parte de Cosme**.

3. **Áreas de Mejora:**
   * **Cosme — Diario de trabajo:** Urge retomar y mantener el diario actualizado. Es la herramienta de reflexión y trazabilidad personal más importante del proceso formativo. Sin diario, es imposible evaluar su proceso de aprendizaje.
   * **Todo el equipo — Actualización del tablero en tiempo real:** Los estados de las tareas deben actualizarse en GitHub Projects conforme se completan, no al final del sprint. Esto mejora la visibilidad del progreso para todo el equipo y el Scrum Master.
   * **Todo el equipo — Asignación explícita de todas las tareas:** Las user stories o tasks que no tienen asignado en el tablero deben corregirse. El Scrum exige que cada tarea tenga dueño visible.
   * **Victor — Hygiene de commits de diario:** Evitar múltiples commits consecutivos del diario en el mismo día. Un solo commit diario bien redactado es suficiente y más limpio para el historial.
   * **Sprint 6 Retrospectiva:** Formalizar la retrospectiva del Sprint 6 creando las tarjetas correspondientes en el GitHub Projects ("Keep/Improve/Stop") para cerrar correctamente el ciclo Scrum.
