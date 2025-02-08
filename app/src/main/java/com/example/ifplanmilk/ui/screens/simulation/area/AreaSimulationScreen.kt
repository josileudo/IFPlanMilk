package com.example.ifplanmilk.ui.screens.simulation.area

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.components.form.IFPlanCurrencyField
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun AreaSimulation(
    modifier: Modifier = Modifier,
    onEvent: (AreaSimulationUiEvent) -> Unit = {},
    uiState: AreaSimulationUiState
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.area,
            onValueChange = {
                onEvent(
                    AreaSimulationUiEvent.OnUpdateAreaFields(
                        field = "area",
                        value = it
                    )
                )
            },
            label = "Área (ha)"
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.picketsNumber,
            onValueChange = {
                onEvent(
                    AreaSimulationUiEvent.OnUpdateAreaFields(
                        field ="picketsNumber",
                        value = it
                    )
                )
            },
            label = "Número de piquetes (Unid)",
            lastItem = true
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AreaSimulationPreview() {
    IFPlanMilkTheme {
        AreaSimulation(uiState = AreaSimulationUiState())
    }
}