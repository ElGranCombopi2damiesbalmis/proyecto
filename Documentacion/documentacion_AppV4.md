# Planify — Documentación del proyecto (versión actualizada)

## Índice
- 1. Título
- 2. Introducción
- 3. Requisitos funcionales
- 4. Análisis y Diseño
  - 4.1 Diagrama de arquitectura
  - 4.2 Módulos y despliegue
  - 4.3 Diagrama de casos de uso
  - 4.4 Diagrama de clases
  - 4.5 Diseño de datos
- 5. Codificación
- 6. Manual de usuario
- 7. Requisitos e instalación
- 8. Conclusiones
- 9. Bibliografía


# Planify — Documentación del proyecto (versión actualizada)

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

### 4.1 Diagrama de arquitectura (PlantUML)
Diagrama de la arquitectura
![Diagrama de arquitectura](./screenshots/architecture.png)

### 4.2 Módulos y despliegue
- `app` (módulo Android): UI, navegación, recursos, `MainActivity`.
- `ui.features`: pantallas Compose por feature (Login, Home, Tareas, Economia, EstadoAnimo, Ajustes).
- `viewmodel`: ViewModels por pantalla.
- `repository`: Repositorios que encapsulan lógica de acceso a datos.
- `data`/`room`: `@Entity`, `@Dao`, `PlanifyDatabase`, converters.
- `di`: módulos Hilt, `PlanifyApp` con precarga de datos.

Tipo de aplicación y dependencias externas
- Aplicación móvil Android (Kotlin + Jetpack Compose).
- Integraciones previstas: servicios REST para sincronización, Google Sign-In/Firebase Auth, analytics.
- Almacenamiento local: Room (SQLite). Opcionalmente Firestore o backend REST en futuras versiones.

### 4.3 Diagrama de casos de uso (PlantUML)
Diagrama de casos de uso
![Diagrama de casos de uso](./screenshots/usecases.png)

### 4.4 Diagrama de clases (atributos y métodos) — simplificado
Diagrama de clases (simplificado)
![Diagrama de clases](./screenshots/classes.png)

### 4.5 Diseño de datos (BD relacional — Room / SQLite)
Se presenta el modelo Entidad/Interrelación y la descripción de las tablas principales. Room mapeará estas entidades a tablas SQLite.

Tablas principales (esquema resumido):

- `Usuario` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nombre` TEXT NOT NULL,
  `email` TEXT UNIQUE NOT NULL,
  `passwordHash` TEXT NOT NULL
)

- `Tarea` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `usuarioId` INTEGER NOT NULL,
  `titulo` TEXT NOT NULL,
  `descripcion` TEXT,
  `fecha` TEXT,
  `estado` TEXT,
  FOREIGN KEY(`usuarioId`) REFERENCES `Usuario`(`id`)
)

- `Transaccion` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `usuarioId` INTEGER NOT NULL,
  `monto` REAL NOT NULL,
  `categoria` TEXT,
  `fecha` TEXT,
  `tipo` TEXT,
  FOREIGN KEY(`usuarioId`) REFERENCES `Usuario`(`id`)
)

- `EstadoAnimo` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `usuarioId` INTEGER NOT NULL,
  `fecha` TEXT,
  `valor` INTEGER,
  FOREIGN KEY(`usuarioId`) REFERENCES `Usuario`(`id`)
)

Notas:
- `fecha` se almacena en ISO-8601 (`YYYY-MM-DD`) y se convierte a `LocalDate` mediante converters en Room.
- Las migraciones deben manejarse explícitamente mediante `Migration` en producción.

Si se empleara NoSQL (p.ej. Firestore) la estructura propuesta sería: `usuarios/{userId}` documentos con subcolecciones `tareas`, `transacciones`, `estadoAnimo`.

## 5. Codificación

### Lenguajes y por qué
- Kotlin: lenguaje principal para la app Android (conciso, interoperable con Java, soporte oficial para Android y Jetpack Compose).
- (Java): files históricos y bocetos; migración a Kotlin recomendada y ya realizada en AppV4.

### Herramientas y entornos
- Android Studio (recomendado) con soporte Compose.
- Gradle (wrapper incluido).
- KSP para generación (Room/Hilt).
- Kotlinx.serialization para persistencia/serialización cuando sea necesario.

### Fragmentos de código relevantes (comentados)

1) Ejemplo de `@Entity` y `@Dao` (Room)
```kotlin
// UsuarioEntity.kt
@Entity(tableName = "Usuario")
data class UsuarioEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val nombre: String,
  @ColumnInfo(index = true) val email: String,
  val passwordHash: String
)

// UsuarioDao.kt
@Dao
interface UsuarioDao {
  @Query("SELECT * FROM Usuario WHERE email = :email LIMIT 1")
  suspend fun findByEmail(email: String): UsuarioEntity?

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insert(usuario: UsuarioEntity): Long

  @Update
  suspend fun update(usuario: UsuarioEntity)
}
```

## 6. Manual de usuario

### Tipos de usuario
- Usuario estándar: puede registrarse, iniciar sesión, gestionar tareas, registrar transacciones y estado de ánimo.
- (Futuro) Usuario administrador: gestión avanzada y supervisión, no implementado actualmente.

### Vista general del manual
El manual de usuario describe la aplicación por pantallas y flujos. Se recomienda acompañarlo con capturas de pantalla o prototipos de las pantallas clave para facilitar su comprensión.

### Pantallas y flujos principales
- **Pantalla de registro/login:** el usuario crea su cuenta o accede con correo y contraseña.
- **Home:** resumen de tareas del día, saldo económico y acceso rápido a las secciones principales.
- **Gestión de tareas:** crear, editar, marcar como completada y eliminar tareas.
- **Economía:** registrar ingresos/gastos por categoría, consultar histórico y ver estadísticas.
- **Estado de ánimo:** seleccionar un valor diario y revisar el historial de ánimo.

### Recomendaciones de prototipado
- Usar capturas de pantalla reales desde `App/AppV4/app/src/main/res` y `ui.features` o un prototipo visual similar.
- Incluir leyendas breves que expliquen cada componente de la UI.
- Priorizar las pantallas de Login, Home, Crear Tarea, Lista de Transacciones y Estado de Ánimo.

## 7. Requisitos e instalación

### Descripción del entregable
El entregable es la aplicación Android `App/AppV4` junto con la documentación de uso y diseño. Contiene:
- `App/AppV4/app`: código fuente Kotlin, recursos, layouts y navegación.
- `App/AppV4/build.gradle.kts` y `settings.gradle.kts`: configuración de Gradle.
- `App/AppV4/ENTREGA_CAMBIOS.md`: cambios implementados.
- `Documentacion/documentacion_AppV4.md`: documentación final.
- `Documentacion/screenshots/`: capturas o diagramas generados.
- `Documentacion/diagrams/`: definición PlantUML de diagramas.

### Estructura de ficheros
```
AppV4/
├─ app/
│  ├─ src/main/java/...       # código Kotlin y lógica de UI
│  ├─ src/main/res/...        # recursos gráficos y layouts
│  └─ build.gradle.kts        # configuración del módulo Android
├─ build.gradle.kts           # configuración del proyecto
├─ settings.gradle.kts        # configuración de módulos
└─ ENTREGA_CAMBIOS.md         # lista de cambios y mejoras
Documentacion/
├─ documentacion_AppV4.md     # documentación final
├─ screenshots/               # imágenes y capturas de apoyo
└─ diagrams/                  # diagramas PlantUML fuente
```

### Procedimientos de instalación y prueba
1. Abrir `App/AppV4` en Android Studio.
2. Configurar JDK 17 y Android SDK con API 34.
3. Sincronizar el proyecto Gradle.
4. Ejecutar en un emulador o dispositivo:
```powershell
cd "App/AppV4"
./gradlew assembleDebug
./gradlew installDebug
```
5. Lanzar la app y crear un usuario para verificar la persistencia en Room.

### Recursos adicionales
- La base de datos SQLite se crea automáticamente al iniciar la app.
- No es necesario crear bases de datos adicionales manualmente.
- Si se añade backend en el futuro, deberá documentarse la configuración de API y credenciales.

## 8. Conclusiones

### Conclusiones sobre el trabajo realizado
- La aplicación se estructuró con MVVM, Room y Hilt, proporcionando separación de responsabilidades y fácil mantenimiento.
- La documentación incluye diseño, datos, código y un manual de usuario básico.

### Posibles ampliaciones y mejoras
- Sincronización remota y autenticación federada.
- Pruebas unitarias e instrumentadas.
- Módulo de backup/restore y exportación de datos.

## 9. Bibliografía

Libros, artículos y apuntes
- Material de curso y apuntes de Android, MVVM y Room.

Direcciones Web
- Documentación Android: https://developer.android.com
- Repositorio del proyecto: (este repositorio local)
- Diario de Cosme: [docs/diarios/cosme_rodriguez.md](docs/diarios/cosme_rodriguez.md)


