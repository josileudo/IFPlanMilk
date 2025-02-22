package com.example.ifplanmilk.data.utils

import com.example.ifplanmilk.data.entities.IFPlanResultSimulationEntity
import com.example.ifplanmilk.data.model.IFPlanResultSimulation

fun IFPlanResultSimulationEntity.toDomain(): IFPlanResultSimulation {
    return IFPlanResultSimulation(
        tenAguaSolo = this.tenAguaSolo,
        prodForragem = this.prodForragem,
        capaSuporte = this.capaSuporte,
        taxaLotacao = this.taxaLotacao,
        itu = this.itu,
        dpl = this.dpl,
        pegadaHidrica = this.pegadaHidrica,
        prodDiaria = this.prodDiaria,
        prodLeiteDia = this.prodLeiteDia,
        prodLeiteAno = this.prodLeiteAno,
        perdaReceitaEstresse = this.perdaReceitaEstresse,
        coe = this.coe,
        cot = this.cot,
        receitaTotalAno = this.receitaTotalAno,
        mlArea = this.mlArea,
        trci = this.trci,
        payback = this.payback,
    )
}