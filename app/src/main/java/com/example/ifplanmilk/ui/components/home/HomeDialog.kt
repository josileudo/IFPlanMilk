package com.example.ifplanmilk.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.components.dialog.IFPlanDialog
import com.example.ifplanmilk.ui.screens.home.HomeUiEvent
import com.example.ifplanmilk.ui.screens.home.HomeUiState
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun HomeDialog(
    dialogTitle: String,
    dialogText: String = "This is an example dialog.",
    icon: ImageVector,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    showDialog: Boolean = false,
    onConfirmationEnabled: Boolean = true,
    uiState: HomeUiState = HomeUiState(),
    onEvent: (HomeUiEvent) -> Unit = {}
) {
    IFPlanDialog(
        dialogTitle = dialogTitle,
        dialogText = dialogText,
        icon = icon,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
        onConfirmationEnabled = onConfirmationEnabled,
        showDialog = showDialog
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = uiState.formTitle,
                onValueChange = { onEvent(HomeUiEvent.OnUpdateFields("formTitle", it)) },
                placeholder = { Text("Título") },
            )

            OutlinedTextField(
                value = uiState.formDescription,
                onValueChange = { onEvent(HomeUiEvent.OnUpdateFields("formDescription", it)) },
                placeholder = { Text("Descrição") },
            )
        }
    }
}

@Preview
@Composable
private fun HomeDialogPreview() {
    IFPlanMilkTheme {
        HomeDialog(
            dialogTitle = "Example Dialog",
            dialogText = "This is an example dialog.",
            icon = Icons.Filled.Add,
            showDialog = true
        )
    }
}