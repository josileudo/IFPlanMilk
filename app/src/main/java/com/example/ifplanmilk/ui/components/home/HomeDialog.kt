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
import com.example.ifplanmilk.ui.components.modal.IFPlanModal
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@Composable
fun HomeDialog(
    dialogTitle: String,
    dialogText: String = "This is an example dialog.",
    icon: ImageVector,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    showDialog: Boolean = false
) {
    IFPlanModal(
        dialogTitle = dialogTitle,
        dialogText = dialogText,
        icon = icon,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
        showDialog = showDialog
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle text change */ },
                placeholder = { Text("Título") },
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle text change */ },
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