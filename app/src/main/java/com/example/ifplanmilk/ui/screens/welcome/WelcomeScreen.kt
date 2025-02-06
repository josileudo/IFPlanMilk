package com.example.ifplanmilk.ui.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ifplanmilk.data.model.Welcome
import com.example.ifplanmilk.data.utils.scaledDp
import com.example.ifplanmilk.ui.components.button.IFPlanButton
import com.example.ifplanmilk.ui.components.lottie.LoadingAnimation
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(
    onNavigateToHome: () -> Unit = {},
    onEvent: (WelcomeUiEvent) -> Unit = {},
    uiState: WelcomeUiState
) {
    val pagerList = listOf(
        Welcome.Fist,
        Welcome.Second,
        Welcome.Third
    )
    val pagerState = rememberPagerState(pageCount = { 3 })

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            TextButton(
                onClick = {
                    onNavigateToHome()
                    onEvent(WelcomeUiEvent.OnCompleteOnboarding)
              },
                modifier = Modifier.align(Alignment.End))
            {
                Text(text = "Pular")
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) {position ->
                PagerScreen(
                    welcome = pagerList[position],
                    screenHeight = screenHeight
                )
            }

            HorizontalPagerScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                pagerState = pagerState,
            )

            FinishButton(
                modifier = Modifier
                    .fillMaxWidth(),
                pagerState = pagerState,
                onFinishClicked = {
                    onNavigateToHome()
                    onEvent(WelcomeUiEvent.OnCompleteOnboarding)
                }
            )
        }
    }

}

@Composable
fun PagerScreen(
    modifier: Modifier = Modifier,
    welcome: Welcome,
    screenHeight: Dp
) {
    val imageSize = screenHeight * 0.4f

    Column(
        modifier = modifier.fillMaxHeight(0.8f).heightIn(max = imageSize),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LoadingAnimation(
            modifier = Modifier.size(imageSize).aspectRatio(1f),
            resInt = welcome.image
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
              Text(
                  modifier = modifier.fillMaxWidth(),
                  text = welcome.title,
                  style = MaterialTheme.typography.headlineLarge,
                  textAlign = TextAlign.Center,
                  fontSize = when {
                      LocalDensity.current.density < 1.5 -> 24.sp
                      LocalDensity.current.density < 2.5 -> 28.sp
                      else -> 32.sp
                  }
              )
            Text(
                text = welcome.subtitle,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun HorizontalPagerScreen(
    modifier: Modifier,
    pagerState: PagerState
) {
    val dotSize = 16.dp.scaledDp()
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(dotSize)
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onFinishClicked: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            IFPlanButton(
                modifier = Modifier.fillMaxWidth(fraction = 0.8f).padding(vertical = 8.dp),
                text = "Finalizar",
                onClick = { onFinishClicked() },
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showBackground = true, showSystemUi = true, device = "spec:width=673dp,height=1481dp")
@Preview(showBackground = true, showSystemUi = true, device = "spec:width=1280dp,height=800dp")
@Composable
fun WelcomeScreenPreview() {
     IFPlanMilkTheme {
         WelcomeScreen(
             uiState = WelcomeUiState()
         )
     }
}