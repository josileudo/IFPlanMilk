package com.example.ifplanmilk.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.R

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onHeaderClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.onboarding_logo),
            contentDescription = "Logo image",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(width = 70.dp, height = 40.dp)
        )

        IconButton(onClick = { onHeaderClick() }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add button",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    HomeHeader()
}