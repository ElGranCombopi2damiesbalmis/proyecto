package com.pmdm.planify.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        TareaEntity::class,
        TransaccionEntity::class,
        RutinaEntity::class,
        UsuarioEntity::class,
        EstadoAnimoRegistroEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PlanifyDB : RoomDatabase() {

    abstract fun tareaDao(): TareaDao
    abstract fun transaccionDao(): TransaccionDao
    abstract fun rutinaDao(): RutinaDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun estadoAnimoDao(): EstadoAnimoDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            PlanifyDB::class.java,
            "planify.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}