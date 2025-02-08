package com.example.ifplanmilk.ui.screens.simulation.climateSoil

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
fun ClimateSoilSimulationScreen(
    modifier: Modifier = Modifier,
    uiState: ClimateSoilSimulationUiState,
    onEvent: (ClimateSoilSimulationUiEvent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IFPlanCurrencyField(
            label = "Precipitação (mm/dia)",
            value = uiState.precipitation,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "precipitation",
                        value = it
                    )
                )
            },
        )

        // TEMPERATURA MÁXIMA
        IFPlanCurrencyField(
            label = "Temperatura máxima (°C)",
            value = uiState.maxTemperature,
            decimalsNumber = 1,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "maxTemperature",
                        value = it
                    )
                )
            },
        )

        // TEMPERATURA MÍNIMA
        IFPlanCurrencyField(
            label = "Temperatura mínima (°C)",
            value = uiState.minTemperature,
            decimalsNumber = 1,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "minTemperature",
                        value = it
                    )
                )
            },
        )

        // UMIDADE RELATIVA
        IFPlanCurrencyField(
            label = "Umidade relativa (%)",
            value = uiState.relativeHumidity,
            decimalsNumber = 1,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        "relativeHumidity",
                        value = it
                    )
                )
            },
        )

        // VELOCIDADE DO VENTO
        IFPlanCurrencyField(
            label = "Velocidade do Vento (m/s)",
            value = uiState.velocityVents,
            decimalsNumber = 1,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "VelocityVents",
                        it
                    )
                )
            },
        )

        // DOSE DE N
        IFPlanCurrencyField(
            label = "Dose de N (dose)",
            value = uiState.nDosage,
            decimalsNumber = 1,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "nDosage",
                        value = it
                    )
                )
            },
        )

        // ÁGUA E OUTROS USOS
        IFPlanCurrencyField(
            label = "Água e outros usos (L/mês)",
            value = uiState.otherAndWater,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "otherAndWater",
                        value = it
                    )
                )
            },
        )

        // ÁGUA DISPONIVEL PARA IRRIGACAO (m3/dia)
        IFPlanCurrencyField(
            label = "Água disp p/ irrigação (m3/dia)",
            value = uiState.waterAvailableToIrrigation,
            decimalsNumber = 2,
            onValueChange = {
                onEvent(
                    ClimateSoilSimulationUiEvent.OnUpdateClimateSoilFields(
                        field = "waterAvailableToIrrigation",
                        value = it
                    )
                )
            },
            lastItem = true
        )
    }
}

@Preview
@Composable
private fun ClimaSoloSimulationPreview() {
    IFPlanMilkTheme {
        ClimateSoilSimulationScreen(
            uiState = ClimateSoilSimulationUiState()
        )
    }
}