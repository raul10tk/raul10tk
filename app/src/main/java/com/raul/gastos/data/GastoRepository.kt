package com.raul.gastos.data

import kotlinx.coroutines.flow.Flow

class GastoRepository(private val dao: GastoDao) {
    fun observarTodos(): Flow<List<Gasto>> = dao.observarTodos()
    fun observarTotalEntre(inicio: Long, fin: Long): Flow<Double> = dao.observarTotalEntre(inicio, fin)
    suspend fun insertar(gasto: Gasto) = dao.insertar(gasto)
    suspend fun actualizar(gasto: Gasto) = dao.actualizar(gasto)
    suspend fun eliminar(gasto: Gasto) = dao.eliminar(gasto)
}
