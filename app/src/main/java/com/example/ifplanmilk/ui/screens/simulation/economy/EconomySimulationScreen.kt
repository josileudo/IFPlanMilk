package com.example.ifplanmilk.ui.screens.simulation.economy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.components.form.IFPlanCurrencyField
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme


@Composable
fun EconomySimulation(
    modifier: Modifier = Modifier,
    uiState: EconomySimulationUiState,
    onEvent: (EconomySimulationUiEvent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // INVESTIMENTOS POR LITROS
        IFPlanCurrencyField(
            label = "Investimentos por L (R$/L)",
            onValueChange = {
                onEvent(
                    EconomySimulationUiEvent.OnUpdateEconomyFields(
                        field = "investmentsPerLiters",
                        value = it
                    )
                )
            },
            value = uiState.investmentsPerLiters,
            decimalsNumber = 2
        )

        // RENDA FAMILIAR
        IFPlanCurrencyField(
            label = "Renda familiar (R$/mês)",
            value = uiState.familyIncome,
            decimalsNumber = 2,
            onValueChange = {
                onEvent(
                    EconomySimulationUiEvent.OnUpdateEconomyFields(
                        field = "familyIncome",
                        value = it
                    )
                )
            },
        )

        // TAXA DE DEPRECIAÇÃO
        IFPlanCurrencyField(
            label = "Taxa de depreciação (%a.a.)",
            value = uiState.depreciationRate,
            onValueChange = {
                onEvent(
                    EconomySimulationUiEvent.OnUpdateEconomyFields(
                        field = "depreciationRate",
                        value = it
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun EconomySimulationPreview() {
    IFPlanMilkTheme {
        EconomySimulation(
            uiState = EconomySimulationUiState()
        )
    }
}