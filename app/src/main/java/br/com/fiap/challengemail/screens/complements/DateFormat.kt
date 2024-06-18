package br.com.fiap.challengemail.screens.complements

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun DateFormat(dateString: String): Calendar? {
    try {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(dateString, formatter)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, date.year)
        calendar.set(Calendar.MONTH, date.monthValue - 1) // Meses em Java Calendar começam em 0
        calendar.set(Calendar.DAY_OF_MONTH, date.dayOfMonth)
        return calendar
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}