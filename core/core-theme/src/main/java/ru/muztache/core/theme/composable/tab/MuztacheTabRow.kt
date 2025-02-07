package ru.muztache.core.theme.composable.tab

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun MuztacheTabRow(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = Color.Transparent,
        indicator = { },
        divider = { },
        modifier = Modifier
            .clip(RoundedCornerShape(MuztacheTheme.corners.medium))
            .then(modifier),
        tabs = tabs
    )
}
