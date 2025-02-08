package com.example.ifplanmilk.ui.components.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import androidx.wear.compose.material.MaterialTheme
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import com.example.ifplanmilk.ui.theme.Typography
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

@OptIn(InternalCoroutinesApi::class)
@Composable
fun IFPlanDialog(
    modifier: Modifier = Modifier.fillMaxWidth().size(height = 300.dp, width = 100.dp),
    dialogTitle: String,
    dialogText: String = "This is an example dialog.",
    icon: ImageVector,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    showDialog: Boolean = false,
    content: @Composable () -> Unit = {}
) {
    if(showDialog) {
        val coroutineScope = rememberCoroutineScope()
        Dialog(
            onDismissRequest = { onDismissRequest() },
        ) {
            // Draw a rectangle shape with rounded corners inside the dialog
            OutlinedCard(
                modifier = modifier,
                colors = CardDefaults.outlinedCardColors(),
                border = BorderStroke(
                    2.dp,
                    androidx.compose.material3.MaterialTheme.colorScheme.inverseSurface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = dialogTitle,
                        modifier = Modifier.size(38.dp).align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = dialogTitle,
                        style = Typography.titleSmall,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Container for the input fields
                    content()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text(
                                text = "Cancelar", style = Typography.titleMedium.copy(
                                    color = MaterialTheme.colors.error,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        TextButton(
                            onClick = {
                                coroutineScope.launch {
                                    onDismissRequest()
                                    onConfirmation()
                                }
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Criar")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun IFPlanModalPreview() {
    IFPlanMilkTheme {
        IFPlanDialog(
            dialogTitle = "Example Dialog",
            dialogText = "This is an example dialog.",
            icon = Icons.Rounded.Add,
            showDialog = true,
            content = {
                Text(text ="Hello modal")
            }
        )
    }
}