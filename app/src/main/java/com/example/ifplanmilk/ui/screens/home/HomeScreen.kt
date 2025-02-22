package com.example.ifplanmilk.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.components.card.IFPlanCardList
import com.example.ifplanmilk.ui.components.home.HomeDialog
import com.example.ifplanmilk.ui.components.home.HomeHeader
import com.example.ifplanmilk.ui.components.home.HomeSearchBar
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit = {},
    onNavigateToNewSimulation: () -> Unit = {},
    onNavigateToDetails: (id: Long) -> Unit = {}
) {
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchSimulations)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
            HomeHeader(
                modifier = Modifier.fillMaxWidth(),
                onHeaderClick = { onEvent(HomeUiEvent.OnOpenModal) }
            )

            HomeSearchBar(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    onEvent(HomeUiEvent.OnUpdateSearchQuery(it))
                },
                value = uiState.filteredItems ?: ""
            )

                IFPlanCardList(
                    modifier = modifier.fillMaxWidth(),
                    simulations = uiState.simulationList,
                    onSimulationClick = {
                        onNavigateToDetails(it.id)
                    },
                    onDeleteSimulation = {
                        onEvent(HomeUiEvent.OnDeleteSimulation(it))
                    }
                )


            HomeDialog(
                dialogTitle = "Criar nova simulação",
                dialogText = "Você pode criar uma nova simulação clicando no botão abaixo.",
                icon = Icons.Filled.Add,
                onDismissRequest = {
                    onEvent(HomeUiEvent.OnCloseModal)
                    onEvent(HomeUiEvent.OnResetDialogForm)
                },
                onConfirmation = {
                    onEvent(HomeUiEvent.OnSaveForm).apply {
                        onNavigateToNewSimulation()
                    }
                },
                onConfirmationEnabled = uiState.formTitle.isNotEmpty(),
                showDialog = uiState.showDialog,
                uiState = uiState,
                onEvent = onEvent
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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