package com.example.ifplanmilk.data.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun Date.formatToSimpleDate(format: String = "dd/MM/yyyy"): String{
    val sdf = SimpleDateFormat(format)
    return sdf.format(this)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.timestampToDate(
    format: String = "dd/MM/yyyy",
    locale: Locale = Locale.getDefault(),
    zoneId: ZoneId = ZoneId.systemDefault()
): String {
    val formatter = DateTimeFormatter.ofPattern(format, locale).withZone(zoneId)
    return formatter.format(Instant.ofEpochMilli(this))
}