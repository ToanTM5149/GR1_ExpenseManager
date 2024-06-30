package com.toan.expensemanagergr1.widget

import java.text.SimpleDateFormat
import java.util.Locale

object Ultis {

    fun formatDateToHumanReadableForm(dateInMillis: Long) : String {
        val dateFormatter = SimpleDateFormat("dd//MM//YY", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun formatToDecimalValue(double: Double): String {
        return String.format("%.2f", double)
    }

}