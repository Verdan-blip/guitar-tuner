package ru.muztache.core.theme.composable.surface

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun MuztacheSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        color = MuztacheTheme.colors.background,
        modifier = modifier,
        content = content
    )
}
