package ru.muztache.core.theme.composable.progress

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun MuztacheProgressIndicator(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition("pickTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                delayMillis = 100,
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "pickRotation"
    )
    Box(
        modifier = modifier
    ) {
        val logo = AnimatedImageVector.animatedVectorResource(R.drawable.ic_logo)
        Image(
            painter = rememberAnimatedVectorPainter(logo, true),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(64.dp)
                .rotate(rotation),
            contentScale = ContentScale.Inside
        )
    }
}

@Preview
@Composable
private fun MuztacheProgressIndicatorPreview() {
    MuztacheTheme {
        MuztacheProgressIndicator()
    }
}
