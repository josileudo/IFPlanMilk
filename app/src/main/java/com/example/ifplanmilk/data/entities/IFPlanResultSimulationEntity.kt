package com.example.ifplanmilk.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "result_table",
    foreignKeys = [
        ForeignKey(
            entity = IFPlanSimulationEntity::class,
            parentColumns = ["id"],
            childColumns = ["simulationId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class IFPlanResultSimulationEntity (
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    val simulationId: Long,
    val tenAguaSolo: Double,
    val prodForragem: Double,
    val capaSuporte: Double,
    val taxaLotacao: Double,
    val itu: Double,
    val dpl: Double,
    val pegadaHidrica: Double,
    val prodDiaria: Double,
    val prodLeiteDia: Double,
    val prodLeiteAno: Double,
    val perdaReceitaEstresse: Double,
    val coe: Double,
    val cot: Double,
    val receitaTotalAno: Double,
    val mlArea: Double,
    val trci: Double,
    val payback: Double,
)