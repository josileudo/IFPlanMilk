package com.example.ifplanmilk.ui.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun IfPlanFormItem(
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions? = null,
) {
    var fieldPosition by remember { mutableStateOf(0) }
    OutlinedTextField(
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                fieldPosition = coordinates.positionInRoot().y.toInt()
            },
        value = value,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange,
    )
}

@Preview
@Composable
fun IfPlanTextFieldPreview() {
    IfPlanFormItem(modifier = Modifier, value = "123")
}