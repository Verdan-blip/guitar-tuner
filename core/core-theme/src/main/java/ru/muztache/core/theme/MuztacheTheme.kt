package ru.muztache.core.theme

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import ru.muztache.core.theme.colors.ColorScheme
import ru.muztache.core.theme.colors.LocalMuztacheColors
import ru.muztache.core.theme.colors.muztacheDarkColorScheme
import ru.muztache.core.theme.colors.muztacheLightColorScheme
import ru.muztache.core.theme.corners.Corners
import ru.muztache.core.theme.corners.LocalMuztacheCorners
import ru.muztache.core.theme.corners.muztacheCorners
import ru.muztache.core.theme.paddings.LocalMuztachePaddings
import ru.muztache.core.theme.paddings.muztachePaddings
import ru.muztache.core.theme.paddings.Paddings
import ru.muztache.core.theme.sizes.LocalMuztacheSizes
import ru.muztache.core.theme.sizes.Sizes
import ru.muztache.core.theme.sizes.muztacheSizes
import ru.muztache.core.theme.snackbar.LocalMuztacheSnackBar
import ru.muztache.core.theme.typo.LocalMuztacheTypography
import ru.muztache.core.theme.typo.Typography
import ru.muztache.core.theme.typo.muztacheTypography

@Composable
fun MuztacheTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme)
        muztacheDarkColorScheme
    else
        muztacheLightColorScheme

    val paddings = muztachePaddings
    val typography = muztacheTypography
    val corners = muztacheCorners
    val snackBarState = remember { SnackbarHostState() }
    val sizes = muztacheSizes

    CompositionLocalProvider(
        LocalMuztacheColors provides colorScheme,
        LocalMuztacheTypography provides typography,
        LocalMuztachePaddings provides paddings,
        LocalMuztacheCorners provides corners,
        LocalMuztacheSnackBar provides snackBarState,
        LocalMuztacheSizes provides sizes,
        content = content
    )
}

object MuztacheTheme {

    val colors: ColorScheme
        @Composable get() = LocalMuztacheColors.current

    val paddings: Paddings
        @Composable get() = LocalMuztachePaddings.current

    val typography: Typography
        @Composable get() = LocalMuztacheTypography.current

    val corners: Corners
        @Composable get() = LocalMuztacheCorners.current

    val snackBarHost: SnackbarHostState
        @Composable get() = LocalMuztacheSnackBar.current

    val sizes: Sizes
        @Composable get() = LocalMuztacheSizes.current
}
