package com.pmdm.planify.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RutinaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rutina: RutinaEntity)

    @Update
    suspend fun update(rutina: RutinaEntity)

    @Query("DELETE FROM rutinas WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT COUNT(*) FROM rutinas")
    suspend fun count(): Int

    @Query("SELECT * FROM rutinas")
    suspend fun getAll(): List<RutinaEntity>

    @Query("SELECT * FROM rutinas WHERE id = :id")
    suspend fun getById(id: String): RutinaEntity?
}