package com.example.ifplanmilk.data.utils

import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity
import com.example.ifplanmilk.data.model.IFPlanSimulation

fun IFPlanSimulationEntity.toDomain(): IFPlanSimulation {
    return IFPlanSimulation(
        id = this.id,
        title = this.title,
        creationDate = this.creationDate,
        description = this.description,
        pesoCorporal = this.pesoCorporal,
        milkProduction = this.milkProduction,
        milkFatContent = this.milkFatContent,
        pbFatMilk = this.pbFatMilk,
        horizontalShift = this.horizontalShift,
        verticalShift = this.verticalShift,
        lactatingCows = this.lactatingCows,
        investmentsPerLiters = this.investmentsPerLiters,
        familyIncome = this.familyIncome,
        depreciationRate = this.depreciationRate,
        area = this.area,
        picketsNumber = this.picketsNumber,
        precipitation = this.precipitation,
        maxTemperature = this.maxTemperature,
        minTemperature = this.minTemperature,
        relativeHumidity = this.relativeHumidity,
        velocityVents = this.velocityVents,
        nDosage = this.nDosage,
        otherAndWater = this.otherAndWater,
        waterAvailableForIrrigation = this.waterAvailableForIrrigation
    )
}

fun IFPlanSimulation.toEntity(): IFPlanSimulationEntity{
    return IFPlanSimulationEntity(
        id = this.id,
        title = this.title,
        creationDate = this.creationDate,
        description = this.description,
        pesoCorporal = this.pesoCorporal,
        milkProduction = this.milkProduction,
        milkFatContent = this.milkFatContent,
        pbFatMilk = this.pbFatMilk,
        horizontalShift = this.horizontalShift,
        verticalShift = this.verticalShift,
        lactatingCows = this.lactatingCows,
        investmentsPerLiters = this.investmentsPerLiters,
        familyIncome = this.familyIncome,
        depreciationRate = this.depreciationRate,
        area = this.area,
        picketsNumber = this.picketsNumber,
        precipitation = this.precipitation,
        maxTemperature = this.maxTemperature,
        minTemperature = this.minTemperature,
        relativeHumidity = this.relativeHumidity,
        velocityVents = this.velocityVents,
        nDosage = this.nDosage,
        otherAndWater = this.otherAndWater,
        waterAvailableForIrrigation = this.waterAvailableForIrrigation
    )
}