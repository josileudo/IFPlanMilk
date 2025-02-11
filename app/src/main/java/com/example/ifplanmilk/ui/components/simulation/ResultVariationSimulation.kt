package com.example.ifplanmilk.ui.components.simulation

import android.widget.Space
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
import androidx.compose.ui.zIndex
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultVariationSimulation(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estados para cada slider
    var slider1Value by remember { mutableStateOf(50f) }
    var slider2Value by remember { mutableStateOf(50f) }
    var slider3Value by remember { mutableStateOf(50f) }
    var slider4Value by remember { mutableStateOf(50f) }

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
                    text = "Result Variation Simulation",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Slider 1
                Text(text = "Slider 1: ${slider1Value.toInt()}")
                Slider(
                    value = slider1Value,
                    onValueChange = { slider1Value = it },
                    valueRange = 0f..100f,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Slider 2
                Text(text = "Slider 2: ${slider2Value.toInt()}")
                Slider(
                    value = slider2Value,
                    onValueChange = { slider2Value = it },
                    valueRange = 0f..100f,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Slider 3
                Text(text = "Slider 3: ${slider3Value.toInt()}")
                Slider(
                    value = slider3Value,
                    onValueChange = { slider3Value = it },
                    valueRange = 0f..100f,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Slider 4
                Text(text = "Slider 4: ${slider4Value.toInt()}")
                Slider(
                    value = slider4Value,
                    onValueChange = { slider4Value = it },
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
        ResultVariationSimulation(onDismiss = {showBottomSheet = true})
    }
}