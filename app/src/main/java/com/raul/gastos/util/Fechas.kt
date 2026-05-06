package com.raul.gastos.util

import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

object Fechas {
    private val zona: ZoneId = ZoneId.systemDefault()

    fun inicioDeHoy(): Long =
        LocalDate.now(zona).atStartOfDay(zona).toInstant().toEpochMilli()

    fun finDeHoy(): Long =
        LocalDate.now(zona).plusDays(1).atStartOfDay(zona).toInstant().toEpochMilli() - 1

    fun aLocalDate(epochMillis: Long): LocalDate =
        Instant.ofEpochMilli(epochMillis).atZone(zona).toLocalDate()

    fun formatearFecha(epochMillis: Long): String =
        aLocalDate(epochMillis).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))

    fun formatearHora(epochMillis: Long): String =
        Instant.ofEpochMilli(epochMillis).atZone(zona).toLocalTime()
            .format(DateTimeFormatter.ofPattern("HH:mm"))
}

object Moneda {
    private val formato: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "ES"))

    fun formatear(valor: Double): String = formato.format(valor)
}
