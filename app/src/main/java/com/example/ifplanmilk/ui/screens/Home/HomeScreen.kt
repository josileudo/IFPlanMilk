package com.example.ifplanmilk.ui.screens.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Column {
        Text(text = "Hello welcome to IF Plan Milk")
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}