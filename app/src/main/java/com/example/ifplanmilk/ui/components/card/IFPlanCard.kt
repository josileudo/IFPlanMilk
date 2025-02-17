package com.example.ifplanmilk.ui.components.card

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ifplanmilk.R
import com.example.ifplanmilk.data.model.IFPlanSimulation
import com.example.ifplanmilk.data.model.mock.IFPlanSimulationMock
import com.example.ifplanmilk.data.utils.timestampToDate
import com.example.ifplanmilk.ui.theme.IFPlanMilkTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IFPlanCard(
    modifier: Modifier = Modifier,
    data: IFPlanSimulation,
    onClick: () -> Unit = {},
    onDeleteItem: (IFPlanSimulation) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                coroutineScope.launch {
                    delay(2.seconds)
                    onDeleteItem(data)
                }
                true
            } else {
                false
            }
        }
    )
    SwipeToDismissBox(
        state = swipeToDismissState,
        backgroundContent = {
            val backgroundColor by animateColorAsState(
                targetValue = when (swipeToDismissState.currentValue) {
                    SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.primary
                    SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
                    SwipeToDismissBoxValue.Settled -> MaterialTheme.colorScheme.surface
                    else -> MaterialTheme.colorScheme.surface
                }, label = "Color animated"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(backgroundColor)
                    .align(Alignment.CenterVertically),
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }
        }
    ) {
        OutlinedCard(
            modifier = modifier.fillMaxWidth(),
            onClick = onClick,
            colors = CardDefaults.outlinedCardColors(),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.inverseSurface)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = data.title, style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )

                        data.description?.let {
                            Text(
                                text = data.description,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.calendar_month),
                                contentDescription = "calendar_icon",
                                modifier = Modifier.height(20.dp),
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = data.creationDate.timestampToDate(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal,
                                ),
                                maxLines = 2
                            )
                        }
                    }

                }

                Column(modifier = Modifier.padding(16.dp)) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(width = 34.dp, height = 34.dp)
                            .background(MaterialTheme.colorScheme.surfaceTint)
                    ) {
                        Icon(
                            modifier = Modifier.size(width = 22.dp, height = 22.dp),
                            painter = painterResource(R.drawable.download),
                            contentDescription = "download",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview()
@Composable
fun IFPLanCardPreview() {
    IFPlanMilkTheme {
        Column(modifier = Modifier.padding(8.dp)) {
            IFPlanCard(data = IFPlanSimulationMock[0])
        }
    }
}