package ru.muztache.core.theme.composable.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun MuztacheTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(MuztacheTheme.corners.extraLarge),
        colors = ButtonColors(
            containerColor = MuztacheTheme.colors.primary,
            contentColor = MuztacheTheme.colors.onPrimary,
            disabledContentColor = MuztacheTheme.colors.onPrimary,
            disabledContainerColor = MuztacheTheme.colors.primary
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MuztacheTheme.typography.labelLarge,
            modifier = Modifier
                .padding(all = MuztacheTheme.paddings.medium + MuztacheTheme.paddings.extraSmall)
        )
    }
}