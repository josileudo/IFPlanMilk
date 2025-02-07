package com.example.ifplanmilk.data.model

import androidx.compose.runtime.Composable

data class IFPlanStepSimulation(
    var title: String,
    var content: @Composable () -> Unit,
)