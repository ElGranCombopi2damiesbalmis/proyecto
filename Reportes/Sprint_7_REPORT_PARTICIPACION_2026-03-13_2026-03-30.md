# Informe de Participación — Sprint 7
**Organización:** ElGranCombopi2damiesbalmis  
**Proyecto:** Project  
**Periodo analizado:** 2026-03-13 a 2026-03-30  
**Generado el:** 2026-04-27

---

## 1. Estadísticas Generales

- **Total de usuarios activos:** 1 (Ayman El Hattachi) _(Victor tiene tareas Done en el tablero pero sin commits en el periodo; Cosme sin actividad)_
- **Periodo analizado:** 2026-03-13 a 2026-03-30
- **Días con actividad:** 2 (21/03 y 23/03)
- **Total de commits (excl. @juanjobalmis):** 9
- **Commits efectivos de código (excl. diario/merges):** 4
- **Archivos modificados únicos:** ~110 (mayoritariamente ficheros Kotlin de App/AppV4)

---

## 2. Registro de Commits

> Ordenados cronológicamente. Se excluyen los commits de `@juanjobalmis`.

| Nombre | Fecha | Archivos modificados | Resumen de lo que ha realizado |
|---|---|---|---|
| ayman | 21/03/2026 | App/AppV4/ (~80 archivos): modelos, repositorios, Room (DAOs, entities, converters), DaoMocks, Mocks, ViewModels, Screens, navegación, di/PlanifyApp | Integración completa de la versión 4 del proyecto (AppV4). Incluye arquitectura MVVM con Room como persistencia real, precarga de datos iniciales, navegación Compose unificada y módulo Hilt. |
| ayman | 21/03/2026 | App/AppV4/app/build.gradle.kts, App/AppV4/settings.gradle.kts | Reestructuración del proyecto: ajuste de configuración Gradle de AppV4. |
| ayman | 21/03/2026 | docs/diarios/ayman_elhattachi.md | Actualización del diario de trabajo (entrada S30). |
| ayman | 21/03/2026 | docs/diarios/ayman_elhattachi.md | Actualización del diario de trabajo (entrada S30 continuación). |
| ayman | 23/03/2026 | App/AppV4: LoginRepository, UserSessionRepository, UsuarioRepository, AjustesVM, AnalisisDeGastos, AnalisisDeGastosViewModel, EstadoAnimoVM, LoginViewModel, GymVM, RutinasDeGimnasio, GestionDeTareasScreen, TareaViewModel, HomeViewModel, NavHostPlanify, strings.xml, settings.gradle.kts (~22 archivos) | Funcionalidad del calendario añadida en la pantalla de Tareas; actualización de ViewModels y pantallas de todas las features con correcciones de integración. |
| ayman | 23/03/2026 | App/AppV4/.idea/.name, App/AppV4/settings.gradle.kts | Cambio de versión/nombre del proyecto en la configuración. |
| ayman | 23/03/2026 | docs/diarios/ayman_elhattachi.md, .claude/skills/\*, Reportes/Sprint_5\*, Reportes/Sprint_6\*, archivos .idea y .vs | Actualización del diario (entrada S31) + sincronización de cambios del main (skills juanjo + informes previos). |
| ayman | 23/03/2026 | docs/diarios/ayman_elhattachi.md | Actualización del diario de trabajo. |
| ayman | 23/03/2026 | docs/diarios/ayman_elhattachi.md | Actualización del diario de trabajo. |

---

## 3. Análisis Individualizado de Contribuciones

| Usuario | Commits (Efectivos)* | Días activos | Contribución principal | Seguimiento (Diario) | Discrepancias |
|---|---|---|---|---|---|
| Ayman El Hattachi | 4 efectivos / 9 totales | 2 / 13 días hábiles | Integración de AppV4 completa (Room, MVVM, Hilt) + funcionalidad calendario en Tareas | Al día: entradas S30 (21/03) y S31 (23/03) que coinciden con sus commits | Ninguna relevante; el diario describe exactamente lo que reflejan los commits |
| Víctor Tietje | 0 | 0 / 13 días hábiles | Sin commits en el periodo. Trabajo previo (Room/Hilt) realizado antes del sprint (11/03) e integrado por Ayman el 21/03 | Sin entradas de diario para el periodo (última entrada: 11/03, última del índice: 20/04 fuera del sprint) | Tiene 2 tareas Done asignadas en el tablero ("Vinculación módulo Login" y "Actualizar VMs con Hilt") sin ningún commit durante el sprint. El trabajo fue hecho antes de que comenzara el Sprint 7. |
| Cosme Rodríguez | 0 | 0 / 13 días hábiles | Sin actividad en el repositorio | Sin entradas de diario desde enero 2026 (más de 2 meses sin actualizar) | No tiene tareas asignadas en Sprint 7. Ausencia total tanto en el repositorio como en el tablero Scrum. |

_\* Efectivos = excluyendo commits de solo diario/documentación_

---

## 4. Resumen Análisis 📈 (Individual)

---

**Nombre del [Ayman El Hattachi Annachabi]:**

- **Contribución principal:** Ha realizado la mayor parte del trabajo del sprint: integración de la versión 4 del proyecto (AppV4) en el repositorio, que incluye la arquitectura MVVM completa con Room como persistencia real, módulo de inyección de dependencias con Hilt, navegación Compose unificada, ViewModels con anotaciones Hilt, Repositories con converters y DaoMocks, y precarga de datos iniciales. Adicionalmente, añadió funcionalidad de calendario en la pantalla de Gestión de Tareas con actualización de los ViewModels y pantallas de todas las features.

- **Seguimiento:** El diario está actualizado y es coherente con la actividad en el repositorio. Las entradas S30 (21/03) y S31 (23/03) describen exactamente lo que se ve en los commits: integración de la V4 con pruebas funcionales, y adición del calendario en Tareas. El diario es conciso pero preciso y sin discrepancias.

- **Análisis de las Task y/o UserStories asignadas durante el Sprint:** Ayman tiene asignadas 2 tareas en Sprint 7:
  - ✅ `#112 Agregar sincronización de los datos entre las vistas` → **Done**. Los commits de la integración de AppV4 y la actualización de ViewModels/pantallas confirman que esta tarea se completó efectivamente.
  - ✅ `#117 Actualizar los VMs con anotaciones de Hilt` → **Done** (coasignada con victietje). El commit de integración de AppV4 incluye todos los ViewModels con anotaciones Hilt, lo que confirma su participación en la tarea.
  - Las tareas `#113 Implementación de Room` y `#109 Implementar funcionalidades` están en estado **In Progress** sin asignación explícita, aunque el trabajo de Room está claramente presente en los commits de Ayman. Sería conveniente asignarse estas tareas formalmente.

- **Participación en el grupo de forma activa:** 🟡 - Ayman trabaja de forma activa y con contribuciones reales, pero solo estuvo presente 2 días del sprint (21 y 23 de marzo). Aunque la cantidad de trabajo es significativa, la concentración en tan pocos días indica que la actividad no fue distribuida a lo largo del sprint sino acumulada en esas dos fechas.

- **Registro en Diario de Trabajo durante el Sprint:** 🟢 - Diario actualizado y coherente con el trabajo realizado. Las entradas para los días de actividad (21/03 y 23/03) describen correctamente las tareas realizadas y son reflexivas. No hay días activos sin entrada en el diario.

- **Participación en el Incremento:** 🟢 - La contribución de Ayman es significativa: la integración de AppV4 representa el incremento funcional principal del sprint, con persistencia real mediante Room y funcionalidades completas y operativas. Hace buen uso del workflow de Git con commits bien estructurados y mensajes descriptivos.

---

**Nombre del [Víctor Tietje Fonollosa]:**

- **Contribución principal:** No ha realizado ningún commit durante el Sprint 7 (13-30 de marzo). Sin embargo, su trabajo previo de reestructuración del proyecto (AppV4) realizado durante los días 9 y 11 de marzo —antes del inicio oficial del sprint— fue integrado por Ayman el 21 de marzo. El ENTREGA_CAMBIOS.md confirma una reestructuración profunda y corrección de errores realizada por Víctor sobre el proyecto.

- **Seguimiento:** Sin entradas de diario para el período 13-30 de marzo. La última entrada del periodo lectivo normal es del 11/03 (S28 - Añadiendo Room), que se corresponde con trabajo previo al sprint. El diario no refleja ninguna actividad durante el Sprint 7 propiamente dicho.

- **Análisis de las Task y/o UserStories asignadas durante el Sprint:** Víctor tiene asignadas 2 tareas en Sprint 7:
  - ✅ `#95 Vinculación módulo de Login` → **Done**. El trabajo fue realizado antes del inicio del sprint (indicios en su diario del 26/01 y 11/03). La tarea está marcada Done pero sin commits en el periodo oficial del sprint.
  - ✅ `#117 Actualizar los VMs con anotaciones de Hilt` → **Done** (coasignada con aymanelhattachi). Igualmente, el trabajo fue anterior al sprint. La reestructuración documentada en ENTREGA_CAMBIOS.md confirma que Víctor realizó esta tarea.
  - Existe una discrepancia temporal: las tareas se marcan Done en Sprint 7 pero el trabajo se realizó durante Sprint 6 o incluso antes. Esto sugiere que el tablero Scrum no fue actualizado en tiempo real.

- **Participación en el grupo de forma activa:** 🟡 - Tiene trabajo real que aporta al proyecto (reestructuración profunda de AppV4), pero no hay actividad visible durante el periodo oficial del Sprint 7. Su trabajo fue integrado de forma indirecta a través de los commits de Ayman.

- **Registro en Diario de Trabajo durante el Sprint:** 🔴 - Sin ninguna entrada de diario durante el Sprint 7. La siguiente entrada después del 11/03 corresponde al 20/04, casi un mes y medio después.

- **Participación en el Incremento:** 🟡 - Su contribución al incremento es real pero indirecta: el ENTREGA_CAMBIOS.md demuestra una reestructuración técnica de gran calidad (corrección de errores, unificación de arquitectura, Room, Hilt). Sin embargo, al no haber ningún commit en el periodo del sprint, la contribución no es directamente visible dentro del Sprint 7.

---

**Nombre del [Cosme Juan Rodríguez Pérez]:**

- **Contribución principal:** Ninguna actividad en el repositorio ni en el tablero Scrum durante el Sprint 7. No hay commits, no hay tareas asignadas y el diario no ha sido actualizado desde el 8 de enero de 2026 (más de dos meses y medio de inactividad total).

- **Seguimiento:** El diario de Cosme está completamente desactualizado. No tiene entradas desde enero 2026. No hay ningún registro de actividad en el proyecto durante el Sprint 7, ni tampoco en los sprints intermedios (5, 6 y 7).

- **Análisis de las Task y/o UserStories asignadas durante el Sprint:** Cosme no tiene ninguna tarea asignada en Sprint 7. No hay evidencia de participación en el tablero Scrum.

- **Participación en el grupo de forma activa:** 🔴 - Ausencia total de actividad durante el Sprint 7 y varios sprints anteriores. No hay commits, tareas ni entradas de diario que evidencien participación.

- **Registro en Diario de Trabajo durante el Sprint:** 🔴 - El diario no ha sido actualizado desde enero 2026. No hay ningún registro del Sprint 7 ni de los sprints 5 y 6.

- **Participación en el Incremento:** 🔴 - Sin ninguna contribución al incremento del sprint.

---

## 5. Resumen Análisis 📈 (Por Grupo)

**ElGranCombopi2damiesbalmis — Project:**

- **Funcionalidad del incremento entregado:** 🟡 - Solo Ayman tiene tareas marcadas como Done en Sprint 7 (`#112 Agregar sincronización` y `#117 Actualizar VMs Hilt`). Víctor también tiene 2 tareas Done pero sin actividad en el sprint. Cosme no tiene tareas. Además, las tareas más importantes (`#113 Implementación de Room` y `#109 Implementar funcionalidades`) quedan en estado **In Progress** sin asignar, a pesar de que el trabajo de Room está claramente reflejado en los commits. No todos los miembros activos tienen tareas completadas dentro del periodo oficial.

- **Realización de los eventos de scrum, aplicando su filosofía:** 🔴 - Solo un miembro (Ayman) tiene actividad real de código durante el sprint. No hay evidencia de columna de retrospectiva ni tarjetas de retrospectiva en el tablero para Sprint 7. El trabajo de Víctor, aunque de calidad, fue realizado fuera del sprint y no hay evidencia de daily scrums o eventos de coordinación durante el periodo.

- **Compromiso del equipo con el flujo de trabajo de Scrum a través de GitHub Projects:** 🟡 - Hay tareas asignadas en Sprint 7 y algunas marcadas como Done, lo que indica cierto seguimiento del tablero. Sin embargo, se detectan problemas: las tareas `#113` y `#109` están en In Progress sin asignar explícitamente; las tareas de Víctor se marcaron Done en Sprint 7 pero el trabajo fue realizado antes del inicio del sprint; Cosme no tiene ninguna tarea asignada. El flujo de trabajo Scrum no está siendo seguido de forma rigurosa por todo el equipo.

---

## 6. Resumen Ejecutivo 📈

1. **Tendencias y Patrones:**
   - **13/03 - 30/03/2026 — Concentración de actividad y desequilibrio de carga:** El Sprint 7 muestra un patrón preocupante de desequilibrio: Ayman es prácticamente el único miembro activo con commits en el periodo oficial del sprint (9 commits en solo 2 días). Víctor aporta trabajo de reestructuración técnica de calidad pero realizado antes del inicio del sprint, lo que genera una desconexión entre el tablero Scrum y la realidad del repositorio. Cosme acumula ya más de tres sprints consecutivos sin actividad visible.

2. **Calidad del Trabajo:**
   - La integración de AppV4 realizada por Ayman representa un incremento funcional sólido: Room con persistencia real, MVVM con Hilt, navegación Compose unificada y precarga de datos. La reestructuración previa de Víctor (documentada en ENTREGA_CAMBIOS.md) demuestra un trabajo técnico profundo y bien razonado que facilitó la integración de AppV4. Sin embargo, las tareas de Room (`#113`) e "Implementar funcionalidades" (`#109`) permanecen sin asignar y en estado In Progress a pesar de que el código correspondiente existe en el repositorio. Esto refleja una gestión del tablero Scrum incompleta.

3. **Áreas de Mejora:**
   - **Cosme:** Urgente. Es necesario que retome su participación en el proyecto, actualice su diario y se asigne tareas en el tablero. Tres o más sprints sin actividad es una situación crítica que compromete la evaluación individual.
   - **Víctor:** Mejorar la sincronización entre el trabajo real y el registro en el tablero Scrum. Si el trabajo se realiza antes del sprint, debe organizarse de modo que los commits y actualizaciones del tablero queden dentro del periodo oficial. Es imprescindible retomar el diario de trabajo.
   - **Ayman:** Distribuir mejor el trabajo a lo largo del sprint en lugar de concentrarlo en uno o dos días. Asignarse formalmente las tareas que está realizando (`#113`, `#109`) para que el tablero refleje la realidad.
   - **Equipo:** Incorporar eventos de retrospectiva visibles en el tablero (tarjetas de retrospectiva) y asegurarse de que todos los miembros tienen tareas asignadas al comienzo de cada sprint.
