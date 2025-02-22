package com.example.ifplanmilk.ui.components.swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IFPlanActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector,
    tint: Color = MaterialTheme.colorScheme.secondary,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.background(backgroundColor)
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = tint)
    }
}

@Preview
@Composable
fun IFPlanActionButtonPreview() {
    IFPlanActionButton(icon = Icons.Filled.Delete, tint = Color.White)
}