package com.raul.gastos.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.raul.gastos.data.Categoria
import com.raul.gastos.data.Gasto
import com.raul.gastos.data.GastoDatabase
import com.raul.gastos.data.GastoRepository
import com.raul.gastos.util.Fechas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class EstadoGastos(
    val gastos: List<Gasto> = emptyList(),
    val totalHoy: Double = 0.0,
    val totalGeneral: Double = 0.0
)

class GastoViewModel(private val repo: GastoRepository) : ViewModel() {

    val estado: StateFlow<EstadoGastos> = combine(
        repo.observarTodos(),
        repo.observarTotalEntre(Fechas.inicioDeHoy(), Fechas.finDeHoy())
    ) { lista, totalHoy ->
        EstadoGastos(
            gastos = lista,
            totalHoy = totalHoy,
            totalGeneral = lista.sumOf { it.cantidad }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = EstadoGastos()
    )

    fun agregar(cantidad: Double, categoria: Categoria, nota: String) {
        if (cantidad <= 0.0) return
        viewModelScope.launch {
            repo.insertar(
                Gasto(
                    cantidad = cantidad,
                    categoria = categoria,
                    nota = nota.trim(),
                    fechaEpochMillis = System.currentTimeMillis()
                )
            )
        }
    }

    fun eliminar(gasto: Gasto) {
        viewModelScope.launch { repo.eliminar(gasto) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)
                val dao = GastoDatabase.get(app).gastoDao()
                GastoViewModel(GastoRepository(dao))
            }
        }
    }
}
