package com.example.ifplanmilk.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.data.model.mock.IFPlanSimulationMock
import com.example.ifplanmilk.ui.components.card.IFPlanCardList
import com.example.ifplanmilk.ui.components.home.HomeDialog
import com.example.ifplanmilk.ui.components.home.HomeHeader
import com.example.ifplanmilk.ui.components.home.HomeSearchBar
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit = {},
    onNavigateToNewSimulation: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp).padding(top = 8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
            HomeHeader(
                modifier = Modifier.fillMaxWidth(),
                onHeaderClick = { onEvent(HomeUiEvent.OnOpenModal) }
            )

            HomeSearchBar(
                modifier = Modifier.fillMaxWidth()
            )

            IFPlanCardList(
                modifier =  modifier.fillMaxWidth(),
                simulations = IFPlanSimulationMock,
                onSimulationClick = {/*TODO*/ }
            )

            HomeDialog(
                dialogTitle = "Criar nova simulação",
                dialogText = "Você pode criar uma nova simulação clicando no botão abaixo.",
                icon = Icons.Filled.Add,
                onDismissRequest = { onEvent(HomeUiEvent.OnCloseModal) },
                onConfirmation = { onNavigateToNewSimulation() },
                showDialog = uiState.showDialog
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    IFPlanMilkTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onEvent = {}
        )
    }
}