package com.example.ifplanmilk.ui.components.swipe

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun IFPlanSwipeReveal(
    modifier: Modifier = Modifier,
    isRevealed: Boolean,
    onExpanded: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    actions: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    var contextMenuWidth by remember { mutableFloatStateOf(0.0f) }
    val offset = remember { Animatable(initialValue = 0.0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = isRevealed, contextMenuWidth) {
        if (isRevealed) {
            offset.animateTo(targetValue = contextMenuWidth)
            onExpanded()
        } else {
            offset.animateTo(targetValue = 0.0f)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier.onSizeChanged {
            contextMenuWidth = it.width.toFloat()
        },verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offset.value.roundToInt(), 0) }
                .pointerInput (contextMenuWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = (offset.value + dragAmount)
                                    .coerceIn(0f, contextMenuWidth)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value >= contextMenuWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(contextMenuWidth)
                                        onExpanded()
                                    }
                                }

                                else -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                        onCollapsed()
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            content()
        }

    }
}

@Preview
@Composable
private fun IFPlanSwipeRevealPreview() {
    IFPlanMilkTheme {
        IFPlanSwipeReveal(isRevealed = false, actions = {}, content = {})
    }
}