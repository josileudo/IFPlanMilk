package com.example.ifplanmilk.ui.screens.simulation.sliders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlidersSimulationScreen(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    uiState: SlidersSimulationUiState,
    onEvent: (SlidersSimulationUiEvent) -> Unit = {}
) {
    // Estado do ModalBottomSheet
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = {
            Spacer(modifier = Modifier.height(16.dp))
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Simulação com variáveis",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Slider 1
            Text(text = "COE: ${uiState.sliderCoeValue.toInt()}%")
            Slider(
                value = uiState.sliderCoeValue,
                onValueChange = { onEvent(SlidersSimulationUiEvent.onUpdateSlideFiled("COE", it)) },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Slider 2
            Text(text = "DPL: ${uiState.sliderDplValue.toInt()}%")
            Slider(
                value = uiState.sliderDplValue,
                onValueChange = { onEvent(SlidersSimulationUiEvent.onUpdateSlideFiled("DPL", it)) },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Slider 3
            Text(text = "FOR: ${uiState.sliderForValue.toInt()}%")
            Slider(
                value = uiState.sliderForValue,
                onValueChange = { onEvent(SlidersSimulationUiEvent.onUpdateSlideFiled("FOR", it)) },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Slider 4
            Text(text = "MS: ${uiState.sliderMsValue.toInt()}%")
            Slider(
                value = uiState.sliderMsValue,
                onValueChange = { onEvent(SlidersSimulationUiEvent.onUpdateSlideFiled("MS", it)) },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Slider 5
            Text(text = "Preço: ${uiState.sliderPrecoValue.toInt()}%")
            Slider(
                value = uiState.sliderPrecoValue,
                onValueChange = {
                    onEvent(
                        SlidersSimulationUiEvent.onUpdateSlideFiled(
                            "PRECO",
                            it
                        )
                    )
                },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botão para fechar o BottomSheet (ou aplicar as alterações)
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Aplicar")
            }
        }
    }
}


@Preview
@Composable
private fun ResultVariationSimulationPreview() {
    var showBottomSheet by remember { mutableStateOf(false) }

    IFPlanMilkTheme {
        SlidersSimulationScreen(
            onDismiss = { showBottomSheet = true },
            uiState = SlidersSimulationUiState()
        )
    }
}