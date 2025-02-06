package com.example.ifplanmilk.ui.components.lottie

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ifplanmilk.R

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    resInt: Int,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(resInt)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoadingAnimationPreview() {
    LoadingAnimation(
        resInt = R.raw.mind_person,
        modifier = Modifier.fillMaxWidth().size(200.dp)
    )
}