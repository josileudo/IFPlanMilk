package com.example.ifplanmilk.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.data.model.IFPlanSimulation
import com.example.ifplanmilk.data.model.mock.IFPlanSimulationMock
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun IFPlanCardList(
    modifier: Modifier = Modifier,
    simulations: List<IFPlanSimulation>,
    onSimulationClick: (IFPlanSimulation) -> Unit 
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = simulations, key = {it.id}) { simulation ->
            IFPlanCard(
                data = simulation,
                onClick = { onSimulationClick(simulation) }
            )
        }
    }
}

@Preview
@Composable
private fun IFPlanCardListPreview() {
    IFPlanMilkTheme {
        IFPlanCardList(simulations = IFPlanSimulationMock, onSimulationClick = {})
    }
}