package com.example.ifplanmilk.ui.components.simulation.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.data.model.IFPlanResultSimulation
import com.example.ifplanmilk.data.model.IFPlanSeeItem
import com.example.ifplanmilk.data.model.mock.IFPlanResultSimulationMock
import com.example.ifplanmilk.ui.components.form.IFPlanSeeItemResult
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import com.example.ifplanmilk.ui.theme.Typography

@Composable
fun ResultSimulationContainer(
    modifier: Modifier = Modifier,
    result: IFPlanResultSimulation?
){
    val soilAtAnimalsResult = listOf(
        IFPlanSeeItem(
            title = "Tensão da água no solo (bar)",
            value = result?.tenAguaSolo ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Produção de forragem (kg MV/m2)",
            value = result?.prodForragem ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Capacidade de suporte (animais)",
            value = result?.capaSuporte ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Taxa de lotação (vacas/ha)",
            value = result?.taxaLotacao ?: 0.0
        ),
        IFPlanSeeItem(
            title = "ITU",
            value = result?.itu ?: 0.0
        ),
        IFPlanSeeItem(
            title = "DPL (L/vaca/dia)",
            value = result?.dpl ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Pegada hídrica (L H2O/L leite)",
            value = result?.pegadaHidrica ?: 0.0
        ),
    )
    val systemsEconomicResult = listOf(
        IFPlanSeeItem(
            title = "Produção diária (L/dia)",
            value = result?.prodDiaria ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Produção de leite (L/ha/dia)",
            value = result?.prodLeiteDia ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Produção de leite (L/ha/ano)",
            value = result?.prodLeiteAno ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Perda receita estresse (R$/ano)",
            value = result?.perdaReceitaEstresse ?: 0.0
        ),
        IFPlanSeeItem(
            title = "COE (R$/L)",
            value = result?.coe ?: 0.0
        ),
        IFPlanSeeItem(
            title = "COT (R$/L)",
            value = result?.cot ?: 0.0
        ),
        IFPlanSeeItem(
            title = "ML (R$/L)",
            value = result?.mlArea ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Receita por área (R$/ha/ano)",
            value = result?.receitaTotalAno ?: 0.0
        ),
        IFPlanSeeItem(
            title = "TRCI (%a.a.)",
            value = result?.trci ?: 0.0
        ),
        IFPlanSeeItem(
            title = "Payback (anos)",
            value = result?.payback ?: 0.0
        ),
    )
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 26.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        OutlinedCard (
            modifier = modifier.fillMaxWidth(),
            colors = CardDefaults.outlinedCardColors(),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.inverseSurface)
        ) {
            Text(
                text = "Solo-Água-Planta-Animal",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                soilAtAnimalsResult.forEach { item ->
                    IFPlanSeeItemResult(item = item)
                }
            }
        }

        OutlinedCard(
            modifier = modifier.fillMaxWidth(),
//            onClick = onClick,
            colors = CardDefaults.outlinedCardColors(),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.inverseSurface)
        ) {
            Text(
                text = "Sistemas-Custos-Resultado-Econômico",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                systemsEconomicResult.forEach { item ->
                    IFPlanSeeItemResult(item = item)
                }
            }
        }
    }
}

@Preview
@Composable
fun ResultSimulationContainerPreview(){
    IFPlanMilkTheme  {
        ResultSimulationContainer(
            result = IFPlanResultSimulationMock[0]
        )
    }
}