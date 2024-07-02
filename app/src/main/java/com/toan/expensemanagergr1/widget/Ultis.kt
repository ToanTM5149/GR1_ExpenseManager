package com.toan.expensemanagergr1.widget

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Ultis {

    fun formatDateToHumanReadableForm(dateInMillis: Long) : String {
        val date = Date(dateInMillis)
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormatter.format(date)
    }

    fun formatToDecimalValue(double: Double): String {
        return String.format("%.2f", double)
    }

}