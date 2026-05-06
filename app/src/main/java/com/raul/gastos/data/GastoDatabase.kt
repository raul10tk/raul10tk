package com.raul.gastos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Gasto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GastoDatabase : RoomDatabase() {
    abstract fun gastoDao(): GastoDao

    companion object {
        @Volatile private var instance: GastoDatabase? = null

        fun get(context: Context): GastoDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    GastoDatabase::class.java,
                    "gastos.db"
                ).build().also { instance = it }
            }
    }
}
