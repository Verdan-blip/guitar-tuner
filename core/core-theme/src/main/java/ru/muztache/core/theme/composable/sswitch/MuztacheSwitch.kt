package ru.muztache.core.theme.composable.sswitch

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.theme.MuztacheTheme

@Composable
fun MuztacheSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MuztacheTheme.colors.surface,
            checkedIconColor = MuztacheTheme.colors.primary,
            checkedTrackColor = MuztacheTheme.colors.primary,
            checkedBorderColor = MuztacheTheme.colors.primary,
            uncheckedThumbColor = MuztacheTheme.colors.surface,
            uncheckedTrackColor = MuztacheTheme.colors.neutral,
            uncheckedBorderColor = MuztacheTheme.colors.neutral
        ),
        modifier = modifier
    )
}

@Preview
@Composable
private fun MuztacheSwitchPreview() {
    MuztacheTheme {
        MuztacheSwitch(
            checked = false,
            onCheckedChange = {  }
        )
    }
}
