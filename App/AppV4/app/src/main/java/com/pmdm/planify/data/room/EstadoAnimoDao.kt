
package com.pmdm.planify.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.time.LocalDate

@Dao
interface EstadoAnimoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(registro: EstadoAnimoRegistroEntity)

    @Query("DELETE FROM estado_animo_registros WHERE fecha = :fecha")
    suspend fun delete(fecha: LocalDate)

    @Query("SELECT COUNT(*) FROM estado_animo_registros")
    suspend fun count(): Int

    @Query("SELECT * FROM estado_animo_registros")
    suspend fun getAll(): List<EstadoAnimoRegistroEntity>
}
