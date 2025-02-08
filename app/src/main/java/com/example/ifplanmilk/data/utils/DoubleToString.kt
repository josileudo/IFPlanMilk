package com.example.ifplanmilk.data.utils

import kotlin.math.pow

fun doubleToString(value: Double, decimalsNumber: Int): String {
    if (value == 0.0) return ""
    val factor = 10.0.pow(decimalsNumber.toDouble())
    val longValue = (factor * value).toLong()

    return longValue.toString()
}