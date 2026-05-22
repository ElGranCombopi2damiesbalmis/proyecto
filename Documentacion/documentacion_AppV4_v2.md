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
Comentarios: `findByEmail` se usa para evitar duplicados; `onConflict=ABORT` obliga a manejar el conflicto en repositorio.

2) Repositorio (ejemplo parcial)
```kotlin
class UsuarioRepository @Inject constructor(private val usuarioDao: UsuarioDao) {
  suspend fun registrar(nombre: String, email: String, password: String): Result<Long> {
    // Hash de la contraseña antes de almacenar
    val hash = hashPassword(password)
    val entity = UsuarioEntity(nombre = nombre, email = email, passwordHash = hash)
    return try {
      val id = usuarioDao.insert(entity)
      Result.success(id)
    } catch (e: SQLiteConstraintException) {
      Result.failure(e) // email duplicado, manejar en UI
    }
  }
}
```
Comentarios: el repositorio encapsula la lógica y protege la UI de excepciones de bajo nivel.

3) ViewModel (simplificado)
```kotlin
@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: UsuarioRepository): ViewModel() {
  private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
  val uiState: StateFlow<LoginUiState> = _uiState

  fun registrar(nombre: String, email: String, pass: String) = viewModelScope.launch {
    _uiState.value = LoginUiState.Loading
    when(val res = repo.registrar(nombre, email, pass)) {
      is Result.Success -> _uiState.value = LoginUiState.Success
      is Result.Failure -> _uiState.value = LoginUiState.Error(res.exception)
    }
  }
}
```
Comentarios: ViewModel expone flujos inmutables y maneja scope de coroutine.

## 6. Manual de usuario

### Tipos de usuarios
- Usuario estándar: puede registrarse, gestionar sus tareas, transacciones y registrar estado de ánimo.
- (Futuro) Usuario administrador: gestión avanzada y supervisión (no implementado).

### Capturas y prototipos
- Dentro de `app/src/main/res/layout` y paquetes `ui.features` encontrará los componentes de UI.
- Recomendación: generar capturas reales en emulador y colocarlas en `Documentacion/screenshots/`.
- Ejemplos de pantallas a documentar con captura: Login, Home, Crear Tarea, Lista de Transacciones, Estado de Ánimo.

### Flujos básicos (guía rápida)
1. Registro: Abrir app → Registrar → completar formulario → confirmar.
2. Login: introducir email y contraseña → acceder al Home.
3. Crear tarea: Home → botón `+` → rellenar campos → Guardar.
4. Añadir transacción: Ir a Economía → `+` → seleccionar tipo/categoría → Guardar.
5. Registrar estado de ánimo: Pantalla Estado de Ánimo → seleccionar valor → Guardar.

## 7. Requisitos e instalación

### Entregable y estructura de ficheros (resumen)
```
AppV4/
├─ app/
│  ├─ src/main/java/com/pmdm/planify/...
│  ├─ src/main/res/...
│  └─ build.gradle.kts
├─ build.gradle.kts
├─ settings.gradle.kts
└─ ENTREGA_CAMBIOS.md
Documentacion/
├─ documentacion_AppV4.md
└─ screenshots/ (sugerido)
docs/
├─ diarios/
└─ ...
Reportes/
```

### Procedimiento de instalación y prueba
1. Abrir `App/AppV4` en Android Studio.
2. Verificar JDK 17 configurado y Android SDK con API 34.
3. Ejecutar desde Android Studio (Run -> app) o desde terminal:
```powershell
.\gradlew :app:assembleDebug
.\gradlew :app:installDebug
```
4. La base de datos Room se crea automáticamente al iniciar la app. Para forzar datos iniciales se añadió precarga en `PlanifyApp`.
5. Para probar la app manualmente: crear un usuario, añadir tareas y transacciones, comprobar persistencia tras reinicio.

## 8. Conclusiones

### Conclusiones sobre el trabajo realizado
- La reestructuración dejó la app preparada con arquitectura MVVM, persistencia real con Room y DI con Hilt.
- La migración a Kotlin y Compose facilita mantenimiento y evolución.

### Posibles ampliaciones y mejoras
- Sincronización remota y autenticación federada.
- Pruebas unitarias e instrumentadas.
- Módulo de backup/restore y exportación CSV/PDF.

## Consideraciones y buenas prácticas para la entrega
- Evitar imprimir código con fondo negro; usar tema claro o formatear para impresión.
- No generar diagramas de clases automáticos sin control: filtrarlos y simplificarlos (mostrar solo las clases relevantes con atributos y métodos significativos).
- Documentar fragmentos de código comentados en el repositorio para facilitar lectura.

## 9. Bibliografía

Libros, artículos y apuntes
- Material de curso y apuntes de Android, MVVM y Room.

Direcciones Web
- Documentación Android: https://developer.android.com
- Repositorio del proyecto: (este repositorio local)
- Diario de Cosme: [docs/diarios/cosme_rodriguez.md](docs/diarios/cosme_rodriguez.md)
