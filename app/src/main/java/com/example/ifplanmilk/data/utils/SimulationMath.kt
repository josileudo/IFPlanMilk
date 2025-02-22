package com.example.ifplanmilk.data.utils

import com.example.ifplanmilk.data.model.IFPlanResultSimulation
import com.example.ifplanmilk.ui.screens.simulation.animal.AnimalSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.area.AreaSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.climateSoil.ClimateSoilSimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.economy.EconomySimulationUiState
import com.example.ifplanmilk.ui.screens.simulation.sliders.SlidersSimulationUiState
import kotlin.math.pow

class SimulationMath {
    fun simulate(
        areaState: AreaSimulationUiState,
        animalState: AnimalSimulationUiState,
        economyState: EconomySimulationUiState,
        climateSoilState: ClimateSoilSimulationUiState,
        slidersState: SlidersSimulationUiState
    ): IFPlanResultSimulation {
        val temperaturaMaxima = climateSoilState.maxTemperature
        val temperaturaMinima = climateSoilState.minTemperature
        val velocidadeVento = climateSoilState.velocityVents
        val precipitacao = climateSoilState.precipitation
        val pesoCorporal = animalState.pesoCorporal
        val producaoLeite = animalState.milkProduction
        val teorPB = animalState.pbFatMilk
        val teorGordura = animalState.milkFatContent
        val desloVertical = animalState.verticalShift
        val desloHorizontal = animalState.horizontalShift
        val taxaDepreciacao = economyState.depreciationRate
        val umidadeRelativa = climateSoilState.relativeHumidity
        val doseN = climateSoilState.nDosage
        val area = areaState.area
        val investimento = economyState.investmentsPerLiters
        val rendaFamiliar = economyState.familyIncome
        val numeroPiquetes = areaState.picketsNumber
        val vacasLactacao = animalState.lactatingCows
        val aguaUsos = climateSoilState.otherAndWater
        val aguaDisponivelPorIrrigacao = climateSoilState.waterAvailableToIrrigation

        val varCoe = (slidersState.sliderCoeValue / 100) + 1
        val varDpl = (slidersState.sliderDplValue / 100) + 1
        val varFor = (slidersState.sliderForValue / 100) + 1
        val varMs = (slidersState.sliderMsValue / 100) + 1
        val varPreco = (slidersState.sliderPrecoValue / 100) + 1

        println("*** $areaState $animalState $economyState $climateSoilState")

        // ETo (mm)
        val ETo = (((24.211 * temperaturaMaxima - 635.46) / 30.4) +
                ((53.984 * velocidadeVento + 10.898) / 30.4)) / 2

        // Irrigação (mm)
        val irrigacao = ETo - precipitacao

        // Água aplicada (mm/dia)
        val aguaAplicada = precipitacao + if (aguaDisponivelPorIrrigacao >= irrigacao) {
            irrigacao
        } else {
            aguaDisponivelPorIrrigacao
        }

        // Consumo (Kg MS/dia)
        val consumo =
            -4.69 + (0.0142 * pesoCorporal) + (0.356 * producaoLeite) + (1.72 * teorGordura)

        // Consumo de NDT
        val consumoNDT = ((((48.6 - (0.0183 * pesoCorporal)) + (0.435 * producaoLeite)
                + (0.728 * teorGordura) + (3.46 * teorPB)) * 1.04) / 100) * consumo

        // NDT DV (Deslocamento Vertical)
        val NDTdv = if (desloVertical > 0) {
            (0.00669 * pesoCorporal * (desloVertical / 1000)) * 0.43
        } else {
            0.0
        }

        // NDT DH=
        val NDTdh = (0.00048 * pesoCorporal * (desloHorizontal / 1000)) * 0.43

        // NDT deslocamento
        val NDTdeslocamento = NDTdh + NDTdv

        // Consumo total (Kg MS/dia)
        val consTotal = (consumo + ((NDTdeslocamento / consumoNDT) * consumo)) * varMs

        // Tensão da água no solo (bar)
        val tenAguaSolo = 0.0368068 + (-1.06252 / aguaAplicada)

        // Depreciação (R$/L)
        val depreciacao = (investimento * (taxaDepreciacao / 365))

        // ITU
        val ITU = (0.8 * (temperaturaMaxima + temperaturaMinima)) / 2 +
                (umidadeRelativa / 100) * ((temperaturaMaxima + temperaturaMinima) / 2 - 14.4) + 46.4

        // DPL
        val DPL = -1.075 - (1.736 * producaoLeite) + (0.02474 * producaoLeite * ITU)

        // Produção de forragem
        val prodForragem = ((1.36722 + (-0.284546 * tenAguaSolo) +
                (-2.13514 * tenAguaSolo.pow(2))) * doseN) * varFor

        // Forragem Disponível
        val forrDisponivel = ((prodForragem * 10000) * (area / numeroPiquetes)) * 0.2

        // Suplementação (kg MS/dia)
        val suplementacao = producaoLeite / 2.5

        // Capacidade de suporte (animais)
        val capaSuporte = (forrDisponivel * 0.95) / (consTotal - suplementacao)

        // DPL anual
        val DPLAnual = (DPL * capaSuporte) * 365

        // Produção diária
        val prodDiaria = producaoLeite * (capaSuporte * (vacasLactacao / 100))

        // COE R$/L
        val COE = (4.52816 + (-0.000142 * prodDiaria) +
                (0.00000000767199 * prodDiaria.pow(2)) +
                (-0.24042 * producaoLeite) +
                (0.004937 * producaoLeite.pow(2))) * varCoe

        // Produção de leite (L/ha/ano)
        val prodLeiteAno = (prodDiaria * 365) / area

        // Produção de leite (L/ha/dia)
        val prodLeiteDia = prodLeiteAno / 365

        // MDO familiar
        val mdoFamiliar = rendaFamiliar / (prodDiaria * 30.4)

        // Pegada hídrica
        val pegadaHidrica = (((aguaAplicada * 10000) * area) + (aguaUsos / 30.4)) / prodDiaria

        // Investimento total
        val investimentoTotal = investimento * prodDiaria

        // COT
        val COT = COE + mdoFamiliar + depreciacao

        // Participação da irrigação na água
        val partIrrAgua = (irrigacao / aguaAplicada) * 100

        // Preço do leite
        val precoLeite = ((0.631922 * prodDiaria.pow(0.102383)) +
                (-0.0132 * teorGordura.pow(2) + 0.1384 * teorGordura - 0.3089)) * varPreco

        // Receita total (R$/mês)
        val receitaTotalMes = (prodDiaria * precoLeite) * 30.4

        // ML (R$/L)
        val ML = precoLeite - COT

        // ML Anual
        val MLAnual = ML * prodDiaria * 365

        // Payback (Anos)
        val payback = investimentoTotal / MLAnual

        // Perda de receita com estresse
        val perdaReceitaEstresse = DPLAnual * precoLeite

        // Taxa de lotação
        val taxaLotacao = capaSuporte / area

        // TRCI
        val TRCI = (ML * 365) / investimento * 100

        // Receita por área
        val recArea = (receitaTotalMes * 12) / area

        // COE Total
        val COETotal = COE * prodDiaria * 365

        // Receita total (R$/ano)
        val receitaTotalAno = receitaTotalMes * 12

        // ML por área
        val MLArea = MLAnual / area

        return IFPlanResultSimulation(

            tenAguaSolo = if (!tenAguaSolo.isNaN()) tenAguaSolo else 0.0,
            prodForragem = if (!prodForragem.isNaN()) prodForragem else 0.0,
            capaSuporte = if (!capaSuporte.isNaN()) prodForragem else 0.0,
            taxaLotacao = if (!taxaLotacao.isNaN()) taxaLotacao else 0.0,
            itu = if (!ITU.isNaN()) ITU else 0.0,
            dpl = if (!DPL.isNaN()) DPL else 0.0,
            pegadaHidrica = if (!pegadaHidrica.isNaN()) pegadaHidrica else 0.0,
            prodDiaria = if (!prodDiaria.isNaN()) prodDiaria else 0.0,
            prodLeiteDia = if (!prodLeiteDia.isNaN()) prodLeiteDia else 0.0,
            prodLeiteAno = if (!prodLeiteAno.isNaN()) prodLeiteAno else 0.0,
            perdaReceitaEstresse = if (!perdaReceitaEstresse.isNaN()) perdaReceitaEstresse else 0.0,
            coe = if (!COE.isNaN()) COE else 0.0,
            cot = if (!COT.isNaN()) COT else 0.0,
            receitaTotalAno = if (!receitaTotalAno.isNaN()) receitaTotalAno else 0.0,
            mlArea = if (!MLArea.isNaN()) MLArea else 0.0,
            trci = if (!TRCI.isNaN()) TRCI else 0.0,
            payback = if (!payback.isNaN()) payback else 0.0,
        )
    }
}
