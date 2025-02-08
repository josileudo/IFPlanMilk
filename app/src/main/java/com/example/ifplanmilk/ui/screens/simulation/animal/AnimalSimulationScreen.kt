package com.example.ifplanmilk.ui.screens.simulation.animal

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
fun AnimalSimulation(
    modifier: Modifier = Modifier,
    uiState: AnimalSimulationUiState,
    onEvent: (AnimalSimulationUiEvent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.pesoCorporal,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field ="pesoCorporal",
                        value = it
                    )
                )
            },
            label = "Peso corporal (kg)",
            decimalsNumber = 2
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.milkProduction,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field = "milkProduction",
                        value = it
                    )
                )
            },
            label = "Produção de leite (L/vaca/dia)",
            decimalsNumber = 1
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.milkFatContent,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field = "milkFatContent",
                        value = it
                    )
                )
            },
            label = "Teor de gordura no leite (%)",
            decimalsNumber = 1
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.pbFatMilk,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field = "pbFatMilk",
                        value = it
                    )
                )
            },
            label = "Teor de PB no leite (%)",
            decimalsNumber = 1
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.horizontalShift,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field= "horizontalShift",
                        value = it
                    )
                )
            },
            label = "Deslocamento horizontal (m)"
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.verticalShift,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field = "verticalShift",
                        value = it
                    )
                )
            },
            label = "Deslocamento vertical (m)"
        )

        IFPlanCurrencyField(
            modifier = modifier.fillMaxWidth(),
            value = uiState.lactatingCows,
            onValueChange = {
                onEvent(
                    AnimalSimulationUiEvent.OnUpdateAnimalFields(
                        field ="lactatingCows",
                        value = it
                    )
                )
            },
            label = "Vacas em lactação (%)",
            decimalsNumber = 1,
            lastItem = true
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AnimalSimulationPreview() {
    IFPlanMilkTheme {
        AnimalSimulation(
            uiState = AnimalSimulationUiState()
        )
    }
}