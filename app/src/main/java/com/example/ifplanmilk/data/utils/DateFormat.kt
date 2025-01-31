package com.example.ifplanmilk.data.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Date.formatToSimpleDate(format: String = "dd/MM/yyyy"): String{
    val sdf = SimpleDateFormat(format)
    return sdf.format(this)
}