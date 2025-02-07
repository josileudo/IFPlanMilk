package com.example.ifplanmilk.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Procurar por simulação",
    onValueChange: (String) -> Unit = {},
    value: String = ""
) {
    var query by remember  { mutableStateOf("") }
    var active by remember  { mutableStateOf(false) }

    // MARK: Home screen
    TextField(
        modifier = modifier.fillMaxWidth().border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(8.dp)
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        shape = MaterialTheme.shapes.small,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
        ),
    )
}

@Preview
@Composable
private fun HomeSearchBarPreview() {
    HomeSearchBar()
}
