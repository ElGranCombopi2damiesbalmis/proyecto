# Informe de Participación — Sprint 5
**Organización:** ElGranCombopi2damiesbalmis | **Proyecto:** Project
**Periodo analizado:** 2026-01-20 a 2026-02-12

---

## 1. Estadísticas Generales

* **Total de usuarios activos:** 2 (Ayman El Hattachi Annachabi, Victor Tietje Fonollosa)
* **Periodo analizado:** 2026-01-20 a 2026-02-12
* **Días con actividad:** 3 (22/01, 26/01, 12/02)
* **Total de commits (excl. profesor):** 14
* **Archivos modificados únicos:** ~90

> ⚠️ **Nota:** Cosme Juan Rodríguez Pérez no registró ningún commit durante el periodo del Sprint 5, ni actualizó su diario de trabajo.

---

## 2. Registro de Commits

*(Ordenados cronológicamente, excluido @juanjobalmis)*

| Nombre | Fecha | Archivos modificados | Resumen de lo que ha realizado |
|---|---|---|---|
| Victor Tietje | 2026-01-22 | `App/AppV3/` (~80 ficheros), `.vs/` | Creación completa del proyecto AppV3 en Android Studio: estructura de carpetas, modelos Kotlin, Repositories, DaoMocks, Mocks, Screens UI (Ajustes, Economía, EstadoAnimo, Login, Tareas, VentanaPrincipal, RutinasDeGimnasio), temas, recursos gráficos y configuración de Gradle. |
| Victor Tietje | 2026-01-22 | Docs del proyecto | Actualización de documentación del proyecto (README/docs). |
| Victor Tietje | 2026-01-22 | `App/AppV2/.idea/deploymentTargetSelector.xml`, `App/AppV3/.idea/.name`, `docs/diarios/victor_tietje.md` | Actualización del diario personal y ajuste de metadatos de IDE. |
| Ayman El Hattachi | 2026-01-22 | `docs/diarios/ayman_elhattachi.md` | Actualización del diario de trabajo. |
| Ayman El Hattachi | 2026-01-26 | `App/AppV3/.idea/.name`, `models/Usuario.kt`, `settings.gradle.kts` | Ajustes en el modelo de `Usuario.kt` y configuración del proyecto. |
| Victor Tietje | 2026-01-26 | `app/build.gradle.kts`, `AndroidManifest.xml`, `LoginRepository.kt`, `LoginRepositoryConverter.kt`, `UsuarioRepository.kt`, `UsuarioRepositoryConverter.kt`, `LoginDaoMock.kt`, `UsuarioDaoMock.kt`, `Login.kt`, `Usuario.kt`, `MainActivity.kt`, `strings.xml`, `themes.xml` (13 ficheros) | Vinculación de datos de login con la entidad `Usuario`; implementación de conversión de contraseñas a HashCode en DaoMock; corrección del `AndroidManifest.xml` y del tema para permitir el arranque correcto de la app. |
| Ayman El Hattachi | 2026-01-26 | `RutinaRepository.kt`, `RutinaRepositoryConverter.kt`, `RutinaDaoMock.kt`, `RutinaMock.kt`, `Rutina.kt`, `RutinasDeGimnasio.kt`, `UsuarioRepositoryConverter.kt`, `MainActivity.kt` (8 ficheros) | Creación del modelo, Mock, DaoMock, Repository y RepositoryConverter de `RutinasDeGimnasio`, y enlace de la pantalla con dichos archivos. |
| Ayman El Hattachi | 2026-01-26 | `views/MainActivity.kt` | Cambios adicionales en `MainActivity` para integración de módulos. |
| Victor Tietje | 2026-01-26 | `docs/diarios/victor_tietje.md` | Actualización del diario de trabajo. |
| Ayman El Hattachi | 2026-01-26 | `docs/diarios/ayman_elhattachi.md` | Actualización del diario de trabajo. |
| Ayman El Hattachi | 2026-02-12 | `UsuarioRepository.kt`, `UsuarioRepositoryConverter.kt`, `MainActivity.kt`, `docs/diarios/ayman_elhattachi.md` | Correcciones en `UsuarioRepository` y `UsuarioRepositoryConverter`; ajustes en `MainActivity`; actualización del diario. |
| Ayman El Hattachi | 2026-02-12 | `docs/diarios/ayman_elhattachi.md` | Actualización del diario (entrada final del sprint). |
| Victor Tietje | 2026-02-12 | `docs/diarios/victor_tietje.md` | Actualización del diario + adición de sección de febrero. |
| Victor Tietje | 2026-02-12 | `docs/diarios/victor_tietje.md` | Confirmación de la actualización del diario con la sección de febrero creada. |

---

## 3. Análisis Individualizado de Contribuciones

| Usuario | Commits (Efectivos)* | Días activos | Contribución principal | Seguimiento (Diario) | Discrepancias |
|---|---|---|---|---|---|
| Ayman El Hattachi | 7 total / **4 efectivos** (excluyendo actualizaciones de diario puro) | 3 / 24 posibles | Implementación del stack completo de `RutinasDeGimnasio` (modelo, mock, daomock, repository, screen); ajustes en `Usuario.kt` y `MainActivity.kt`; correcciones en `UsuarioRepository`. | Actualizado en las 3 fechas de actividad (22/01, 26/01, 12/02). La entrada del 12/02 refleja el sprint review y la creación del Sprint 6. Coincidencia aceptable con los commits. | La entrada del 22/01 menciona trabajar desde el PC de Victor (sprint review), pero el commit de esa fecha sólo recoge actualización del diario, lo que es coherente dado que ese día el trabajo de código fue realizado por Victor. |
| Victor Tietje | 7 total / **3 efectivos** (excluyendo diario puro) | 3 / 24 posibles | Creación del proyecto AppV3 (estructura completa), vinculación login-user con hashcode de contraseña, corrección del arranque de la app vía `AndroidManifest.xml`. | Actualizado en las 3 fechas (22/01, 26/01, 12/02). Detalle reflexivo. Coincidencia alta con los commits reales. | El commit del 22/01 "actualizacion de documentacion" no tiene detalle en el diario, pero es de escaso impacto. Las dos entradas del diario del 12/02 son redundantes (dos commits distintos para el mismo diario). |
| Cosme Juan Rodríguez | 0 total / **0 efectivos** | 0 / 24 posibles | Sin actividad. | Sin entradas en el diario para el periodo del Sprint 5. El diario sólo llega hasta el 08/01/2026 (Sprint 4). | Discrepancia total: el tablero de Sprint 4 le asigna tareas completadas ("Done"), pero en Sprint 5 desaparece completamente sin registro ni justificación documentada. |

*Efectivos: se excluyen commits cuyo único contenido es la actualización del diario de trabajo.

---

## 4. Resumen Análisis 📈 (Individual)

---

**Nombre del [Ayman El Hattachi Annachabi]:**

* **Contribución principal:** Ha implementado el módulo completo de `RutinasDeGimnasio` (modelo, mock, DaoMock, repository, repositoryConverter y screen asociada). Además realizó ajustes relevantes en `Usuario.kt`, `UsuarioRepositoryConverter.kt` y `MainActivity.kt` para integrar los nuevos componentes con el resto de la app.

* **Seguimiento:** El diario está actualizado en las 3 fechas de actividad del sprint, con entradas descriptivas que coinciden razonablemente con los commits realizados. La posposición de tareas al Sprint 6 por los exámenes está documentada y justificada.

* **Análisis de las Task y/o UserStories asignadas durante el Sprint:** El tablero del Sprint 5 contiene 3 ítems genéricos sin asignación individual ("Integrar navegación real", "Mantener la estructura del proyecto", "Dar prioridad a otras asignaturas debido a examenes"), todos en estado **Retrospective** y sin ninguno en **Done**. Ayman contribuyó a acercar la app a una navegación funcional (módulo Rutinas + ajustes en MainActivity), aunque la tarea no llegó a marcarse como completada. El volumen de exámenes fue señalado como causa del arrastre de trabajo al Sprint 6.

* **Participación en el grupo de forma activa:** 🟡 — Asistió y participó en los 3 días de actividad del sprint, con contribuciones técnicas reales, pero la baja frecuencia de días activos (3 de 24 posibles) y la ausencia de tareas marcadas "Done" en el tablero limitan la valoración a notable con reservas.

* **Registro en Diario de Trabajo durante el Sprint:** 🟡 — El diario refleja las fechas de trabajo con cierto detalle, pero las entradas se concentran en las mismas fechas de los commits; no hay seguimiento en días intermedios ni reflexión profunda sobre el avance técnico.

* **Participación en el Incremento:** 🟡 — Contribuyó con un módulo funcional nuevo (`RutinasDeGimnasio`) y con mejoras en `Usuario`, pero ninguna tarea del sprint está marcada como "Done" en el tablero. El incremento existe en código pero no fue validado formalmente.

---

**Nombre del [Victor Tietje Fonollosa]:**

* **Contribución principal:** Responsable de la creación del proyecto AppV3 (incluida la estructura completa de carpetas, screens, modelos y configuración Gradle), así como de la implementación del sistema de login con vinculación segura al usuario (conversión de contraseñas a HashCode) y la corrección de errores en `AndroidManifest.xml` y `themes.xml` que impedían el arranque de la app.

* **Seguimiento:** El diario está actualizado en las 3 fechas con entradas reflexivas, detalladas y coherentes con los commits. El nivel de reflexión personal y técnica es el más elevado del grupo. La entrada del 12/02 reconoce el impacto de la época de exámenes con madurez.

* **Análisis de las Task y/o UserStories asignadas durante el Sprint:** Al igual que el resto, las 3 tareas del Sprint 5 en el tablero no tienen asignación individual y están en estado **Retrospective** (ninguna "Done"). Sin embargo, Victor fue el artífice del commit más significativo del sprint: la creación de AppV3 con todo el proyecto base, que sirvió de punto de partida para el resto del equipo. No obstante, esta tarea no se reflejó correctamente como un ítem cerrado en el tablero de Scrum.

* **Participación en el grupo de forma activa:** 🟢 — Participó activamente en los días de trabajo, con el commit de mayor impacto del sprint. Muestra iniciativa tanto técnica como de organización del equipo (sprint review, reorganización de carpetas).

* **Registro en Diario de Trabajo durante el Sprint:** 🟢 — El diario incluye entradas en las 3 fechas activas del sprint, con reflexión personal de calidad, detalles técnicos precisos y coherencia con los commits. Es el diario más completo y reflexivo del equipo.

* **Participación en el Incremento:** 🟡 — Su contribución técnica es la más significativa del sprint (AppV3 + login funcional), pero ninguna tarea está formalmente cerrada en el tablero como "Done". El incremento es real en el repositorio, pero no trazable en el tablero Scrum.

---

**Nombre del [Cosme Juan Rodríguez Pérez]:**

* **Contribución principal:** Sin contribución durante el Sprint 5. No realizó ningún commit en el periodo analizado (2026-01-20 a 2026-02-12).

* **Seguimiento:** El diario de trabajo no tiene entradas para este sprint. La última entrada registrada es del 08/01/2026 (Sprint 4). No hay justificación documentada de la ausencia.

* **Análisis de las Task y/o UserStories asignadas durante el Sprint:** No se le asignaron tareas en el tablero de Sprint 5 (ninguno de los 3 ítems tiene asignado a `CosmeJuan`). Esto contrasta con su participación en Sprint 4, donde tenía dos tareas en "Done" ("Desarrollar la ventana Tareas", "Desarrollar la ventana Home"). No existe registro de su participación en el sprint review ni en la retrospectiva.

* **Participación en el grupo de forma activa:** 🔴 — Ausencia total de commits y de actualizaciones en el diario durante todo el sprint. No hay evidencia de actividad en el repositorio ni en el tablero del proyecto.

* **Registro en Diario de Trabajo durante el Sprint:** 🔴 — El diario no tiene ninguna entrada para el periodo del Sprint 5. Está desactualizado desde el 08/01/2026.

* **Participación en el Incremento:** 🔴 — Sin contribución alguna al incremento del Sprint 5.

---

## 5. Resumen Análisis 📈 (Por Grupo)

**Nombre del [ElGranCombopi2damiesbalmis] - [Project]:**

* **Funcionalidad del incremento entregado:** 🔴 — Ningún miembro del equipo tiene tareas marcadas como "Done" en el Sprint 5. Las 3 tareas del sprint están todas en estado "Retrospective". El incremento existe en el código (AppV3 creado, módulo Rutinas implementado, login funcional), pero no fue validado ni cerrado formalmente en el tablero Scrum.

* **Realización de los eventos de scrum, aplicando su filosofía:** 🟡 — Se realizó el Sprint Review del Sprint 5 (documentado en los diarios de Ayman y Victor el 12/02), y hay 3 ítems en la columna "Retrospective" que evidencian una retrospectiva. Sin embargo, Cosme no tiene evidencia de participación en estos eventos, y las tareas del sprint carecen de asignaciones individuales, lo que debilita la trazabilidad del trabajo personal.

* **Compromiso del equipo con el flujo de trabajo de Scrum a través de GitHub Projects:** 🔴 — Las 3 tareas del Sprint 5 no tienen asignaciones individuales, ninguna pasó a "Done" y todo el trabajo pendiente se trasladó al Sprint 6. A pesar del trabajo real registrado en commits (creación AppV3, login funcional, módulo Rutinas), el sprint no refleja ese trabajo en el tablero de forma ordenada. Uno de los tres miembros no tuvo ningún ítem asignado, ningún commit ni actualización de diario durante el sprint.

---

## 6. Resumen Ejecutivo 📈

1. **Tendencias y Patrones:**
   * **2026-01-22 – Creación de AppV3 y sprint review:** Victor lidera la reorganización del proyecto y la creación de la versión 3 de la app, estableciendo una base sólida para el resto del sprint. El equipo (Ayman y Victor) realizó el sprint review del Sprint 4 de forma conjunta ese mismo día.
   * **2026-01-26 – Pico de actividad técnica:** Día más productivo del sprint, con 7 commits de código relevantes. Victor implementó el sistema de login seguro con hashcode; Ayman construyó el módulo completo de RutinasDeGimnasio. Cosme estuvo ausente.
   * **2026-02-12 – Cierre del sprint con resaca de exámenes:** El sprint cerró con exclusividad en actualizaciones de diario, sprint review y creación del Sprint 6. El equipo reconoce abiertamente el impacto del período de exámenes en la productividad.

2. **Calidad del Trabajo:**
   * Los commits de contenido técnico son de calidad: los mensajes son descriptivos, los cambios están cohesionados y la arquitectura que se sigue (modelo / mock / daomock / repository / screen) es coherente y escalable.
   * La implementación del hashcode para contraseñas en `LoginDaoMock` es un detalle positivo desde el punto de vista de la seguridad.
   * El proyecto AppV3, creado en un único commit masivo, sería más trazable si se hubiera dividido en commits incrementales más pequeños.

3. **Áreas de Mejora:**
   * **Asignación de tareas en el tablero:** Las tareas del sprint deben tener siempre un responsable asignado y deben moverse a "Done" cuando se completen. No hacerlo rompe la trazabilidad Scrum.
   * **Regularidad de los commits:** 3 días activos en 24 posibles es un ritmo bajo, especialmente para un sprint ya afectado por exámenes. Se recomienda al menos mantener la actualización del diario y del tablero de forma semanal aunque no haya commits de código.
   * **Cosme:** Es urgente que retome su participación activa en el proyecto. Si existen causas personales o dificultades técnicas que impiden su contribución, deben comunicarse al equipo y quedar documentadas en el diario.
   * **Compromisos de sprint:** Considerar un menor alcance de sprint cuando se anticipe un período de exámenes, en lugar de trasladar tareas masivamente al siguiente sprint.
