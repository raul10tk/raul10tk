package com.raul.gastos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Categoria(val etiqueta: String, val emoji: String) {
    COMIDA("Comida", "🍴"),
    TRANSPORTE("Transporte", "🚌"),
    OCIO("Ocio", "🎬"),
    HOGAR("Hogar", "🏠"),
    SALUD("Salud", "💊"),
    COMPRAS("Compras", "🛒"),
    OTROS("Otros", "✨");
}

@Entity(tableName = "gastos")
data class Gasto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cantidad: Double,
    val categoria: Categoria,
    val nota: String,
    val fechaEpochMillis: Long
)
