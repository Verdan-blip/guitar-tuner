package ru.muztache.core.theme.composable.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun MuztacheTextTab(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Tab(
        selected = selected,
        onClick = onClick,
        selectedContentColor = MuztacheTheme.colors.textPrimary,
        unselectedContentColor = MuztacheTheme.colors.textSecondary,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MuztacheTheme.typography.titleMedium,
            color = if (selected)
                MuztacheTheme.colors.textPrimary
            else
                MuztacheTheme.colors.textUnselected
        )
        if (selected) {
            Box(
                modifier = Modifier
                    .size(16.dp, 2.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = MuztacheTheme.corners.small,
                            bottomStart = MuztacheTheme.corners.small
                        )
                    )
                    .background(MuztacheTheme.colors.primary)
            )
        }
    }
}