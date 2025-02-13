package com.example.ifplanmilk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IFPlanResultSimulation(
    var tenAguaSolo: Double,
    var prodForragem: Double,
    var capaSuporte: Double,
    var taxaLotacao: Double,
    var itu: Double,
    var dpl: Double,
    var pegadaHidrica: Double,
    var prodDiaria: Double,
    var prodLeiteDia: Double,
    var prodLeiteAno: Double,
    var perdaReceitaEstresse: Double,
    var coe: Double,
    var cot: Double,
    var receitaTotalAno: Double,
    var mlArea: Double,
    var trci: Double,
    var payback: Double,
)
