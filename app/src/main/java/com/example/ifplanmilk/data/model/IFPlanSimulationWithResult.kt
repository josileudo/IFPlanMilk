package com.example.ifplanmilk.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.ifplanmilk.data.entities.IFPlanResultSimulationEntity
import com.example.ifplanmilk.data.entities.IFPlanSimulationEntity

data class IFPlanSimulationWithResult(
    @Embedded val simulation: IFPlanSimulationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "simulationId"
    )
    val result: IFPlanResultSimulationEntity?
)
