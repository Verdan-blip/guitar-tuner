package ru.muztache.feature.tuner.ui.composable.indicator

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember

interface TuningIndicatorState {

    val progress: State<Float>

    @SuppressLint("ComposableNaming")
    @Composable
    fun changeDeviationAnimation(newProgress: Float)
}

class TuningIndicatorStateImpl(initialState: Float) : TuningIndicatorState {

    override var progress: State<Float> = mutableFloatStateOf(
        initialState.coerceIn(PROGRESS_RANGE)
    )

    @Composable
    override fun changeDeviationAnimation(newProgress: Float) {
        progress = animateFloatAsState(
            targetValue = newProgress.coerceIn(PROGRESS_RANGE),
            label = LABEL_PROGRESS_ANIMATION
        )
    }

    companion object {

        const val LABEL_PROGRESS_ANIMATION = "Progress animation"
        val PROGRESS_RANGE = -0.5f..0.5f
    }
}

@Composable
fun rememberProgressIndicatorState(
    initialState: Float = 0f
): TuningIndicatorState = remember {
    TuningIndicatorStateImpl(initialState)
}