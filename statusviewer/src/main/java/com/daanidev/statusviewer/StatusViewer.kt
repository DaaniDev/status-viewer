package com.daanidev.statusviewer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class IndicatorLocationType {
    TOP,
    BOTTOM
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatusViewer(
    modifier: Modifier,
    imagesList: List<Int>,
    duration: Long = 50,
    progressIncrement: Float = 0.01f,
    progressColor: Color,
    indicatorHeight: Dp = 8.dp,
    topIndicatorPadding: Dp = 8.dp,
    bottomIndicatorPadding: Dp = 8.dp,
    indicatorLocationType: IndicatorLocationType = IndicatorLocationType.TOP,
    onClicked: () -> Unit = {}
) {

    val pagerState = rememberPagerState(pageCount = { imagesList.size })

    var progress by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(pagerState.currentPage) {
        progress = 0f
        while (progress < 1f) {
            delay(duration)
            progress += progressIncrement
        }
        val nextPage = (pagerState.currentPage + 1) % imagesList.size
        coroutineScope.launch {
            pagerState.animateScrollToPage(nextPage)
        }
    }
    Box(modifier = modifier) {

        HorizontalPager(state = pagerState) { page ->
            StatusItem(imagesList[page]) {
                onClicked()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    when (indicatorLocationType) {
                        IndicatorLocationType.TOP -> Alignment.TopCenter
                        IndicatorLocationType.BOTTOM -> Alignment.BottomCenter
                    }
                )
        ) {
            ProgressIndicator(
                imagesList.size,
                pagerState.currentPage,
                progress,
                progressColor,
                indicatorHeight,
                topIndicatorPadding,
                bottomIndicatorPadding,
            )

        }
    }
}

@Composable
fun StatusItem(status: Int, animationDuration: Int = 1000, onClicked: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = animationDuration)
        )
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable {
                onClicked()
            }) {
            Image(
                painter = painterResource(status),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ProgressIndicator(
    count: Int,
    currentPage: Int,
    progress: Float,
    progressColor: Color,
    indicatorHeight: Dp,
    topIndicatorPadding: Dp,
    bottomIndicatorPadding: Dp
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = topIndicatorPadding,
                start = 8.dp,
                end = 8.dp,
                bottom = bottomIndicatorPadding
            )
            .height(indicatorHeight),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(indicatorHeight)
                    .padding(6.dp)
            ) {
                val isCurrentPage = index == currentPage

                Canvas(modifier = Modifier.fillMaxSize()) {

                    drawLine(
                        color = Color.Gray.copy(alpha = 0.3f),
                        start = Offset(0f, size.height / 2f),
                        end = Offset(size.width, size.height / 2f),
                        strokeWidth = indicatorHeight.toPx(),
                        cap = StrokeCap.Round
                    )

                    if (isCurrentPage) {
                        val endX = size.width * progress

                        drawLine(
                            color = progressColor,
                            start = Offset(0f, size.height / 2f),
                            end = Offset(endX, size.height / 2f),
                            strokeWidth = indicatorHeight.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
        }
    }
}