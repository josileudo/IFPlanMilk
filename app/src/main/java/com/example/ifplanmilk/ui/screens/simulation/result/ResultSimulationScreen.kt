package com.example.ifplanmilk.ui.screens.simulation.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ifplanmilk.data.model.IFPlanSeeItem
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import com.example.ifplanmilk.ui.components.form.IFPlanSeeItemResult
import com.example.ifplanmilk.ui.components.simulation.ResultVariationSimulation
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import com.example.ifplanmilk.ui.theme.Typography

@Composable
fun ResultSimulation(
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val soilAtAnimalsResult = listOf(
        IFPlanSeeItem(
            title = "Tensão da água no solo (bar)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Produção de forragem (kg MV/m2)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Capacidade de suporte (animais)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Taxa de lotação (vacas/ha)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "ITU",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "DPL (L/vaca/dia)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Pegada hídrica (L H2O/L leite)",
            value = "10.0"
        ),
    )

    val systemsEconomicResult = listOf(
        IFPlanSeeItem(
            title = "Produção diária (L/dia)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Produção de leite (L/ha/dia)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Produção de leite (L/ha/ano)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Perda receita estresse (R$/ano)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "COE (R$/L)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "COT (R$/L)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "ML (R$/L)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Receita por área (R$/ha/ano)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "TRCI (%a.a.)",
            value = "10.0"
        ),
        IFPlanSeeItem(
            title = "Payback (anos)",
            value = "10.0"
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = "Resultado da simulação", modifier.fillMaxWidth())

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 26.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                OutlinedCard(
                    modifier = modifier.fillMaxWidth(),
//            onClick = onClick,
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

        Box {
            Box(modifier = Modifier.fillMaxWidth()) {
                // Botão, posicionado na parte inferior do Box
                IFPlanButton(
                    onClick = { showBottomSheet = true },
                    text = "Simular",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .zIndex(1f)
                )

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = (-20).dp)
                        .zIndex(1f)
                        .background(color = Color.White, shape = CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Sharp.KeyboardArrowUp,
                        contentDescription = "Arrow Up",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
    if (showBottomSheet) {
        ResultVariationSimulation(
            onDismiss = { showBottomSheet = false },
            modifier = Modifier.zIndex(5f)
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultSimulationPreview() {
    IFPlanMilkTheme {
        ResultSimulation(modifier = Modifier.zIndex(5f))
    }
}