package com.example.ifplanmilk.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "simulation_table")
data class IFPlanSimulationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val creationDate: Long,
    val description: String = "",
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
    val waterAvailableForIrrigation: Double
)
