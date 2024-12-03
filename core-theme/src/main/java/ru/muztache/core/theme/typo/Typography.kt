package ru.muztache.core.theme.typo

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class Typography(
    val titleSmall: TextStyle,
    val titleMedium: TextStyle,
    val titleLarge: TextStyle,
    val subtitleSmall: TextStyle,
    val subtitleMedium: TextStyle,
    val subtitleLarge: TextStyle,
    val bodySmall: TextStyle,
    val bodyMedium: TextStyle,
    val bodyLarge: TextStyle
)

val muztacheTypography: Typography = Typography(
    titleSmall = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400),
    titleMedium = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W400),
    subtitleSmall = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W300),
    subtitleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400),
    subtitleLarge = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400),
    bodySmall = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W300),
    bodyMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400),
    bodyLarge = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400)
)

val LocalMuztacheTypography = staticCompositionLocalOf<Typography> {
    error("Typography has not been provided")
}