package com.example.ifplanmilk.data.model

import java.util.Date

data class IFPlanSimulation (
    val id: Long = 0,
    val title: String,
    val creationDate: Date,
    val description: String?,
    val pesoCorporal: Double,
    val milkProduction: Double,
    val milkFatContent: Double,
    val pbFatMilk: Double,
    val horizontalShift: Double,
    val verticalShift: Double,
    val lactatingCows: Double,
    val investmentsPerLiters: Double,
    val familyIncome: Double,
    val depreciationRate: Double,
    val area: Double,
    val picketsNumber: Double,
    val precipitation: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val relativeHumidity: Double,
    val velocityVents: Double,
    val nDosage: Double,
    val otherAndWater: Double,
)
