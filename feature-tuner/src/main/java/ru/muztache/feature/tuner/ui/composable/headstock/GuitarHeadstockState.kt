package ru.muztache.feature.tuner.ui.composable.headstock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

@Stable
interface HeadstockState {
    
    var selectedString: MutableIntState
}

@Stable
class HeadstockStateImpl : HeadstockState {

    override var selectedString: MutableIntState = mutableIntStateOf(0)
}

@Composable
fun rememberHeadstockState(): HeadstockState = remember {
    HeadstockStateImpl()
}