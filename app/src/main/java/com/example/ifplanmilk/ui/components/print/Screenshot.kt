package com.example.ifplanmilk.ui.components.print

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.ifplanmilk.data.utils.saveImageToStorage
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun ScreenshotComposable(
    isDetails: Boolean = false,
    simulationTitle: String,
    onNavigateToHome: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val rootView = LocalView.current
    val coroutineScope = rememberCoroutineScope()

    // Estado para controlar a visibilidade do conteúdo
    var isContentVisible by remember { mutableStateOf(true) }
    var contentBounds by remember { mutableStateOf<Rect?>(null) }
    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }
    val scrollState = rememberScrollState()

    // Debug log function
    fun logDebug(message: String) {
        Log.d("ScreenshotComposable", message)
    }

    // Função para tirar o print
    fun takeScreenshot() {
        try {
            logDebug("Starting screenshot capture")
            logDebug("Root view dimensions: ${rootView.width} x ${rootView.height}")

            if (rootView.width == 0 || rootView.height == 0) {
                logDebug("Error: Invalid view dimensions")
                return
            }

            val fullBitmap = Bitmap.createBitmap(
                rootView.width,
                rootView.height,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(fullBitmap)
            rootView.draw(canvas)

            contentBounds?.let { bounds ->
                logDebug("Content bounds: $bounds")
                val x = bounds.left.coerceAtLeast(0f).toInt()
                val y = bounds.top.coerceAtLeast(0f).toInt()
                val width = bounds.width.coerceAtMost(rootView.width.toFloat()).toInt()
                val height = bounds.height.coerceAtMost(rootView.height.toFloat()).toInt()

                logDebug("Cropping dimensions: x=$x, y=$y, width=$width, height=$height")

                if (x + width <= fullBitmap.width && y + height <= fullBitmap.height) {
                    bitmapState.value = Bitmap.createBitmap(fullBitmap, x, y, width, height)
                    logDebug("Successfully created cropped bitmap")
                } else {
                    bitmapState.value = fullBitmap
                    logDebug("Using full bitmap due to bounds exceeding limits")
                }
            } ?: run {
                bitmapState.value = fullBitmap
                logDebug("No content bounds available, using full bitmap")
            }
        } catch (e: Exception) {
            logDebug("Error taking screenshot: ${e.message}")
            e.printStackTrace()
        }
    }

    // Função para compartilhar o print
    fun shareScreenshot() {
        try {
            val screenshot = bitmapState.value
            if (screenshot == null) {
                logDebug("No bitmap available for sharing")
                return
            }

            logDebug("Saving bitmap to storage")
            val uri = saveImageToStorage(context = context, bitmap = screenshot, title = simulationTitle)

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/png"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            context.startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
            logDebug("Share intent launched successfully")
        } catch (e: Exception) {
            logDebug("Error sharing screenshot: ${e.message}")
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isDetails) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IFPlanButton(
                        onClick = { onNavigateToHome() },
                        iconRes = Icons.Filled.ArrowBack,
                    )
                    Text(
                        text = simulationTitle,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                IFPlanButton(
                    iconRes = Icons.Filled.Share,
                    onClick = {
                        coroutineScope.launch {
                            scrollState.scrollTo(0)
                            delay(100) // Pequeno delay para garantir que o conteúdo está renderizado
                            takeScreenshot()
                            delay(500) // Delay para garantir que o screenshot foi capturado
                            shareScreenshot()
                        }
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    contentBounds = Rect(
                        coordinates.positionInWindow(),
                        coordinates.size.toSize()
                    )
                }
        ) {
            content()
        }
    }
}