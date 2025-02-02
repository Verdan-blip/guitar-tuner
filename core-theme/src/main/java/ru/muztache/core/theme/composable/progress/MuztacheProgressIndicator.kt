package ru.muztache.core.theme.composable.progress

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Box(
        modifier = modifier
    ) {
        val logo = AnimatedImageVector.animatedVectorResource(R.drawable.ic_logo)
        Image(
            painter = rememberAnimatedVectorPainter(logo, true),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(64.dp),
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