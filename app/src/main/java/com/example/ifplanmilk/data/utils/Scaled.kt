package com.example.ifplanmilk.data.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

// Extensions para escalonamento responsivo
@Composable
fun Dp.scaledDp(): Dp = with(LocalDensity.current) {
    val scaleFactor = when (this.density) {
        in 0.1f..1.0f -> 0.8f
        in 1.1f..2.0f -> 1.0f
        else -> 1.2f
    }
    this@scaledDp * scaleFactor
}

//fun Int.Sp.scaledSp(): TextUnit = with(LocalDensity.current) {
//    val scaleFactor = when (this.density) {
//        in 0.1f..1.0f -> 0.9f
//        in 1.1f..2.0f -> 1.0f
//        else -> 1.1f
//    }
//    (this@scaledSp.value * scaleFactor).sp
//}