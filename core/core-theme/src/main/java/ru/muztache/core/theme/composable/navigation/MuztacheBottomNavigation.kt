package ru.muztache.core.theme.composable.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.muztache.core.theme.MuztacheTheme

data class BottomNavItem(
    val icon: ImageVector,
    val route: Any
)

@Composable
private fun BottomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(24.dp, 4.dp)
                .clip(RoundedCornerShape(
                    bottomEnd = MuztacheTheme.corners.small,
                    bottomStart = MuztacheTheme.corners.small
                ))
                .background(
                    if (isSelected)
                        MuztacheTheme.colors.primary
                    else
                        Color.Transparent
                )
        )
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = if (isSelected)
                MuztacheTheme.colors.primary
            else
                MuztacheTheme.colors.neutral,
            modifier = Modifier
                .clickable { onClick() }
                .padding(vertical = MuztacheTheme.paddings.medium)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MuztacheBottomNavigation(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    onClick: (BottomNavItem) -> Unit,
    selectedItemIndex: Int = 0
) {
    FlowRow(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(MuztacheTheme.colors.surface)
            .then(modifier)
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedItemIndex
            Column {
                BottomNavItem(
                    item = item,
                    onClick = { onClick(item) },
                    isSelected = isSelected
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MuztacheBottomNavigationPreview() {
    MuztacheTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MuztacheTheme.colors.background)
        ) {
            MuztacheBottomNavigation(
                items = List(3) {
                    BottomNavItem(Icons.Filled.Home, 5)
                },
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
        }
    }
}