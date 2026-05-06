package com.raul.gastos.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromCategoria(value: Categoria): String = value.name

    @TypeConverter
    fun toCategoria(value: String): Categoria = Categoria.valueOf(value)
}
