package com.example.ifplanmilk.data.utils

import java.lang.Double
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun String.currencyFormat(decimals: Int = 2): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
    val symbols: DecimalFormatSymbols = formatter.decimalFormatSymbols
    symbols.currencySymbol = ""

    formatter.decimalFormatSymbols = symbols
    formatter.maximumFractionDigits = decimals

    return formatter.format(Double(this)).toString()
}