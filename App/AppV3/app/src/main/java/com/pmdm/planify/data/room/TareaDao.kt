package com.pmdm.planify.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TareaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: TareaEntity)

    @Update
    suspend fun update(tarea: TareaEntity)

    @Query("DELETE FROM tareas WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT COUNT(*) FROM tareas")
    suspend fun count(): Int

    @Query("SELECT * FROM tareas")
    suspend fun getAll(): List<TareaEntity>

    @Query("SELECT * FROM tareas WHERE id = :id")
    suspend fun getById(id: String): TareaEntity?

    @Query("SELECT * FROM tareas WHERE completada = 1")
    suspend fun getCompletadas(): List<TareaEntity>

    @Query("SELECT * FROM tareas WHERE etiqueta = :etiqueta")
    suspend fun getByEtiqueta(etiqueta: String): List<TareaEntity>
}