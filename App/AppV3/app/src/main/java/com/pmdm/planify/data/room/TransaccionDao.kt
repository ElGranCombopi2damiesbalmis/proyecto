package com.pmdm.planify.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransaccionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaccion: TransaccionEntity)

    @Update
    suspend fun update(transaccion: TransaccionEntity)

    @Query("DELETE FROM transacciones WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT COUNT(*) FROM transacciones")
    suspend fun count(): Int

    @Query("SELECT * FROM transacciones")
    suspend fun getAll(): List<TransaccionEntity>

    @Query("SELECT * FROM transacciones WHERE id = :id")
    suspend fun getById(id: String): TransaccionEntity?

    @Query("SELECT * FROM transacciones WHERE tipo = :tipo")
    suspend fun getByTipo(tipo: String): List<TransaccionEntity>

    // Calcula el saldo: suma ingresos, resta gastos
    @Query("SELECT SUM(CASE WHEN tipo = 'INGRESO' THEN cantidad ELSE -cantidad END) FROM transacciones")
    suspend fun getSaldo(): Double
}