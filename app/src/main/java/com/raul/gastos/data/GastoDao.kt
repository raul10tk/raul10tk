package com.raul.gastos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GastoDao {
    @Query("SELECT * FROM gastos ORDER BY fechaEpochMillis DESC")
    fun observarTodos(): Flow<List<Gasto>>

    @Query("SELECT COALESCE(SUM(cantidad), 0) FROM gastos WHERE fechaEpochMillis BETWEEN :inicio AND :fin")
    fun observarTotalEntre(inicio: Long, fin: Long): Flow<Double>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(gasto: Gasto): Long

    @Update
    suspend fun actualizar(gasto: Gasto)

    @Delete
    suspend fun eliminar(gasto: Gasto)
}
